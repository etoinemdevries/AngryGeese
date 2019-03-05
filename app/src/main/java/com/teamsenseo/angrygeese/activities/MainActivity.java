package com.teamsenseo.angrygeese.activities;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseUser;

/**
 * Login screen
 *
 * @author Gago
 */
public class MainActivity extends AppCompatActivity {
    private TextView info, userRegistration;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private EditText name, password;
    private int counter = 5;
    private Button login;

    @Override
    protected final void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);

        this.name = findViewById(R.id.etName);
        this.password = findViewById(R.id.etPassword);
        this.info = findViewById(R.id.tvInfo);
        this.login = findViewById(R.id.btnLogin);
        this.userRegistration = findViewById(R.id.tvRegister);
        this.info.setText("No of attempts remaining: 5");

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

    /** Attempts to log in */
    private final void validate(final String name, final String password) {
        progressDialog.setMessage("ProgressDialog");
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

                    info.setText("Attempt: " + counter);
                    progressDialog.dismiss();

                    if (counter-- == 0) {
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

                                login.setEnabled(true);
                            }
                        }).run();
                    }
                }
            }
        });
    }
}
