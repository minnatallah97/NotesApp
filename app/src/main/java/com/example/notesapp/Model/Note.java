package com.example.notesapp.Model;

import android.widget.EditText;

public class Note {
    String NoteId;
    String NoteTitle;
    String NoteDesc;

    public Note() {
    }

    public Note(String noteId, String noteTitle, String noteDesc) {
        NoteId = noteId;
        NoteTitle = noteTitle;
        NoteDesc = noteDesc;
    }

    public String getNoteId() {
        return NoteId;
    }

    public void setNoteId(String noteId) {
        NoteId = noteId;
    }

    public String getNoteTitle() {
        return NoteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        NoteTitle = noteTitle;
    }

    public String getNoteDesc() {
        return NoteDesc;
    }

    public void setNoteDesc(String noteDesc) {
        NoteDesc = noteDesc;
    }
}