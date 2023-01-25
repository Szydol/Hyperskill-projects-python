package client;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class ClientArgs {
    private ClientArgs() {

    }
    @Parameter(names = "-t")
    String clientCommandRequest = "";
    @Parameter(names = "-i")
    int clientCellIndex = 0;
    @Parameter(names = "-m")
    String clientValueToStore = "";

    public static ClientArgs parse(String inputString) {

        ClientArgs clientArgs = new ClientArgs();
        String[] argValues = inputString.split(" ", Math.min(6, inputString.split(" ").length));

        JCommander.newBuilder()
                .addObject(clientArgs)
                .build()
                .parse(argValues);

        return clientArgs;
    }
}
