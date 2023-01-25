package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 23456;

    public void run(String[] args) {

        String inputMessage = String.join(" ", args);
        System.out.println("Client started!");

        try (
                Socket socket = new Socket(InetAddress.getByName(ADDRESS), PORT);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {


            output.writeUTF(inputMessage);
            ClientArgs clientArgs = ClientArgs.parse(inputMessage);

            String temp = "";
            if (!clientArgs.clientCommandRequest.equals("exit")) {
                temp = String.valueOf(clientArgs.clientCellIndex);
            }

            System.out.println("Sent: " + clientArgs.clientCommandRequest + " " + temp + " "
                    + clientArgs.clientValueToStore);


            String receivedMsg = input.readUTF();
            System.out.printf("Received:  %s", receivedMsg);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}