import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by sudhakar on 4/2/16.
 */
public class Client {
    private static Socket cs = null;
    public static void main(String args[]) throws IOException {
        try {
            cs = new Socket("localhost", 5430);

            //Prepare the required streams to receive/transfer data
            BufferedReader br = null;
            PrintWriter pr = null;
            Scanner scanner = null;
            String userInput = null;
            try {
                br = new BufferedReader(new InputStreamReader(cs.getInputStream()));
                pr = new PrintWriter(cs.getOutputStream(), true);
                scanner = new Scanner(System.in);

                System.out.println("Connection Established with Server..\n");
                System.out.println("Type your Names\n");

                userInput = scanner.nextLine();
                System.out.println("Message sent as 'Hello from Client - " + userInput + "'");
                pr.println(userInput);
                System.out.println("Server :"+br.readLine());
                do{
                    userInput = scanner.nextLine();
                    pr.println(userInput);
                    System.out.println("Server: "+br.readLine());
                } while(!userInput.startsWith("Bye"));

                pr.println(userInput);

            } catch (IOException e) {
                System.out.println(e);
            } finally {
                if (br != null)
                    br.close();
                if (pr != null)
                    pr.close();
            }
        } catch(IOException e) {
        }finally
         {
             if (cs != null)
                 cs.close();
        }
    }
}

