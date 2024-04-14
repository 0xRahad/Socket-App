const express = require('express');
const app = express();
const http = require('http');
const expressServer = http.createServer(app);
const { Server } = require('socket.io');
const io = new Server(expressServer);
const mysql = require('mysql');

// MySQL connection configuration for XAMPP
const dbConfig = {
    host: 'localhost',
    user: 'root', // Default user for XAMPP
    password: '', // Default password for XAMPP (empty by default)
    database: 'user_db' // Replace 'your_database_name' with your actual database name
};

// Create a MySQL connection pool
const pool = mysql.createPool(dbConfig);

io.on('connection', function(socket) {
    console.log('New Android user connected');

    // Fetch data from database and emit it to the client
    const fetchData = () => {
        pool.query('SELECT * FROM user_table', (error, results, fields) => {
            if (error) {
                console.error('Error fetching data from MySQL:', error);
                return;
            }
            socket.emit('mysqlData', results);
        });
    };

    // Fetch data initially and at regular intervals
    fetchData();
    const intervalId = setInterval(fetchData, 10);

    // Handle socket disconnection (optional for better resource management)
    socket.on('disconnect', () => {
        clearInterval(intervalId);
        console.log('Android user disconnected');
    });


});

app.get('/', function(request, response) {
    response.sendFile(__dirname + '/index.html');
});

expressServer.listen(3000, function() {
    console.log('Server running at http://localhost:3000');
});
