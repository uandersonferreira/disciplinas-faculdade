

//document.onload = () => {}
$(() => {
    
    const socket = io()
    console.log("Conectado ao servidor");

    $("form").submit(() => {
        socket.emit("message", $("#texto").val());
        return false;
    })


    socket.on("message", (texto) => $("#mensagens").append("<li>").text(texto));

});
