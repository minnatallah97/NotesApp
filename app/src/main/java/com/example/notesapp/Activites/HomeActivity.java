
package com.example.notesapp.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.notesapp.Model.Note;
import com.example.notesapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    FloatingActionButton add_btn;
    FloatingActionButton signout;
    private ArrayList<Note> arrayList;
    private FirebaseRecyclerAdapter <Note,FirebaseViewHolder> adapter;
    private  FirebaseRecyclerOptions<Note> options;
    private DatabaseReference databaseReference;

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        add_btn = findViewById(R.id.add_btn);
        recyclerView = findViewById(R.id.rvV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<Note>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("notes");
        databaseReference.keepSynced(true);
        options = new FirebaseRecyclerOptions.Builder<Note>().setQuery(databaseReference,Note.class).build();
        adapter = new FirebaseRecyclerAdapter<Note, FirebaseViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewHolder holder, int position, @NonNull final Note model) {
                holder.title.setText(model.getNoteTitle());
                holder.Desc.setText(model.getNoteDesc());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(HomeActivity.this,Main2Activity.class);
                        intent.putExtra("title",model.getNoteTitle());
                        intent.putExtra("Desc",model.getNoteDesc());
                        startActivity(intent);

                    }
                });
            }

            @NonNull
            @Override
            public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebaseViewHolder(LayoutInflater.from(HomeActivity.this).inflate(R.layout.row_item2,parent,false));
            }
        };
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,AddNote.class);
                startActivity(intent);


            }
        });
        signout = findViewById(R.id.signout_btn);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(HomeActivity.this,SigninActivity.class);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);
    }




}