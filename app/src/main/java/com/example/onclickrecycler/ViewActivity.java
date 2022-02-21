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
    TextView  Tage, TlastName, TfirstName;
    ImageView imageView_c;
    DatabaseReference ref;
    private ArrayList<person> messagesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        ref = FirebaseDatabase.getInstance().getReference();

        imageView_c = findViewById(R.id.imageView22);

        Tage = findViewById(R.id.Tage);
        TfirstName = findViewById(R.id.Tfirstname);
        TlastName = findViewById(R.id.Tlastname);

        String CarKey = getIntent().getStringExtra("CarKey");

        ref.child(CarKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){

                    String carName = snapshot.child("firstname").getValue().toString();
                    String ImageUrl = snapshot.child("lastname").getValue().toString();
                    String age = snapshot.child("age").getValue().toString();
                    String image = snapshot.child("image").getValue().toString();

                    Tage.setText(age);
                    TlastName.setText(ImageUrl);
                    TfirstName.setText(carName);

                    Picasso.get().load(image).into(imageView_c);
                    // Now adding the data to image and text viw
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