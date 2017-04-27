/**
 * Created by ivan on 10/04/2017.
 */
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    private int port;
    public static ServerSocket serverSocket;

    public Server(ServerSocket serverSocket, int port) {
        this.serverSocket = serverSocket;
        this.port = port;
    }

    @Override
    public void run() {
        String word;
        Socket clientSocket = null;

        try {
            System.out.println("Server running on port " +port +"...");

            while (!serverSocket.isClosed()) {
                clientSocket = serverSocket.accept();

                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

                word = input.readLine();

                ServerConsole.refreshConsole("Received: " +word);

                System.out.println("Received: " +word);
                String meaning = "word not found";

                int index = Utils.searchWord(word);
                if (index != -1) {
                    meaning = Utils.sigArray.get(index);
                    output.writeBytes(meaning +"\n");
                }
                else
                    output.writeBytes(meaning +"\n");

                ServerConsole.refreshConsole("Sent \"" +meaning +"\" to client.");
                System.out.println("Sent \"" +meaning +"\" to client.");
            }
        } catch (IOException ignored) {}
    }
}
