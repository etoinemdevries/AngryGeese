package com.teamsenseo.angrygeese.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.teamsenseo.angrygeese.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Register screen
 *
 * @author Gago
 */
public final class RegistrationActivity extends AppCompatActivity {
    private EditText name, password, email;
    private FirebaseAuth firebaseAuth;
    private TextView userLogin;
    private Button regButton;

    @Override
    protected final void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_registration);

        /* Set up interface components */
        this.name = findViewById(R.id.etUserName);
        this.password = findViewById(R.id.etUserPassword);
        this.email = findViewById(R.id.etUserEmail);
        this.regButton = findViewById(R.id.btnRegister);
        this.userLogin = findViewById(R.id.tvUserLogin);
        this.firebaseAuth = FirebaseAuth.getInstance();

        this.regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(final View View) {
                if (validate()) {
                    final String authMail = email.getText().toString().trim();
                    final String authPassword = password.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(authMail, authPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public final void onComplete(final @NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegistrationActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                                return;
                            }

                            Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        this.userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(final View view) {
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });
    }

    /**
     * Checks if all data is entered
     */
    private final boolean validate() {
        final String name = this.name.getText().toString();
        final String password = this.password.getText().toString();
        final String email = this.email.getText().toString();

        if (name.isEmpty() || password.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "please enter all the details", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}