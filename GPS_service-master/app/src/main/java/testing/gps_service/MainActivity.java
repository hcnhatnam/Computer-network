package testing.gps_service;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText tkEdit,IPEdit,portEdit;

    private RadioGroup radioGroup;
    private RadioButton radioButtonVitri, radioButtonTk;
    private EditText edNhap;
    private String  taikhoan,IP_Analyst,IP_gateway,taikhoanNhap;
    private int  port_Analyst,port_gateway;
    private EditText IPGateway, portGateway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tkEdit = (EditText) findViewById(R.id.tkEdit);
        IPEdit = (EditText) findViewById(R.id.IPAnalystEdit);
        portEdit = (EditText) findViewById(R.id.PortEdit);
        IPGateway = (EditText) findViewById(R.id.IPGWEdit);
        portGateway = (EditText) findViewById(R.id.PortGWEdit);

        radioGroup = (RadioGroup) findViewById(R.id.truycap);
        radioButtonTk = (RadioButton) findViewById(R.id.TimKiemRB);
        radioButtonVitri = (RadioButton) findViewById(R.id.ViTriRB);
        edNhap = (EditText) findViewById(R.id.SearchEdit);



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                doOnCheckedChangeListener(group,checkedId);
            }
        });

    }

    private void doOnCheckedChangeListener(RadioGroup group, int checkedId) {
        int checkedRadioId = group.getCheckedRadioButtonId();

        if(checkedRadioId == R.id.TimKiemRB)
            edNhap.setVisibility(View.VISIBLE);
        else edNhap.setVisibility(View.INVISIBLE);
    }

    public void xuLyMoVaGuiDuLieu(View view) {


        taikhoan = tkEdit.getText().toString();
        IP_Analyst = IPEdit.getText().toString();
        IP_gateway = IPGateway.getText().toString();
        boolean check=true;

        if(!isValidEmail(taikhoan)) {
            tkEdit.setError("Invalid Email");
            check=false;
        }

        if(!isValidIP(IP_Analyst)) {
            IPEdit.setError("Invalid IP");
            check=false;
        }

        if(!isValidIP(IP_gateway)) {
            IPGateway.setError("Invalid IP");
            check=false;
        }

        try {
            port_Analyst = Integer.parseInt(portEdit.getText().toString());
        } catch (NumberFormatException e) {
            portEdit.setError("Invalid Port");
            check=false;
        }
        try {
            port_gateway = Integer.parseInt(portGateway.getText().toString());
        } catch (NumberFormatException e) {
            portGateway.setError("Invalid Port");
            check=false;
        }

        TruyCap(check);
        // Intent i = new Intent(MainActivity.this,ManHinh2Activity.class);


        // startActivity(i);
    }

    private void TruyCap(Boolean check){
        int checkRadioId = radioGroup.getCheckedRadioButtonId();

        if (checkRadioId == R.id.ViTriRB)
            if (check)
                doOnCheckedViTri();
            else
                showAlertDialog();

        else {
            if (!isValidEmail(edNhap.getText().toString())) {
                edNhap.setError("Invalid Email");
                check = false;
            }
            if (check) {
                taikhoanNhap = edNhap.getText().toString();
                doOnCheckedTimKiem();
            }
            else
                showAlertDialog();
        }

    }

    private void doOnCheckedViTri() {
        Intent i = new Intent(MainActivity.this,ViTriActivity.class);
        i.putExtra("taikhoan",taikhoan);
        i.putExtra("IP",IP_gateway);
        i.putExtra("port",""+port_gateway);
        startActivity(i);

    }
    private boolean isValidIP(String IP) {
        String IPADDRESS_PATTERN =
                "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
        Matcher matcher = pattern.matcher(IP);
        return matcher.matches();
    }


    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }




    private void doOnCheckedTimKiem() {
        Intent i = new Intent(MainActivity.this,TimKiemActivity.class);
        i.putExtra("taikhoan",taikhoan);
        i.putExtra("taikhoanNhap",taikhoanNhap);
        i.putExtra("IP_Analyst",IP_Analyst);
        i.putExtra("port_Analyst",port_Analyst);
        startActivity(i);
    }

    Dialog dialog;

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn cần điền thông tin chính xác");
        builder.setCancelable(true);

        builder.setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();



    }
}
