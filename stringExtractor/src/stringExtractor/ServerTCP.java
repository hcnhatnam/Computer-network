package stringExtractor;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ServerTCP {
    public static void main(String argv[]) throws Exception {
        String clientSentence;
        String capitalizedSentence;
        ServerSocket welcomeSocket = new ServerSocket(9876);

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();
            List<GPS> lst=getData.getDatabaseData(clientSentence);
            ListGPS list=new ListGPS(lst);
            capitalizedSentence = list.analysisGPS()+ '\n';
            outToClient.writeBytes(capitalizedSentence);
        }
    }


}