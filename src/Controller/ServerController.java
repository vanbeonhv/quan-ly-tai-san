package Controller;

import Model.Event;
import Model.TaiSan;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServerController {

    private Connection con;
    private DatagramSocket myServer;
    private final int serverPort = 5555;
    private DatagramPacket receivePacket = null;

    public ServerController() {
        getDBConnection("quan-ly-tai-san", "root", "");
        openServer(serverPort);
        System.out.println("UDP server is running...");
        while (true) {
            listenning();
        }
    }

    private void getDBConnection(String dbName, String username, String password) {
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/" + dbName + "?serverTimezone=UTC";
        String dbClass = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(dbClass);
            con = DriverManager.getConnection(dbUrl, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openServer(int portNumber) {
        try {
            myServer = new DatagramSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listenning() {
        Event event = receiveData();
        Object result = "false";

        switch (event.getAction()) {
            case "add":
                addTaiSan(event.getTaisan());
                result = "true";
                break;
            default:
                System.out.println("Error: action khong hop le: " + event.getAction());
                break;
        }

        sendData(result);
    }

    private Event receiveData() {
        Event event = null;
        try {
            byte[] receiveData = new byte[1024];
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            myServer.receive(receivePacket);
            ByteArrayInputStream bais = new ByteArrayInputStream(receiveData);
            ObjectInputStream ois = new ObjectInputStream(bais);
            event = (Event) ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }

    private boolean addTaiSan(TaiSan taisan) {
        String query = "INSERT INTO `tai_san`(`ten_tai_san`, `loai_tai_san`, `vi_tri_phong_hien_tai`, `gia_tri`)"
                + "VALUES ('"
                + taisan.getTenTaiSan()
                + "','"
                + taisan.getLoaiTaiSan()
                + "','"
                + taisan.getViTriPhong()
                + "',"
                + taisan.getGiaTri()
                + ")";
        return executeUpdate(query);
    }

    private boolean executeQuery(String query) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean executeUpdate(String query) {
        try {
            Statement stmt = con.createStatement();
            int rowsAffected = stmt.executeUpdate(query);
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void sendData(Object result) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(result);
            oos.flush();

            InetAddress IPAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            byte[] sendData = baos.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(sendData,
                    sendData.length, IPAddress, clientPort);
            myServer.send(sendPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
