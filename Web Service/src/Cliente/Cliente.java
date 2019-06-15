package Cliente;

import Gui.GUI;
import WebServer.FiguraGeometrica;

import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class Cliente {

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        URL url = new URL ("http://127.0.0.1:8080/figura?wsdl");
        QName qname = new QName ("http://WebServer/", "FiguraGeometricaImplService");
        Service ws = Service.create (url, qname);
        FiguraGeometrica figuraGeometrica = ws.getPort (FiguraGeometrica.class);

        GUI gui = new GUI ( );

        int numRequisicao;
        String resposta;

        numRequisicao = opcao ( );
        gui.setVisible (true);
        gui.setLocationRelativeTo (null);
        for (int i = 0; i < numRequisicao; i++) {
            Thread.sleep (1000);
            resposta = figuraGeometrica.figuraAleatoria ( );
            gui.setImgLabel (resposta);
            gui.setIterLabel (i + 1);
            System.out.println (resposta + " " + i);
        }
    }

    private static int opcao() {
        String opInt = JOptionPane.showInputDialog ("Número de Requisições: ");
        return Integer.parseInt (opInt);
    }

}