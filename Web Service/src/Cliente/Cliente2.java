package Cliente;

import Gui.GUI;
import WebServer.FiguraGeometrica;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class Cliente2 {

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        URL url = new URL ("http://127.0.0.1:8080/figura?wsdl");
        QName qname = new QName ("http://WebServer/", "FiguraGeometricaImplService");
        Service ws = Service.create (url, qname);
        FiguraGeometrica figuraGeometrica = ws.getPort (FiguraGeometrica.class);

        GUI gui = new GUI ( );

        int numRequisicao;
        String resposta;

        numRequisicao = gui.setOpcao ( );
        gui.setVisible (true);
        gui.setLocationRelativeTo (null);
        for (int i = 0; i < numRequisicao; i++) {

            Thread.sleep (aleatorio (500, 1000));
            resposta = figuraGeometrica.figuraAleatoria ( );
            gui.setImgLabel (resposta);
            gui.setIterLabel (i + 1);
            System.out.println (resposta + " " + i);

        }
    }

    private static int aleatorio(int min, int max) {
        Random generator = new Random ( );
        return generator.nextInt((max - min) + 1) + min;
    }
    
}