package com.example.testbarang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LihatBarang extends AppCompatActivity {

    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Barang> daftarBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_barang);

        rvView = (RecyclerView) findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);

        database = FirebaseDatabase.getInstance().getReference();


        database.child("Barang").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                daftarBarang = new ArrayList<>();
                for (DataSnapshot notDataSnapshot : dataSnapshot.getChildren()){

                    Barang barang = notDataSnapshot.getValue(Barang.class);
                    barang.setKode(notDataSnapshot.getKey());

                    daftarBarang.add(barang);

                }

                adapter = new AdapterLihatBarang(daftarBarang, LihatBarang.this);
                rvView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


                System.out.println(databaseError.getDetails()+""+databaseError.getMessage());
            }
        });
    }
    public static Intent getActIntent(Activity activity){
        return new Intent(activity, LihatBarang.class);
    }

    public void onDeleteData(Barang barang, final int position){

        if(database!=null){
            database.child("barang").child(barang.getKode()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(LihatBarang.this, "succes delete", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
