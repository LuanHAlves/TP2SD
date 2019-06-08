package Servidor;

import FigureaGeometrica.FiguraGeometricaImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {

        ServerSocket servidor = null;
        try {

            servidor = new ServerSocket (666);
            servidor.setReuseAddress (true);

            /*
             *  Thread principal apenas para aceitar novas coneções
             **/
            while (true) {
                Socket cliente = servidor.accept ( );
                System.out.println ("Novo cliente conectado " + cliente.getInetAddress ( ).getHostAddress ( ));
                ClientHandler clienteSock = new ClientHandler (cliente);

                new Thread (clienteSock).start ( );
            }

        } catch (IOException e) {
            e.printStackTrace ( );
        } finally {

            if (servidor != null) {
                try {
                    servidor.close ( );
                } catch (IOException e) {
                    e.printStackTrace ( );
                }
            }

        }
    }


    private static class ClientHandler implements Runnable {

        private final Socket clienteSocket;

        private FiguraGeometricaImpl figura = new FiguraGeometricaImpl ( );

        ClientHandler(Socket socket) {
            this.clienteSocket = socket;
        }

        @Override
        public void run() {
            PrintWriter output = null;
            BufferedReader input = null;
            try {

                output = new PrintWriter (clienteSocket.getOutputStream ( ), true);
                input = new BufferedReader (new InputStreamReader (clienteSocket.getInputStream ( )));
                String msgCliente;
                while ((msgCliente = input.readLine ( )) != null) {

                    if (msgCliente.equals ("x")) {
                        msgCliente = figura.figuraAleatoria ( );
                        System.out.printf ("Msg Enviado pelo Servidor: %s\n", msgCliente);
                        output.println (msgCliente);
                    } else {
                        msgCliente = "Requisicao invalida!!!";
                        System.err.printf ("Msg Enviado pelo Servidor: %s\n", msgCliente);
                        output.println (msgCliente);
                    }

                }

            } catch (IOException e) {
                e.printStackTrace ( );
            } finally {
                try {
                    if (output != null) {
                        output.close ( );
                    }
                    if (input != null)
                        input.close ( );
                    clienteSocket.close ( );
                } catch (IOException e) {
                    e.printStackTrace ( );
                }
            }
        }
    }
}