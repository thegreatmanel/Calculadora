/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadoraserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juchafernandez
 */
public class Cliente extends Thread {

    //declaramos
    private static int operando1, operando2, operacion, respuesta;
    private Socket cliente;

    public Cliente(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try {
            //leemos la cadena de entrada 
            InputStream is = cliente.getInputStream();
            operando1 = is.read();
            operando2 = is.read();
            operacion = is.read();
            System.out.println("El operando 1 es: " + operando1);
            System.out.println("El operando 2 es: " + operando2);
            //clasificamos por los posibles casos de operación y hacemos el cáculo(0 suma, 1 resta, 2 multiplica y 3 divide)
            //y luego detenemos el proceso con BREAK
            switch (operacion) {
                case 0:
                    respuesta = operando1 + operando2;
                    System.out.println("La operación es suma");
                    System.out.println("El resultado es: " + operando1 + " + "
                            + operando2 + " = " + (operando1 + operando2));
                    break;
                case 1:
                    respuesta = operando1 - operando2;
                    System.out.println("La operación es resta");
                    System.out.println("El resultado es: " + operando1 + " - "
                            + operando2 + " = " + (operando1 - operando2));
                    break;
                case 2:
                    respuesta = (operando1 * operando2);
                    System.out.println("La operación es multiplicación");
                    System.out.println("El resultado es: " + operando1 + " * "
                            + operando2 + " = " + (respuesta));
                    break;
                default:
                    respuesta = (operando1 / operando2);
                    System.out.println("La operación es división");
                    System.out.println("El resultado es: " + operando1 + " / "
                            + operando2 + " = " + (respuesta));
            }
            //mandamos una respuesta con write en una cadena de caracteres saliente
            OutputStream oc = cliente.getOutputStream();
            oc.write(respuesta);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
