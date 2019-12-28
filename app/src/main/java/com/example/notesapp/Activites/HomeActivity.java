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

import com.example.notesapp.Adapters.NoteAdapter;
import com.example.notesapp.Model.Note;
import com.example.notesapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity  {


    private RecyclerView recyclerView ;
    private FirebaseRecyclerOptions<Note> options ;
    private FirebaseRecyclerAdapter<Note,FirebaseViewHolder> adapter ;
    private DatabaseReference databaseReference ;

    List<Note> arrayList ;
    FloatingActionButton add_btn ;
    FloatingActionButton signout ;

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening() ;
    }

    @Override
    protected void onStop()  {
        super.onStop();
        adapter.stopListening() ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_home) ;


        arrayList  = new ArrayList<Note>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("notes") ;
        databaseReference.keepSynced(true);
        options = new FirebaseRecyclerOptions.Builder<Note>().setQuery(databaseReference,Note.class).build() ;
        adapter = new FirebaseRecyclerAdapter<Note, FirebaseViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewHolder holder, int position, @NonNull final Note model) {
                holder.title.setText(arrayList.get(position).getNoteTitle()) ;
                holder.Desc.setText(arrayList.get(position).getNoteDesc()) ;



            }

            @NonNull
            @Override
            public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebaseViewHolder(LayoutInflater.from(HomeActivity.this).inflate(R.layout.row_item2,parent,false));
            }
        };

        add_btn = findViewById(R.id.add_btn) ;
        recyclerView = findViewById(R.id.rvV) ;
        recyclerView.setHasFixedSize(true) ;
        recyclerView.setAdapter(adapter) ;


        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,AddNote.class) ;
                startActivity(intent);


            }
        });
        signout = findViewById(R.id.signout_btn) ;
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut() ;
                Intent intent = new Intent(HomeActivity.this,SigninActivity.class) ;
                startActivity(intent) ;
            }
        });
}




    }
