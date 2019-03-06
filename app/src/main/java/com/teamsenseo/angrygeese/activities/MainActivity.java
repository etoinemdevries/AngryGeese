package com.teamsenseo.angrygeese.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.teamsenseo.angrygeese.R;

/**
 * Login screen
 *
 * @author Gago
 */
public final class MainActivity extends AppCompatActivity {
    private TextView info, userRegistration;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private EditText name, password;
    private Button login;
    private int counter;

    @Override
    protected final void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);

        this.name = findViewById(R.id.etName);
        this.password = findViewById(R.id.etPassword);
        this.info = findViewById(R.id.tvInfo);
        this.login = findViewById(R.id.btnLogin);
        this.userRegistration = findViewById(R.id.tvRegister);
        this.info.setText("Remaining attempts: " + (counter = 5));

        this.firebaseAuth = FirebaseAuth.getInstance();
        this.progressDialog = new ProgressDialog(this);
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
            finish();
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        }

        this.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(final View view) {
                validate(name.getText().toString(), password.getText().toString());
            }
        });

        this.userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(final View view) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });
    }

    /**
     * Attempts to log in
     */
    private final void validate(final String name, final String password) {
        if (name.isEmpty() || password.isEmpty()) {
            Toast.makeText(MainActivity.this, "Cannot log in with empty username and/or password", Toast.LENGTH_LONG).show();
            System.out.println("Attempted to sign in with empty username and/or password");
            return;
        }

        progressDialog.setMessage("Logging in");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(name, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public final void onComplete(final @NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this, SecondActivity.class));
                } else {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                    info.setText("Remaining attempts: " + --counter);
                    progressDialog.dismiss();

                    if (counter == 0) {
                        info.setText("Please wait 5 seconds");
                        login.setEnabled(false);

                        new Thread(new Runnable() {
                            @Override
                            public final void run() {
                                try {
                                    Thread.sleep(5000L);
                                } catch (final Exception e) {
                                    System.out.println("Failed to sleep");
                                    e.printStackTrace();
                                }

                                info.setText("Remaining attempts: " + (counter = 5));
                                login.setEnabled(true);
                            }
                        }).run();
                    }
                }
            }
        });
    }
}
