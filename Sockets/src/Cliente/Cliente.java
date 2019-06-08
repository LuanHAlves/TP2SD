package Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {

//    private DataInputStream in;
//    private DataOutputStream out;

    public static void main(String[] args) {

        String host = "127.0.0.1";
        int portaServidor = 7896;
        try (Socket socket = new Socket (host, portaServidor)) {
            PrintWriter output = new PrintWriter (socket.getOutputStream ( ), true);
            BufferedReader input = new BufferedReader (new InputStreamReader (socket.getInputStream ( )));
            Scanner scanner = new Scanner (System.in);

//            DataInputStream in= new DataInputStream(socket.getInputStream());
//            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            String mensagem = null;
            while (!"exit".equalsIgnoreCase (mensagem)) {
                mensagem = scanner.nextLine ( );
                output.println (mensagem);
                output.flush ( );
//                out.writeUTF(mensagem);
                System.out.println (input.readLine ());
            }
            scanner.close ( );
        } catch (IOException e) {
            e.printStackTrace ( );
        }
    }
}