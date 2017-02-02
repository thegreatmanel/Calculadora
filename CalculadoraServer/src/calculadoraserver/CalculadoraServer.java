/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadoraserver;

import java.io.IOException;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 *
 * @author juchafernandez
 */
public class CalculadoraServer extends Thread {

    public static void main(String[] args) throws IOException {
        //esperamos peticiones y aceptamos la del cliente
        ServerSocket server = new ServerSocket();
        SocketAddress addr = new InetSocketAddress("localhost", 6000);
        server.bind(addr);
        System.out.println("Servidor preparado para recibir");
        while (true) {
            Socket conexion = server.accept();
            new Cliente(conexion).start();
        }

    }

}
