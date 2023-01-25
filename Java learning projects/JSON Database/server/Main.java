package server;

public class Main {


    public static void main(String[] args) {

        Database database = new Database(1000);
        Server server = new Server(database);

        System.out.println("Server started!");

        while (!server.isExit()) {
            server.run();
        }
    }
}
