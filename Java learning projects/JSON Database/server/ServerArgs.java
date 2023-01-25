package server;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class ServerArgs {
    private ServerArgs() {

    }
    @Parameter(names = "-t")
    String serverCommandRequest = "";
    @Parameter(names = "-i")
    int serverCellIndex = 0;
    @Parameter(names = "-m")
    String serverValueToStore = "";

    public static ServerArgs parse(String receivedString) {

        ServerArgs serverArgs = new ServerArgs();
        String[] argValues = receivedString.split(" ", Math.min(6, receivedString.split(" ").length));

        JCommander.newBuilder()
                .addObject(serverArgs)
                .build()
                .parse(argValues);

        return serverArgs;
    }
}
