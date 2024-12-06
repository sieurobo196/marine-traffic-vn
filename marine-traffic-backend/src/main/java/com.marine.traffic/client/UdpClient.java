package com.marine.traffic.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {
    public static void main(String[] args) {
        sendBatchData();
    }

    public static void sendMsgTestServer() {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            String secret_key = "marine_traffic";
            byte[] sendData = secret_key.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
            clientSocket.send(sendPacket);
            System.out.println("Message sent to server: " + secret_key);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket); // Nhận dữ liệu từ server

            // Chuyển đổi dữ liệu nhận được thành chuỗi
            String receivedString = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Response from server: " + receivedString);
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendBatchData() {
//        0,3,2019-2-15 00:14:36,18,107.08898,10.389408333333334,0.0,219.1999969482422,291,0.0,0,574082072,,,,,,,,,,,,,
//        0,2,2019-2-15 00:14:36,17,107.12228,10.412515,0.0,135.5,79,0.0,0,574001870,,,,,,,,,,,,,
//        5,2,2019-2-15 00:14:36,17,107.03281666666666,10.286681666666667,0.6000000238418579,261.3999938964844,82,0.0,0,574002640,,,,,,,,,,,,,
//        5,2,2019-2-15 00:14:36,60,107.083755,10.391125,0.0,237.6999969482422,110,0.0,0,574335000,,,,,,,,,,,,,
//        format_package = secret_key;data1;data2
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");

            String response = "marine_traffic;0,3,2019-2-15 00:14:36,18,107.08898,10.389408333333334,0.0,219.1999969482422,291,0.0,0,574082072,,,,,,,,,,,,,;" +
                    "0,2,2019-2-15 00:14:36,17,107.12228,10.412515,0.0,135.5,79,0.0,0,574001870,,,,,,,,,,,,,;" +
                    "5,2,2019-2-15 00:14:36,17,107.03281666666666,10.286681666666667,0.6000000238418579,261.3999938964844,82,0.0,0,574002640,,,,,,,,,,,,,;" +
                    "5,2,2019-2-15 00:14:36,60,107.083755,10.391125,0.0,237.6999969482422,110,0.0,0,574335000,,,,,,,,,,,,,;";



            byte[] sendData = response.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
            clientSocket.send(sendPacket);
            System.out.println("Message sent to server: " + response);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket); // receive data from server

            // convert data
            String receivedString = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Response from server: " + receivedString);
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
