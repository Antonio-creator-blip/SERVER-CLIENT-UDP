package com.company;
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String args[]) throws Exception{
        DatagramSocket serverSocket = new DatagramSocket(6789);
        boolean attivo = true;
        byte[] bufferIN = new byte[1024];
        byte[] bufferOUT = new byte[1024];

        System.out.println("SERVER avviato...");
        while(attivo){
            DatagramPacket receivePacket = new DatagramPacket(bufferIN,bufferIN.length);
            serverSocket.receive(receivePacket);
            String ricevuto = new String(receivePacket.getData());
            int numCaratteri = receivePacket.getLength();
            ricevuto=ricevuto.substring(0,numCaratteri);
            System.out.println("Ricevuto: " + ricevuto);
            InetAddress IPClient = receivePacket.getAddress();
            int portaClient = receivePacket.getPort();
            String daSpedire = ricevuto.toUpperCase();
            bufferOUT = daSpedire.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(bufferOUT, bufferOUT.length, IPClient,portaClient);
            serverSocket.send(sendPacket);

            if(ricevuto.equals("fine")){
                System.out.println("SERVER IN CHIUSURA");
                attivo=false;
            }
        }
        serverSocket.close();
    }
}
