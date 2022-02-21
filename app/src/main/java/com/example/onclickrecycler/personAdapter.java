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

        holder.firstname.setText(model.getFirstname());


        holder.lastname.setText(model.getLastname());

        holder.age.setText(model.getAge());

        Picasso.get().load(model.getImage()).into(holder.imageView);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ViewActivity.class);
                intent.putExtra("CarKey", getRef(position).getKey());
                view.getContext().startActivity(intent);


                // Toast.makeText(view.getContext(), "Good"+    position , Toast.LENGTH_LONG).show();
            }
        });



    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public personsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person, parent, false);
        return new personAdapter.personsViewholder(view);
    }


    class personsViewholder
            extends RecyclerView.ViewHolder {
        TextView firstname, lastname, age;
        ImageView imageView;
        public personsViewholder(@NonNull View itemView)
        {
            super(itemView);

            firstname = itemView.findViewById(R.id.firstname);
            lastname = itemView.findViewById(R.id.lastname);
            age = itemView.findViewById(R.id.age);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
