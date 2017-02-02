/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.io.InputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author juchafernandez
 */
public class Calculadora {

    /**
     * @param args the command line arguments
     */
    //declaramos las variables  
    static int operando1, operando2, operacion, respuesta;

    //con el método Scanner le decimos que lea los datos entrantes y los igualamos a los operandos
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        operando1 = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el primer operando: "));

        operando2 = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el segundo operando: "));
        //aquí creamos un condicional para que nos avise de un error cuando no metamos una de las operaciones disponibles
        do {

            operacion = Integer.parseInt(JOptionPane.showInputDialog("Que desea hacer? (0 Sumar, 1 Restar,2 Multiplicar,3 Dividir) "));
            if (operacion < 0 || operacion > 3) {
                JOptionPane.showMessageDialog(null, "Operando no válido, introduzca otro");
            }
        } while (operacion < 0 || operacion > 3);
//con el connect nos conectamos al server en local por el puerto 6000
        Socket client = new Socket();
        InetSocketAddress addr = new InetSocketAddress("localhost", 6000);
        System.out.println("Conectando al servidor...");
        client.connect(addr);
        System.out.println("Conexión establecida");
        OutputStream os = client.getOutputStream();
        //enviamos el dato 
        os.write(operando1);
        os.write(operando2);
        os.write(operacion);
        InputStream ic = client.getInputStream();
        //leemos la respuesta que recibimos y la mostramos por pantalla, luego cerramos el cliente   
        respuesta = ic.read();
        System.out.println("Datos enviados");
        JOptionPane.showMessageDialog(null, "Resultado=" + respuesta);
        System.out.println("Cerrando conexión al servidor");
        client.close();
    }
}
