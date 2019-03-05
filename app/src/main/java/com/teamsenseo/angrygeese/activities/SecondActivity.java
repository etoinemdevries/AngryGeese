package com.teamsenseo.angrygeese.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.teamsenseo.angrygeese.R;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Second activity interface
 *
 * @author Gago
 */
public final class SecondActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private Button logout;

    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_second);

        this.firebaseAuth = FirebaseAuth.getInstance();
        this.logout = findViewById(R.id.btnLogout);

        this.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout();
            }
        });
    }

    /**
     * Log the user out
     */
    private final void Logout() {
        this.firebaseAuth.signOut();
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
            case R.id.logoutMenu:
                Logout();
                break;
        }

        return true;
    }
}
