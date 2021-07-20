package com.example.coding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.coding.adapter.MainAdapter;
import com.example.coding.model.Model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Model model;
    ArrayList<Model> list;
    MainAdapter adapter;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview);
        MainAdapter adapter=new MainAdapter(this,list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);
        try {
            GetDataFromFirebase();
            ClearAll();

        }catch(Exception e)
        {
            e.printStackTrace();
        }



    }

    private void ClearAll() {
        if(list !=null)
        {
            list.clear();
        }
        list=new ArrayList<>();
    }

    private void GetDataFromFirebase() {
/*
        Query query=databaseReference.child("model");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    Model model=new Model();
                    model.setImage(snapshot.child("image").getValue().toString());
                    model.setName(snapshot.child("name").getValue().toString());
                    list.add(model);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

*/
        Toast.makeText(this, "sahi 1", Toast.LENGTH_SHORT).show();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("model");
        Toast.makeText(this, "Sahi 2", Toast.LENGTH_SHORT).show();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    Model model=new Model();
                    Toast.makeText(MainActivity.this, "sahi 3", Toast.LENGTH_SHORT).show();
                    model.setImage(snapshot.child("image").getValue().toString());
                    model.setName(snapshot.child("name").getValue().toString());
                    list.add(model);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });


    }
}