package testing.gps_service;

/**
 * Created by Nam on 10/16/2017.
 */
import java.lang.reflect.Array;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import java.io.*;
import java.net.*;
import java.util.List;

public class UDPClient {

    private String IP;
    private AsyncTask<Void, Void, Void> async_cient;
    public String Message;
    private int port;

    UDPClient(String IP,int port) {
        this.IP = IP;
        this.port = port;
    }

    @SuppressLint("NewApi")
    public void NachrichtSenden()//type=null if UDP //type=email ifTCP
    {
        async_cient = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                DatagramSocket ds = null;

                try {
                    InetAddress addr = InetAddress.getByName(IP);
                    ds = new DatagramSocket(port);
                    DatagramPacket dp;
                    dp = new DatagramPacket(Message.getBytes(), Message.getBytes().length, addr, port);
                    ds.setBroadcast(true);
                    ds.send(dp);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (ds != null) {
                        ds.close();
                    }
                }
                return null;

            }

            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
            }
        };

        if (Build.VERSION.SDK_INT >= 11)
            async_cient.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            async_cient.execute();
    }


}
