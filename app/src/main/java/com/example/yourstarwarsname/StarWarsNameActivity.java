package com.example.yourstarwarsname;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.jar.Attributes;

public class StarWarsNameActivity extends AppCompatActivity {

        private NameRoomDatabase nameDatabase;

        protected EditText nameEditText;
        protected Button addNameButton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_name);

            nameEditText = findViewById(R.id.name_edit_text);
            addNameButton = findViewById(R.id.add_name_button);

            addNameButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addName();
                }
            });
        }

        @Override
        protected void onStart() {
            super.onStart();
            nameDatabase = ((NameApplication)getApplicationContext()).getDatabase();
        }

        protected void addName() {
            if (nameEditText.getText().toString().isEmpty()) {
                Toast.makeText(this, "Again, do! Name must you enter.", Toast.LENGTH_LONG).show();
            } else {
                Name name = new Attributes.Name(nameEditText.getText().toString());

                nameDatabase.nameDao().insert(name);

                Toast.makeText(this, "Added, your name has!", Toast.LENGTH_LONG).show();

                addAnotherPrompt();
            }
        }

        protected void addAnotherPrompt() {

            final AlertDialog.Builder alert = new AlertDialog.Builder(this);

            alert.setTitle("Add Another Name?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            nameEditText.setText("");

                            dialog.dismiss();
                        }
                    })

                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            finish();
                        }
                    })

                    .show();

        }
    }
}
