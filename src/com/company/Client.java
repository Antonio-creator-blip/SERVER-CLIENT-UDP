package com.company;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String args[]) throws Exception{
        int portaServer = 6789;
        InetAddress IPServer = InetAddress.getByName("localhost");

        byte[] bufferIN = new byte[1024];
        byte[] bufferOUT = new byte[1024];
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        DatagramSocket clientSocket = new DatagramSocket();
        System.out.println("Client pronto - inserisci un dato da inviare: ");
        String daSpedire = input.readLine();
        bufferOUT = daSpedire.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(bufferOUT, bufferOUT.length, IPServer,portaServer);
        clientSocket.send(sendPacket);
        DatagramPacket receivePacket = new DatagramPacket(bufferIN, bufferIN.length);
        clientSocket.receive(receivePacket);
        String ricevuto = new String(receivePacket.getData());
        int numCaratteri = receivePacket.getLength();
        ricevuto=ricevuto.substring(0,numCaratteri);
        System.out.println("dal Server : " + ricevuto);

        clientSocket.close();
    }
}
