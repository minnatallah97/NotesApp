package com.example.notesapp.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notesapp.Model.Note;
import com.example.notesapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddNote extends AppCompatActivity {
    Button save;
    EditText et_title , et_Desc;
    TextView tv ;
    DatabaseReference databaseNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        databaseNote = FirebaseDatabase.getInstance().getReference("notes");
        save = findViewById(R.id.save);
        et_title = findViewById(R.id.note_title);
        et_Desc = findViewById(R.id.note_desc);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();

            }
        });

    }
    private void addNote(){
        String title = et_title.getText().toString().trim();
        String desc = et_Desc.getText().toString().trim();

        if(!TextUtils.isEmpty(title)){
            String id = databaseNote.push().getKey();
            Note note = new Note(id,title,desc);
            databaseNote.child(id).setValue(note);
            Toast.makeText(this, "Note Added ", Toast.LENGTH_SHORT).show();

        }

        else {
            Toast.makeText(this, "Enter the note title ", Toast.LENGTH_SHORT).show();
        }
    }
}
