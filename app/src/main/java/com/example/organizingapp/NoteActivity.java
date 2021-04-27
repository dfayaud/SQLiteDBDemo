package com.example.organizingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class NoteActivity extends AppCompatActivity {

    Button addNoteButton, calendarButton, byDateButton, byBookButton;
    EditText bookNameEditText, noteTextEditText, dateEditText;
    ListView noteListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        addNoteButton = findViewById(R.id.addNoteButton);
        byDateButton = findViewById(R.id.byDateButton);
        byBookButton = findViewById(R.id.byBookButton);
        bookNameEditText = findViewById(R.id.bookNameEditText);
        noteTextEditText = findViewById(R.id.noteTextEditText);
        dateEditText = findViewById(R.id.dateEditText);
        noteListView = findViewById(R.id.noteListView);
        calendarButton = findViewById(R.id.calendarButton);

        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataBaseHelper dataBaseHelper = new DataBaseHelper(NoteActivity.this);
                Note note = new Note(-1,
                        dataBaseHelper.notebookNameToNotebookId(bookNameEditText.getText().toString()),
                        noteTextEditText.getText().toString(),
                        dateEditText.getText().toString(),
                        false);
                boolean success = dataBaseHelper.addNote(note);

                List<Note> notes = dataBaseHelper.getNotes();
                ArrayAdapter noteArrayAdapter = new ArrayAdapter<Note>(NoteActivity.this, android.R.layout.simple_list_item_1, notes);
                noteListView.setAdapter(noteArrayAdapter);
                Toast.makeText(NoteActivity.this, "Note Added", Toast.LENGTH_SHORT).show();

            }

        });

        byDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(NoteActivity.this);
                List<Note> notes = dataBaseHelper.getNotes("4/29/21");
                ArrayAdapter noteArrayAdapter = new ArrayAdapter<Note>(NoteActivity.this, android.R.layout.simple_list_item_1, notes);
                noteListView.setAdapter(noteArrayAdapter);
            }
        });

        byBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(NoteActivity.this);
                List<Note> notes = dataBaseHelper.getNotes(dataBaseHelper.notebookNameToNotebookId("Plants"));
                ArrayAdapter noteArrayAdapter = new ArrayAdapter<Note>(NoteActivity.this, android.R.layout.simple_list_item_1, notes);
                noteListView.setAdapter(noteArrayAdapter);
            }
        });

        //Substituting for calendar intent

//        calendarButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                DataBaseHelper dataBaseHelper = new DataBaseHelper(NoteActivity.this);
//                List<Note> notes = dataBaseHelper.getNotes();
//                ArrayAdapter noteArrayAdapter = new ArrayAdapter<Note>(NoteActivity.this, android.R.layout.simple_list_item_1, notes);
//                noteListView.setAdapter(noteArrayAdapter);
//            }
//        });

        noteListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Note clickedNote = (Note)parent.getItemAtPosition(position);
                DataBaseHelper dataBaseHelper = new DataBaseHelper(NoteActivity.this);
                dataBaseHelper.deleteNote(clickedNote);
                Toast.makeText(NoteActivity.this, "Deleted " + clickedNote, Toast.LENGTH_SHORT).show();
                List<Note> notes = dataBaseHelper.getNotes();
                ArrayAdapter noteArrayAdapter = new ArrayAdapter<Note>(NoteActivity.this, android.R.layout.simple_list_item_1, notes);
                noteListView.setAdapter(noteArrayAdapter);

                return false;
            }
        });

        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note clickedNote = (Note)parent.getItemAtPosition(position);
                DataBaseHelper dataBaseHelper = new DataBaseHelper(NoteActivity.this);
                dataBaseHelper.updateNoteIsCompleted(clickedNote, true);
                Toast.makeText(NoteActivity.this, "Task Completed", Toast.LENGTH_SHORT).show();
                List<Note> notes = dataBaseHelper.getNotes();
                ArrayAdapter noteArrayAdapter = new ArrayAdapter<Note>(NoteActivity.this, android.R.layout.simple_list_item_1, notes);
                noteListView.setAdapter(noteArrayAdapter);
            }
        });


    }
    public void switchCalendarActivity(View view){
        Intent intent  = new Intent(this, MainActivity.class);  // <----- rename "MainActivity" to switch to calendar
        startActivity(intent);

    }
}