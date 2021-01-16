package com.android.uas1;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.android.uas1.DatabaseHandler.NUMBER;

public class DashboardApp extends AppCompatActivity {

    EditText numberOne, numberTwo, numberThree, numberFour;
    TextView txtAngka;
    Button btnSearch;
    int displayValue,inputNumber;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    DatabaseHandler dbHandler;
    List<Integer> number = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_app);

        numberOne = findViewById(R.id.angkaPertama);
        numberTwo = findViewById(R.id.angkaKedua);
        numberThree = findViewById(R.id.angkaKetiga);
        numberFour = findViewById(R.id.angkaKeempat);
        btnSearch = findViewById(R.id.Cari);
        dbHandler = new DatabaseHandler(this);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (numberOne.getText().toString().length() == 0){
                    numberOne.setError("anda belum memasukan nilai");
                } else if(numberTwo.getText().toString().length() == 0){
                    numberTwo.setError("anda belum memasukan nilai");
                } else if(numberThree.getText().toString().length() == 0){
                    numberThree.setError("anda belum memasukan nilai");
                } else if(numberFour.getText().toString().length() == 0){
                    numberFour.setError("anda belum memasukan nilai");
                } else {
                    number.add(Integer.parseInt(numberOne.getText().toString()));
                    number.add(Integer.parseInt(numberTwo.getText().toString()));
                    number.add(Integer.parseInt(numberThree.getText().toString()));
                    number.add(Integer.parseInt(numberFour.getText().toString()));
                    Log.i( "Value: ", number.toString());
                    displayValue = Collections.min(number);
                    dialogForm();

                    number.clear();

                    numberOne.setText(null);
                    numberTwo.setText(null);
                    numberThree.setText(null);
                    numberFour.setText(null);
                }


            }
        });


    }

   public void alertForm(Context context){
        new AlertDialog.Builder(DashboardApp.this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("Peringatan")
                .setMessage("Masih ada Field yang belum anda input")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DashboardApp.this, "OK", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private void dialogForm(){
        dialog = new AlertDialog.Builder(DashboardApp.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.save_number, null);
        dialog.setView(dialogView);
        dialog.setTitle("Konfirmasi");

        txtAngka = dialogView.findViewById(R.id.angka);
        txtAngka.setText(Integer.toString(displayValue));
        dialog.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                addRecord(displayValue);
                Toast.makeText(DashboardApp.this, "Angka Berhasil Disimpan", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();

    }

    public void addRecord(int number){
        try {
            SQLiteDatabase db = dbHandler.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(dbHandler.NUMBER, displayValue);

            db.insert(dbHandler.TABLE_NAME,null, values);
        } catch (SQLiteAbortException exception){
            Log.e("Data Tidak Tersimpan ", exception.getMessage() );
        }
    }
}