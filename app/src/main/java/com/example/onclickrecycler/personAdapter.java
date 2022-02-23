package com.example.onclickrecycler;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;


public class personAdapter extends FirebaseRecyclerAdapter<
        person, personAdapter.personsViewholder> {

    public personAdapter(
            @NonNull FirebaseRecyclerOptions<person> options)
    {
        super(options);
    }

    // Function to bind the view in Card view(here
    // "person.xml") iwth data in
    // model class(here "person.class")
    @Override
    protected void
    onBindViewHolder(@NonNull personsViewholder holder, @SuppressLint("RecyclerView") int position, @NonNull person model)
    {

     ;

        holder.textView_p.setText(model.getName());
        holder.textView_url.setText(model.getUrl());

        Picasso.get().load(model.getImage()).into(holder.imageView);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Browser.class);
                intent.putExtra("CarKey", getRef(position).getKey());
                view.getContext().startActivity(intent);
                // Toast.makeText(view.getContext(), "Good"+    position , Toast.LENGTH_LONG).show();
            }
        });


    }


    @NonNull
    @Override
    public personsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person, parent, false);
        return new personAdapter.personsViewholder(view);
    }


    class personsViewholder
            extends RecyclerView.ViewHolder {
        TextView textView_p, textView_url;
        ImageView imageView;
        public personsViewholder(@NonNull View itemView)
        {
            super(itemView);


            textView_p = itemView.findViewById(R.id.textView_p);
            textView_url = itemView.findViewById(R.id.textView_url);
            imageView = itemView.findViewById(R.id.imageView_p);
        }
    }
}