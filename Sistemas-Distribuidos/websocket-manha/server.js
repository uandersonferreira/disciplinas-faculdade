const express = require("express")
const app = express()
app.use(express.static("public"))

const http = require("http").createServer(app)


//const newServer = require("socket.io")
//const serverSocket = newServer()
const serverSocket = require("socket.io")(http) //INCLUIDO


const PORT = 8000
http.listen(PORT, () => console.log(`Servidor iniciado na porta ${PORT}`))

app.get("/", (request, response) => response.sendFile(__dirname + "/index.html"))

serverSocket.on("connection", (socket) => {
    console.log(`Cliente ${socket.id} conectado`);

    socket.on("message", (texto) => {
        console.log(`Msg recebida de ${socket.id}:${texto}`);
        serverSocket.emit("message", texto)

    })

});