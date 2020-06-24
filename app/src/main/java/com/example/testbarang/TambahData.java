package com.example.testbarang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahData extends AppCompatActivity {

    private DatabaseReference database;


    private Button btSubmit;
    private EditText etKode;
    private EditText etNama;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        etKode = (EditText) findViewById(R.id.editNo);
        etNama = (EditText) findViewById(R.id.editNama);
        btSubmit = (Button) findViewById(R.id.btnOk);


        database = FirebaseDatabase.getInstance().getReference();

        final Barang barang = (Barang) getIntent().getSerializableExtra("data");

        if (barang != null) {
            etNama.setText(barang.getNama());
            etKode.setText(barang.getKode());
            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    barang.setNama(etNama.getText().toString());
                    barang.setKode(etKode.getText().toString());

                    updateBarang(barang);
                }
            });
        } else {

            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!(etKode.getText().toString().isEmpty()) && !(etNama.getText().toString().isEmpty()))
                        submitBrg(new Barang(etKode.getText().toString(), etNama.getText().toString()));
                    else
                        Toast.makeText(getApplicationContext(), "Data tidak boleh kosong",
                                Toast.LENGTH_LONG).show();

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(etKode.getWindowToken(), 0);
                }


            });
        }
    }

    private boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }

    private void  updateBarang(Barang barang){
        database.child("barang")
                .child(barang.getKode())
                .setValue(barang)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {


                        Snackbar.make(findViewById(R.id.btnTambah), "Data berhasil diupdatekan", Snackbar.LENGTH_LONG).setAction("Oke", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        }).show();
                    }
                });
    }

    private void submitBrg(Barang brg) {

        database.child("Barang").push().setValue(brg).addOnSuccessListener(this,
                new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        etKode.setText("");
                        etNama.setText("");
                        Toast.makeText(getApplicationContext(),
                                "Data berhasi ditambahkan",
                                Toast.LENGTH_LONG).show();
                    }
                });
         }
    public static Intent getActIntent(MainActivity activity) {
        return new Intent(activity, TambahData.class);
    }
    }



