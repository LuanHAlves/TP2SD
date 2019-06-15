package Cliente;

import Gui.GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {

//    private DataInputStream in;
//    private DataOutputStream out;

    private static void runCliente(String host, int portaServidor) {

        GUI gui = new GUI ( );
        gui.setVisible (true);
        gui.setLocationRelativeTo (null);

        try {

            Socket socket = new Socket (host, portaServidor);
            PrintWriter output = new PrintWriter (socket.getOutputStream ( ), true);
            BufferedReader input = new BufferedReader (new InputStreamReader (socket.getInputStream ( )));
            Scanner scanner = new Scanner (System.in);

//            DataInputStream in= new DataInputStream(socket.getInputStream());
//            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            String mensagem = "x";
            String fig;
            int iteracoes = 50;

            for (int i = 0; i < iteracoes; i++) {
                output.println (mensagem);
                output.flush ( );
                fig = input.readLine ( );
                System.out.println (fig);
                gui.setImgLabel (fig);
                gui.setIterLabel (i + 1);
                Thread.sleep (1000);
            }

            scanner.close ( );
        } catch (IOException | InterruptedException e) {
            e.printStackTrace ( );
        }
    }

    public static void main(String[] args) {
        String host = "127.0.0.1";
        int portaServidor = 7896;

        runCliente (host, portaServidor);
    }

}