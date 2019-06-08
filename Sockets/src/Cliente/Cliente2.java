package Cliente;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente2 {

    private DataInputStream in;
    private DataOutputStream out;

    public static void main(String[] args) {

        String host = "127.0.0.1";
        int portaServidor = 666;
        try (Socket socket = new Socket (host, portaServidor)) {
            PrintWriter output = new PrintWriter (socket.getOutputStream ( ), true);
            BufferedReader input = new BufferedReader (new InputStreamReader (socket.getInputStream ( )));
            Scanner scanner = new Scanner (System.in);

            DataInputStream in= new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            String mensagem = null;
            while (!"exit".equalsIgnoreCase (mensagem)) {
                mensagem = scanner.nextLine ( );
//                output.println (mensasgem);
//                output.flush ( );
                out.writeUTF(mensagem);
                System.out.println (in.readUTF ( ));
            }
            scanner.close ( );
        } catch (IOException e) {
            e.printStackTrace ( );
        }
    }
}