package com.teamsenseo.angrygeese.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.teamsenseo.angrygeese.Field;
import com.teamsenseo.angrygeese.R;

public class SecondActivity extends AppCompatActivity {

    private EditText fieldID,fieldPerceel,fieldDamage;
    private Button send;

    DatabaseReference databaseReference;

    private FirebaseAuth firebaseAuth;
//    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("Velden");

        fieldID = (EditText)findViewById(R.id.fieldID);
        fieldPerceel = (EditText)findViewById(R.id.fieldPerceel);
        fieldDamage = (EditText)findViewById(R.id.fieldDamage);
        send = (Button)findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addField();
            }
        });

    }

    private void Logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(SecondActivity.this, MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater() .inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.logoutMenu: {
                Logout();
            }
        }
        return true;
    }

    public void addField() {

        String veldID = fieldID.getText().toString();
        String perceelNaam = fieldPerceel.getText().toString();
        String veldSchade = fieldDamage.getText().toString();


        if(!TextUtils.isEmpty(veldID) && !TextUtils.isEmpty(perceelNaam)) {

            String id = databaseReference.push().getKey();

            Field veld = new Field(veldID,perceelNaam,veldSchade);

            databaseReference.child(id).setValue(veld);

            fieldID.setText("");
            fieldPerceel.setText("");
            fieldDamage.setText("");

        }
        else {
            Toast.makeText(SecondActivity.this,"Please fill in the remaining forms", Toast.LENGTH_SHORT).show();
        }
    }
}
