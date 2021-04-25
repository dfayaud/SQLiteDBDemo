package com.example.organizingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Button addNotebookButton, viewAllButton, notesActivityButton;
    EditText notebookNameEditText, notebookColorEditText, pictureLocationEditText;
    CheckBox isListCheckBox;
    ListView notebookListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNotebookButton = findViewById(R.id.addNotebookButton);
        notebookNameEditText = findViewById(R.id.notebookNameEditText);
        notebookColorEditText = findViewById(R.id.notebookColorEditText);
        isListCheckBox = findViewById(R.id.isListCheckBox);
        notebookListView = findViewById(R.id.notebookListView);
        viewAllButton = findViewById(R.id.viewAllButton);
        notesActivityButton = findViewById(R.id.notesActivityButton);
        pictureLocationEditText = findViewById(R.id.pictureLocationEditText);

        addNotebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Notebook notebook = new Notebook(-1,
                        notebookNameEditText.getText().toString(),
                        notebookColorEditText.getText().toString(),
                        pictureLocationEditText.getText().toString(),
                        isListCheckBox.isChecked());
                Toast.makeText(MainActivity.this, "Notebook Added", Toast.LENGTH_SHORT).show();

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                boolean success = dataBaseHelper.addNotebook(notebook);
                List<Notebook> notebooks = dataBaseHelper.getNotebooks();
                ArrayAdapter notebookArrayAdapter = new ArrayAdapter<Notebook>(MainActivity.this, android.R.layout.simple_list_item_1, notebooks);
                notebookListView.setAdapter(notebookArrayAdapter);
            }

        });

        viewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                List<Notebook> notebooks = dataBaseHelper.getNotebooks();
                ArrayAdapter notebookArrayAdapter = new ArrayAdapter<Notebook>(MainActivity.this, android.R.layout.simple_list_item_1, notebooks);
                notebookListView.setAdapter(notebookArrayAdapter);
                //Toast.makeText(MainActivity.this, notebooks.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        notebookListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Notebook clickedNotebook = (Notebook)parent.getItemAtPosition(position);
                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                dataBaseHelper.deleteNotebook(clickedNotebook);
                Toast.makeText(MainActivity.this, "Deleted " + clickedNotebook, Toast.LENGTH_SHORT).show();
                List<Notebook> notebooks = dataBaseHelper.getNotebooks();
                ArrayAdapter notebookArrayAdapter = new ArrayAdapter<Notebook>(MainActivity.this, android.R.layout.simple_list_item_1, notebooks);
                notebookListView.setAdapter(notebookArrayAdapter);

                return false;
            }
        });


    }

    public void switchNoteActivity(View view){
        Intent intent  = new Intent(this, NoteActivity.class);
        startActivity(intent);

    }
}