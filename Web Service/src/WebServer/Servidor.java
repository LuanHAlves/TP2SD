package WebServer;

import javax.xml.ws.Endpoint;

public class Servidor {

    private static void runServico() {
        try {
            Endpoint.publish ("http://127.0.0.1:8080/figura", new FiguraGeometricaImpl ( ));
        } catch (Exception e) {
            System.err.println ("Erro!");
            e.printStackTrace ();
        }
    }

    public static void main(String[] args) {
        runServico ( );
    }
}