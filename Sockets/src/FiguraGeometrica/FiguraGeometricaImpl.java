package FiguraGeometrica;

import java.util.Random;

public class FiguraGeometricaImpl implements FiguraGeometrica {

    @Override
    public String quadrado() {
        return "quadrado";
    }

    @Override
    public String retangulo() {
        return "retangulo";
    }

    @Override
    public String circulo() {
        return "circulo";
    }

    @Override
    public String triangulo() {
        return "triangulo";
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
}
