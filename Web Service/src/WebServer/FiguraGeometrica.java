package WebServer;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface FiguraGeometrica {

    @WebMethod
    String quadrado();

    @WebMethod
    String retangulo();

    @WebMethod
    String circulo();

    @WebMethod
    String triangulo();

    @WebMethod
    String figuraAleatoria();
}