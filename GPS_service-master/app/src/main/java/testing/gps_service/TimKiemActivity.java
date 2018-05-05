package testing.gps_service;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class TimKiemActivity extends AppCompatActivity implements OnMapReadyCallback{



    private GoogleMap mMap;
    private String taikhoan,taikhoanNhap,IP_Analyst;
    private int port_Analyst;
    private String[] Location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);

        ///addControls();

        Intent intent = getIntent();
        taikhoan = intent.getStringExtra("taikhoan");
        taikhoanNhap = intent.getStringExtra("taikhoanNhap");
        IP_Analyst=intent.getStringExtra("IP_Analyst");
        port_Analyst=intent.getIntExtra("port_Analyst",1234);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.myMap);
        mapFragment.getMapAsync(this);




        AsyncTask<Void, String, Void> async_cient;
        async_cient = new AsyncTask<Void, String, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                try {
                    String sentence;
                    String modifiedSentence;
                    Socket clientSocket = new Socket(IP_Analyst, port_Analyst);
                    DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                    BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    sentence=taikhoanNhap;
                    outToServer.writeBytes(sentence + '\n');
                    modifiedSentence = inFromServer.readLine();
                    publishProgress(modifiedSentence);
                    clientSocket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;

            }
            protected void onProgressUpdate(String... values) {
                super.onProgressUpdate(values);
                Location=values[0].split("\\-");
                onMapReady(mMap);
                //TextView textView = (TextView) findViewById(R.id.textView);
                //textView.append(values[0]);
            }
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
            }
        };

        if (Build.VERSION.SDK_INT >= 14)
            async_cient.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            async_cient.execute();






         mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.myMap);
        mapFragment.getMapAsync(this);

    }

    private final String LocationFail="9999|9999";
    @Override
    public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
        //  ArrayList<LatLng> ltl = new ArrayList<>();

        if(Location!=null) {
            if (Location[0].equals(LocationFail))
                Toast.makeText(TimKiemActivity.this, "Không tìm thấy dữ liệu của " + taikhoanNhap, Toast.LENGTH_SHORT).show();
            else {
                for (int i=0;i<3;i++){
                    String[] temp = Location[i].split("\\|");
                    double KinhDo = Double.parseDouble(temp[0]);
                    double ViDo = Double.parseDouble(temp[1]);

                        LatLng newLocation = new LatLng(KinhDo, ViDo);
                        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").anchor(0.5f,0.5f));
                        mMap.addMarker(new MarkerOptions().position(newLocation).title("Level"+i+1).anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.defaultMarker(i*30)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation,8));
                    if(i>=Location.length-1) break;
                }

        }
        }
    }


}
