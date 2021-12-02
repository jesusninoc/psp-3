package multicast;

import java.net.*;
import java.io.*;

public class MulticastS{
    public MulticastS() {

    }

    {
            int port = 2020;
            //224.0.0.1 ... 224.0.0.255
            InetAddress multicastGroupIP = null;
            try {
                multicastGroupIP = InetAddress.getByName("224.0.0.5");
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            System.out.println(multicastGroupIP);
            MulticastSocket socket = null;
            try {
                socket = new MulticastSocket(port);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            try {
                socket.joinGroup(multicastGroupIP);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            byte[] m = "La compra del coche se ha realizado".getBytes();
            DatagramPacket send = new DatagramPacket(m, m.length, multicastGroupIP, port);
            try {
                socket.send(send);
            } catch (IOException e) {
                e.printStackTrace();
            }

            byte[] bufer = new byte[1000];
            String linea;

            while (true)
            {
                DatagramPacket receive = new DatagramPacket(bufer, bufer.length);
                try {
                    socket.receive(receive);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                linea = new String(receive.getData(), 0, receive.getLength());
                System.out.println("Receive: " + linea);

                //Break if "Bye"
                if (linea.equals("Bye"))
                {
                    socket.close();
                    break;
                }
            }
            try {
                socket.leaveGroup(multicastGroupIP);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
