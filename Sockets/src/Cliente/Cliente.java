package Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        String host = "127.0.0.1";
        int portaServidor = 666;
        try (Socket socket = new Socket (host, portaServidor)) {
            PrintWriter output = new PrintWriter (socket.getOutputStream ( ), true);
            BufferedReader input = new BufferedReader (new InputStreamReader (socket.getInputStream ( )));
            Scanner scanner = new Scanner (System.in);
            String mensasgem = null;
            while (!"exit".equalsIgnoreCase (mensasgem)) {
                mensasgem = scanner.nextLine ( );
                output.println (mensasgem);
                output.flush ( );
                System.out.println (input.readLine ( ));
            }
            scanner.close ( );
        } catch (IOException e) {
            e.printStackTrace ( );
        }
    }
}