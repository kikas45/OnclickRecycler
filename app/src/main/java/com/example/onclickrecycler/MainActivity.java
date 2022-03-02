package com.example.onclickrecycler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    personAdapter adapter; // Create Object of the Adapter class
    DatabaseReference mbase; // Create object of the

    SwipeRefreshLayout swipeRefreshLayout;

    private ShimmerFrameLayout shimmerFrameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///// shimmer
        shimmerFrameLayout = findViewById(R.id.shimmerLayout);
        shimmerFrameLayout.startShimmer();

        //////

      Utils.getDatabase();

        mbase = FirebaseDatabase.getInstance().getReference();

        recyclerView = findViewById(R.id.recycler1);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<person> options
                = new FirebaseRecyclerOptions.Builder<person>()
                .setQuery(mbase, person.class)
                .build();

        /// this will help us get reid of shimmer effect
        mbase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        adapter = new personAdapter(options);
        recyclerView.setAdapter(adapter);


        swipeRefreshLayout = findViewById(R.id.swipeRefresher);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onRefresh() {

                FirebaseRecyclerOptions<person> options
                        = new FirebaseRecyclerOptions.Builder<person>()
                        .setQuery(mbase, person.class)
                        .build();

                adapter = new personAdapter(options);
                ////
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);


            }
        });


    }


    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();


    }


    /*@Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }*/

    @Override
    protected void onResume() {
        super.onResume();
        adapter.startListening();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        adapter.stopListening();
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