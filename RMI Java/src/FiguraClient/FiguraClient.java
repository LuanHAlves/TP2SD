package FiguraClient;

import FiguraGeometrica.FiguraGeometrica;

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

    public static void main(String[] args) throws InterruptedException {

        try {

            FiguraClient figuraClient = new FiguraClient (666);
            Registry registry = LocateRegistry.getRegistry ("localhost", figuraClient.getPortaServidor ( ));
            FiguraGeometrica figura = (FiguraGeometrica) registry.lookup ("FiguraGeometrica");

            for (int i = 0; i < 10; i++) {
                String resposta = figura.figuraAleatoria ();
                System.out.println (resposta);
                Thread.sleep (1000);
            }


        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace ( );
        }
    }
}