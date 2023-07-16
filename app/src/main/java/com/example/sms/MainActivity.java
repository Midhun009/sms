package com.example.sms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSION_REQUEST_RECEIVE_SMS = 0;
    TextView txtnumber, txtmessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED)
        {

            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.RECEIVE_SMS))
            {
                //Do not do anything
            }
            else
            {
                ActivityCompat.requestPermissions(MainActivity.this,new
                        String[]{Manifest.permission.RECEIVE_SMS},MY_PERMISSION_REQUEST_RECEIVE_SMS);
            }
        }
        Bundle b = getIntent().getBundleExtra("data");
        txtnumber = findViewById(R.id.txtnumber);
        txtmessage = findViewById(R.id.txtmessage);
        if(b!=null){
            String number=b.getString("number");
            String content=b.getString("content");
            txtnumber.setText(number);
            txtmessage.setText(content);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_RECEIVE_SMS: {
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Thank you for permitting", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Cant do anything until you permit me", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
