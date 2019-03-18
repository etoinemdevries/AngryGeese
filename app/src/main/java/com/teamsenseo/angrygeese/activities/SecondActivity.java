package com.teamsenseo.angrygeese.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.teamsenseo.angrygeese.utils.Field;
import com.teamsenseo.angrygeese.R;

public final class SecondActivity extends AppCompatActivity {
    private EditText fieldID, fieldPerceel, fieldDamage;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private Button send;

    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Velden");

        fieldID = findViewById(R.id.fieldID);
        fieldPerceel = findViewById(R.id.fieldPerceel);
        fieldDamage = findViewById(R.id.fieldDamage);
        send = findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addField();
            }
        });
    }

    private final void Logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(SecondActivity.this, MainActivity.class));
    }

    @Override
    public final boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public final boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logoutMenu: {
                Logout();
            }
        }
        return true;
    }

    public final void addField() {
        String veldID = fieldID.getText().toString();
        String perceelNaam = fieldPerceel.getText().toString();
        String veldSchade = fieldDamage.getText().toString();


        if (!veldID.isEmpty() && !perceelNaam.isEmpty()) {
            String id = databaseReference.push().getKey();
            Field veld = new Field(veldID, perceelNaam, veldSchade);
            databaseReference.child(id).setValue(veld);

            fieldID.setText("");
            fieldPerceel.setText("");
            fieldDamage.setText("");
            return;
        }

        Toast.makeText(SecondActivity.this, "Please fill in the remaining forms", Toast.LENGTH_SHORT).show();
    }
}
