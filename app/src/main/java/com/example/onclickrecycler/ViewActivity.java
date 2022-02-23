package com.example.onclickrecycler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {
    TextView  TName;
    ImageView imageView_v;
    DatabaseReference ref;
    private ArrayList<person> messagesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        ref = FirebaseDatabase.getInstance().getReference();

        imageView_v = findViewById(R.id.imageView_v);
        TName = findViewById(R.id.textView_v);


        String CarKey = getIntent().getStringExtra("CarKey");

        ref.child(CarKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){



                    String Name = snapshot.child("name").getValue().toString();
                    String image = snapshot.child("image").getValue().toString();

                    //ininliazting them
                    TName.setText(Name);
                   Picasso.get().load(image).into(imageView_v);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}