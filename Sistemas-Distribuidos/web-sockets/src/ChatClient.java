import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    //IP e Porta servidor
    private final static String SERVER_ADDRES = "127.0.0.1";
    private Socket clientSocket;
    Scanner scanner = new Scanner(System.in);
    private  PrintWriter printWriter;

    public void start() throws IOException {
        clientSocket = new Socket(SERVER_ADDRES, ChatServer.PORT);
//        bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));//Envio de dados
         printWriter = new PrintWriter(clientSocket.getOutputStream(), true);//Envio de dados e chama o fush() automaticamnete
//        bufferedWriter.newLine();//aloca uma quebra de linha no final da mensagem

        System.out.println("Cliente conectado ao servidor em "+SERVER_ADDRES+":"+ChatServer.PORT);
        messageLoop();
    }

    private void messageLoop() throws IOException {
        String mensagem;

        do {
            System.out.print("Digite uma mensagem (ou 's' para sair): ");
            mensagem = scanner.nextLine();
            printWriter.println(mensagem);
//            bufferedWriter.write(mensagem);//lança uma IOException
//            bufferedWriter.newLine();//lança uma IOException
//            bufferedWriter.flush();

        }while (!mensagem.equalsIgnoreCase("s"));

    }

    public static void main(String[] args) {
        try {
            ChatClient client = new ChatClient();
            client.start();
        } catch (IOException e) {
            System.out.println("Erro ao iniciar cliente: "+e.getMessage());
            throw new RuntimeException(e);
        }
        System.out.println("Cliente finalizado!");

    }

}//class
