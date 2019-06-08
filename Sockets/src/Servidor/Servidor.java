package Servidor;

import FigureaGeometrica.FiguraGeometricaImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {


    private static class ClienteConHandler implements Runnable {

        private final Socket clienteSocket;

        ClienteConHandler(Socket socket) {
            this.clienteSocket = socket;
        }

        @Override
        public void run() {
            PrintWriter output = null;
            BufferedReader input = null;
            FiguraGeometricaImpl figura = new FiguraGeometricaImpl ( );

            try {

                output = new PrintWriter (clienteSocket.getOutputStream ( ), true);
                input = new BufferedReader (new InputStreamReader (clienteSocket.getInputStream ( )));

//                DataInputStream in = new DataInputStream (clienteSocket.getInputStream ( ));
//                DataOutputStream out = new DataOutputStream (clienteSocket.getOutputStream ( ));

                String msgCliente;
                while ((msgCliente = input.readLine ( )) != null) {
//                while ((msgCliente = in.readUTF ( )) != null) {

//                    msgCliente = in.readUTF ();
                    if (msgCliente.equals ("x")) {
                        msgCliente = figura.figuraAleatoria ( );
                        System.out.printf ("Msg Enviado pelo Servidor: %s\n", msgCliente);
                        output.println (msgCliente);
//                        out.writeUTF (msgCliente);
                    } else {
                        msgCliente = "Requisicao invalida!!!";
                        System.err.printf ("Msg Enviado pelo Servidor: %s\n", msgCliente);
                        output.println (msgCliente);
//                        out.writeUTF (msgCliente);
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


    public static void main(String[] args) {

        ServerSocket servidor = null;
        try {

            servidor = new ServerSocket (7896);
            servidor.setReuseAddress (true);

            /*
             *  Thread principal apenas para aceitar novas coneções
             **/
            while (true) {
                Socket cliente = servidor.accept ( );
                System.out.println ("Novo cliente conectado " + cliente.getInetAddress ( ).getHostAddress ( ));
                ClienteConHandler clienteSock = new ClienteConHandler (cliente);

                /*
                 *  Thread para cada cliente
                 **/
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

}