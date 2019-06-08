package FiguraServer;

import FiguraGeometrica.*;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class FiguraServer implements FiguraGeometrica {

    private int porta;

    private FiguraServer(int porta) {
        this.porta = porta;
    }

    @Override
    public String quadrado() {
        return "QUADRADO";
    }

    @Override
    public String retangulo() {
        return "RETANGULO";
    }

    @Override
    public String circulo() {
        return "CIRCULO";
    }

    @Override
    public String triangulo() {
        return "TRIANGULO";
    }

    @Override
    public String figuraAleatoria() {
        Random generator = new Random ( );
        int escolha = generator.nextInt (4);
        String figura;

        switch (escolha) {
            case 0:
                figura = quadrado ( );
                break;
            case 1:
                figura = retangulo ( );
                break;
            case 2:
                figura = circulo ( );
                break;
            case 3:
                figura = triangulo ( );
                break;
            default:
                throw new IllegalStateException ("Unexpected value: " + escolha);
        }
        return figura;
    }

    public static void main(String[] args) {

        FiguraServer figServer = new FiguraServer (666);

        try {


            FiguraGeometrica figura = (FiguraGeometrica) UnicastRemoteObject.exportObject (figServer, 0);
            Registry registry = LocateRegistry.createRegistry (figServer.porta);
            registry.bind ("FiguraGeometrica", figura);
            System.out.println ("Servidor Pronto :) ");


        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace ( );
        }
    }
}