package FiguraGeometrica;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FiguraGeometrica extends Remote {

    String quadrado() throws RemoteException;

    String retangulo() throws RemoteException;

    String circulo() throws RemoteException;

    String triangulo() throws RemoteException;

    String figuraAleatoria() throws RemoteException;
} 