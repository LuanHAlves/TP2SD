package FiguraClient;

import FiguraGeometrica.FiguraGeometrica;
import Gui.GUI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class FiguraClient {

    private int portaServidor;

    private FiguraClient(int portaServidor) {
        this.portaServidor = portaServidor;
    }

    private int getPortaServidor() {
        return portaServidor;
    }

    private static void runCliente(int portaServidor) throws InterruptedException {

        GUI gui = new GUI ( );
        gui.setVisible (true);
        gui.setLocationRelativeTo (null);

        try {

            FiguraClient figuraClient = new FiguraClient (portaServidor);
            Registry registry = LocateRegistry.getRegistry ("localhost", figuraClient.getPortaServidor ( ));
            FiguraGeometrica figura = (FiguraGeometrica) registry.lookup ("FiguraGeometrica");

            int iteracoes = 10;
            for (int i = 0; i < iteracoes; i++) {
                String resposta = figura.figuraAleatoria ( );
                gui.setImgLabel (resposta);
                gui.setIterLabel (i+1);
                System.out.println (resposta);
                Thread.sleep (1000);
            }


        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace ( );
        }
    }

    public static void main(String[] args) {
        int porta = 666;
        try {
            runCliente (porta);
        } catch (InterruptedException e) {
            e.printStackTrace ( );
        }
    }
}