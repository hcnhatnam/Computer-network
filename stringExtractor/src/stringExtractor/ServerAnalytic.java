package stringExtractor;

import java.io.*;
import java.net.*;
import java.util.List;

/**
 * @document: ServerProgram
 * @author: Pham Van Linh
 */
public class ServerAnalytic {
    
    public static void main(String[] args) throws IOException {
    	
    	Thread mThread = new Thread(new MQTTRecieve());
		mThread.start();
		
        ServerSocket listener = null;

        System.out.println("Server ANALYTIC is waiting ...");

        int clientNumber = 0;

        try {
            listener = new ServerSocket(GUI.port);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }

        try {
            while (true) {
                Socket socketOfServer = listener.accept();
                new ServiceThread(socketOfServer, clientNumber++).start();
            }
        } finally {
            listener.close();
        }
    }

    private static void log(String mes) {
        System.out.println(mes);
    }

    private static class ServiceThread extends Thread {

        private int clientNumber;

        private Socket socketOfServer;

        public ServiceThread(Socket sockerOfSocket, int clientNumber) {
            this.clientNumber = clientNumber;
            this.socketOfServer = sockerOfSocket;

            log("New connection with client# " + this.clientNumber + " at " + socketOfServer);

        }

        @Override
        public void run() {
            try {
            	String clientSentence,capitalizedSentence;
            	BufferedReader inFromClient =
                        new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
                DataOutputStream outToClient = new DataOutputStream(socketOfServer.getOutputStream());
                clientSentence = inFromClient.readLine();
                List<GPS> lst=getData.getDatabaseData(clientSentence);
                
               ListGPS list=new ListGPS(lst);
                capitalizedSentence = list.analysisGPS()+ '\n';
                outToClient.writeBytes(capitalizedSentence);
//
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}

