import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public final static int PORT = 4000;
    private ServerSocket serverSocket;
    private BufferedReader bufferedReader;


    public void start() throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.printf("Servidor inicicado com sucesso na porta '%d'%n",PORT);
        clientConnectionLoop();
    }

    private void clientConnectionLoop() throws IOException {
        while (true){
            Socket clientSocket =  serverSocket.accept();
            System.out.println("Cliente: "+clientSocket.getRemoteSocketAddress()+" conectou-se");
            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));//Recebimento de dados
            String mensagem = bufferedReader.readLine();//ler uma linha inteira até encontrar um /n - quebra de linha vinda do envio de dados

            System.out.printf("Mensagem recebida do cliente %s:%s %n", clientSocket.getRemoteSocketAddress(), mensagem);

        }
    }//method


    public static void main(String[] args) {
        try {
            ChatServer server = new ChatServer();
            server.start();

        } catch (IOException e) {
            System.out.println("Erro ao iniciar o servidor: "+e.getMessage());
            throw new RuntimeException(e);
        }

        System.out.println("Servidor finalizado. . .");

    }//main

}//class
/*


 */