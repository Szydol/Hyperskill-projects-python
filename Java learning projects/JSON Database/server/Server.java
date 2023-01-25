package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public Server(Database database) {
        this.database = database;
    }

    private final static String COMMANDS = "get, set, delete, exit";
    private final Database database;
    private boolean exit = false;
    public boolean isExit() {
        return exit;
    }
    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 23456;



    public void run() {

        String receivedString;


        try (
                ServerSocket server = new ServerSocket(PORT, 50, InetAddress.getByName(ADDRESS));
                Socket socket = server.accept();
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {

            receivedString = input.readUTF();

            if (receivedString.equals("")) {
                System.out.println("Received message is empty");
            } else {
                System.out.println("Received: " + receivedString);
            }

            ServerArgs serverArgs = ServerArgs.parse(receivedString);


            String outputMsg = executeCommand(serverArgs.serverCommandRequest, serverArgs.serverCellIndex, serverArgs.serverValueToStore);

            output.writeUTF(outputMsg);
            System.out.printf("Sent: " + outputMsg + "\n" + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String executeCommand(String commandRequest, int cellIndex, String valueToStore) {

        String outputMessage = "";

        if (!COMMANDS.contains(commandRequest) || cellIndex > 1000) {
            outputMessage = "ERROR";
        } else {

            switch (commandRequest) {
                case "exit":
                    outputMessage = "OK";
                    exit = true;
                    break;

                case "set":
                    boolean result = database.set(cellIndex, valueToStore);
                    outputMessage = result ? "OK" : "ERROR";
                    break;

                case "get":
                    String value = database.get(cellIndex);
                    boolean isValueNull = null == value;
                    outputMessage = isValueNull ? "ERROR" : value;
                    break;

                case "delete":
                    database.delete(cellIndex);
                    outputMessage = "OK";
                    break;
            }
        }

        return outputMessage;
    }
}
