package com.example.vize;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class Donusturucu extends AppCompatActivity {
    EditText editTextNumberDecimal;
    Spinner spinnerdecimal;
    Button buttonsonucdecimal;
    TextView textViewdecimal;

    EditText megaByteEditText;
    Spinner donusturmeTipiByteSpinner;
    Button donusturmeButtonByte;
    TextView sonucTextViewByte;

    EditText celsiusEditText;
    Button donusturmeButtonsicaklik;
    TextView sonucTextView;

    RadioButton fahrenheitRadioButton;
    RadioButton kelvinRadioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donusturucu);

        editTextNumberDecimal = findViewById(R.id.editTextNumberDecimal);
        spinnerdecimal = findViewById(R.id.spinnerdecimal);
        buttonsonucdecimal = findViewById(R.id.buttonsonucdecimal);
        textViewdecimal = findViewById(R.id.textViewdecimal);

        megaByteEditText = findViewById(R.id.megaByteEditText);
        donusturmeTipiByteSpinner = findViewById(R.id.donusturmeTipiByteSpinner);
        donusturmeButtonByte = findViewById(R.id.donusturmeButtonByte);
        sonucTextViewByte = findViewById(R.id.sonucTextViewByte);

        celsiusEditText = findViewById(R.id.celsiusEditText);
        fahrenheitRadioButton = findViewById(R.id.fahrenheitRadioButton);
        kelvinRadioButton = findViewById(R.id.kelvinRadioButton);
        donusturmeButtonsicaklik = findViewById(R.id.donusturmeButtonsicaklik);
        sonucTextView = findViewById(R.id.sonucTextView);

        donusturmeButtonsicaklik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donusturmeYapSicaklik();
            }
        });

        String[] donusturmeTipleri = {"İkilik", "Sekizlik", "Onaltılık"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, donusturmeTipleri);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerdecimal.setAdapter(adapter);

        buttonsonucdecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donusturmeYap();
            }
        });

        String[] donusturmeTipleriByte = {"Kilo Byte", "Byte", "Kibi Byte", "Bit"};
        ArrayAdapter<String> adapterByte = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, donusturmeTipleriByte);
        adapterByte.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        donusturmeTipiByteSpinner.setAdapter(adapterByte);

        donusturmeButtonByte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donusturmeYapByte();
            }
        });
    }

    private void donusturmeYap() {
        String onlukSayiStr = editTextNumberDecimal.getText().toString();

        if (onlukSayiStr.isEmpty()) {
            textViewdecimal.setText("Lütfen bir sayı girin");
            return;
        }

        int onlukSayi = Integer.parseInt(onlukSayiStr);
        String secilenTip = spinnerdecimal.getSelectedItem().toString();
        String sonuc = "";

        switch (secilenTip) {
            case "İkilik":
                sonuc = Integer.toBinaryString(onlukSayi);
                break;
            case "Sekizlik":
                sonuc = Integer.toOctalString(onlukSayi);
                break;
            case "Onaltılık":
                sonuc = Integer.toHexString(onlukSayi);
                break;
        }

        textViewdecimal.setText("Sonuç: " + sonuc);
    }

    private void donusturmeYapByte() {
        String megabyteStr = megaByteEditText.getText().toString();

        if (megabyteStr.isEmpty()) {
            sonucTextViewByte.setText("Lütfen bir sayı girin");
            return;
        }

        double megabyte = Double.parseDouble(megabyteStr);
        String secilenTipByte = donusturmeTipiByteSpinner.getSelectedItem().toString();
        double sonucByte = 0;

        switch (secilenTipByte) {
            case "Kilo Byte":
                sonucByte = megabyte * 1024;
                break;
            case "Byte":
                sonucByte = megabyte * 1024 * 1024;
                break;
            case "Kibi Byte":
                sonucByte = megabyte * 1000;
                break;
            case "Bit":
                sonucByte = megabyte * 1024 * 1024 * 8;
                break;
        }

        sonucTextViewByte.setText("Sonuç: " + sonucByte);
    }

    private void donusturmeYapSicaklik() {
        String celsiusStr = celsiusEditText.getText().toString();

        if (celsiusStr.isEmpty()) {
            sonucTextView.setText("Lütfen bir sıcaklık girin");
            return;
        }

        double celsius = Double.parseDouble(celsiusStr);
        double sonuc = 0;

        if(fahrenheitRadioButton.isChecked()){
            sonuc = (celsius * 9/5) + 32;
        }else if(kelvinRadioButton.isChecked()){
            sonuc = celsius + 273.15;
        }
        sonucTextView.setText("Sonuç: " + sonuc);
    }
}
