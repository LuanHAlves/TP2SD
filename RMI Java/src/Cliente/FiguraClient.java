package Cliente;

import FiguraGeometrica.FiguraGeometrica;
import Gui.GUI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

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

        try {

            FiguraClient figuraClient = new FiguraClient (portaServidor);
            Registry registry = LocateRegistry.getRegistry ("localhost", figuraClient.getPortaServidor ( ));
            FiguraGeometrica figura = (FiguraGeometrica) registry.lookup ("FiguraGeometrica");

            String resposta;
            int iteracoes = gui.setOpcao ( );
            gui.setVisible (true);
            gui.setLocationRelativeTo (null);

            for (int i = 0; i < iteracoes; i++) {
                Thread.sleep (aleatorio (500, 1000));
                resposta = figura.figuraAleatoria ( );
                gui.setImgLabel (resposta);
                gui.setIterLabel (i + 1);
                System.out.println (resposta);
            }


        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace ( );
        }
    }

    private static int aleatorio(int min, int max) {
        Random generator = new Random ( );
        return generator.nextInt ((max - min) + 1) + min;
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