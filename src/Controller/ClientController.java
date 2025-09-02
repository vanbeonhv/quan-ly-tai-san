package Controller;

import Model.Event;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientController {

    private int serverPort = 5555;
    private int clientPort = 6666;
    private String serverHost = "localhost";
    private DatagramSocket myClient;

    public ClientController() {
    }

    public void openConnection() {
        try {
            myClient = new DatagramSocket(clientPort);
            myClient.setSoTimeout(60* 1000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            myClient.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sendData(Event event) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(event);
            oos.flush();

            InetAddress IPAddress
                    = InetAddress.getByName(serverHost).getByName(serverHost);
            byte[] sendData = baos.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(sendData,
                    sendData.length, IPAddress, serverPort);
            myClient.send(sendPacket);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String receiveData() {
        String result = "";
        try {
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            myClient.receive(receivePacket);
            ByteArrayInputStream bais = new ByteArrayInputStream(receiveData);
            ObjectInputStream ois = new ObjectInputStream(bais);
            result = (String) ois.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public java.util.List<Model.TaiSan> receiveDataList() {
        java.util.List<Model.TaiSan> result = new java.util.ArrayList<>();
        try {
            byte[] receiveData = new byte[4096];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            myClient.receive(receivePacket);
            ByteArrayInputStream bais = new ByteArrayInputStream(receiveData);
            ObjectInputStream ois = new ObjectInputStream(bais);
            result = (java.util.List<Model.TaiSan>) ois.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
