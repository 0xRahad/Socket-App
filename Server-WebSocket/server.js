const express = require('express');
const socket= require('socket.io');
const fs = require('fs');
const app = express();
var PORT= process.env.PORT || 3000;
const server = app.listen(PORT)

app.use(express.static('public'));
console.log('Server is running');
const io = socket(server);

var count = 0;

io.on('connection',(socket) => {
    console.log("New socket connection "+ socket.id)
    socket.on('counter',() => {
        count++;
        io.emit("counter",count)
    })
})