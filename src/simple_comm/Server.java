import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by sudhakar on 4/2/16.
 */
public class Server {
    public static void main(String args[]) throws IOException {
        int port = Integer.parseInt("5430");
        BufferedReader br = null;
        PrintWriter pw = null;
        ServerSocket server_sc = null;

        try {
            server_sc = new ServerSocket(port);
            Socket client_sc = server_sc.accept();

            br = new BufferedReader(new InputStreamReader(client_sc.getInputStream()));
            pw = new PrintWriter(client_sc.getOutputStream(), true);

            String line = br.readLine();
            System.out.println("Hello from Client - "+line);
            pw.println("Hello from Server - "+line);

            while(!line.startsWith("Bye")){
                line = br.readLine();
                System.out.println("Client says :"+line);
                pw.println(line);
            }

            line = br.readLine();
            System.out.println(line);
        } catch (IOException e){
            System.out.println(e);
        } finally {
            server_sc.close();
        }

    }
}
