package saim.com.sammessenger;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {

    FirebaseAuth auth;

    EditText email_reg, password_reg;
    Button btnSignup_reg, btnPassword_reg, btnSignin_reg;
    ProgressBar progressBar_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        init();
    }

    public void init(){
        auth = FirebaseAuth.getInstance();

        email_reg = (EditText) findViewById(R.id.email_reg);
        password_reg = (EditText) findViewById(R.id.password_reg);

        btnSignup_reg = (Button) findViewById(R.id.btnSignup_reg);
        btnPassword_reg = (Button) findViewById(R.id.btnPassword_reg);
        btnSignin_reg = (Button) findViewById(R.id.btnSignin_reg);

        progressBar_reg = (ProgressBar) findViewById(R.id.progressBar_reg);

        ButtonClicked();
    }

    private void ButtonClicked() {

        btnSignup_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_reg.getText().toString().trim();
                String password = password_reg.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar_reg.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(Signup.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar_reg.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(Signup.this, "Authentication failed." + task.getException(),Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(Signup.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });

            }
        });

        btnSignin_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnPassword_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
