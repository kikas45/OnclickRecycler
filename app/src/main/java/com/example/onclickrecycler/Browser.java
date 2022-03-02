package com.example.onclickrecycler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Browser extends AppCompatActivity {
    TextView TName;
    ImageView imageView_v;
    DatabaseReference ref;

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);



     try {
         Utils.getDatabase();
     } catch (Exception exception){

         Toast.makeText(this, "Unable to get offline data", Toast.LENGTH_SHORT).show();
     }


        ref = FirebaseDatabase.getInstance().getReference();
        TName = findViewById(R.id.url);
        String CarKey = getIntent().getStringExtra("CarKey");

        ref.child(CarKey).addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetJavaScriptEnabled")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    String Name = snapshot.child("url").getValue().toString();
                    // String image = snapshot.child("image").getValue().toString();
                    //ininliazting them

                    TName.setText(Name);
                   // Picasso.get().load(image).into(imageView_v);

                    webView = findViewById(R.id.webview);
                    webView.loadUrl(Name);
                    webView.getSettings().setJavaScriptEnabled(true);

                    // WebViewClient allows you to handle
                    // onPageFinished and override Url loading.
                    webView.setWebViewClient(new WebViewClient());
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

    public static class Utils {
        private static FirebaseDatabase mDatabase;

        public static FirebaseDatabase getDatabase() {
            if (mDatabase == null) {
                mDatabase = FirebaseDatabase.getInstance();
                mDatabase.setPersistenceEnabled(true);
            }
            return mDatabase;
        }

    }

}