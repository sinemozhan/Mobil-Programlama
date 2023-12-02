package com.example.vize;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.telephony.SmsManager;
import android.widget.Toast;
import android.Manifest;


public class SMS extends AppCompatActivity {

    private  static int PERMISSION_REQUEST_SMS =1;
    EditText editTextTelefon,editTextMesaj;
    Button gonderButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        gonderButton=findViewById(R.id.gonderButton);
        editTextTelefon=findViewById(R.id.editTextTelefon);
        editTextMesaj=findViewById(R.id.editTextMesaj);

        gonderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ContextCompat.checkSelfPermission(SMS.this, Manifest.permission.SEND_SMS)
                    !=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(SMS.this, new String[]
                            {Manifest.permission.SEND_SMS},PERMISSION_REQUEST_SMS);
            }else{
                    sendSMS();
                }
            }
        });
    }
    private void sendSMS(){
        String telefonNumarasi=editTextTelefon.getText().toString();
        String mesaj=editTextMesaj.getText().toString();

        try{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(telefonNumarasi,null,mesaj,null,null);
            showToast("Sms gönderildi");
        } catch (Exception e){
            e.printStackTrace();
            showToast("Sms gönderme hatası");
        }
    }

    private void showToast(String mesaj){
        Toast.makeText(this,mesaj,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == PERMISSION_REQUEST_SMS){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                sendSMS();
            }else{

            }
        }
    }
}