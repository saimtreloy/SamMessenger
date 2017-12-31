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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import saim.com.sammessenger.Model.ModelUser;
import saim.com.sammessenger.Utility.DatabaseTable;

public class Signup extends AppCompatActivity {

    FirebaseAuth auth;
    DatabaseReference mDatabase;

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
        mDatabase = FirebaseDatabase.getInstance().getReference().child(DatabaseTable.userTable);

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
                final String email = email_reg.getText().toString().trim();
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
                                if (!task.isSuccessful()) {
                                    Toast.makeText(Signup.this, "Authentication failed." + task.getException(),Toast.LENGTH_SHORT).show();
                                } else {

                                    String userID = auth.getCurrentUser().getUid();
                                    ModelUser modelUser = new ModelUser("streloy", "Md. Mobinur Rahman Saim", "I am Developer.", email, "+8801711415554", "Male", "Default", "Default");
                                    mDatabase.child(userID).setValue(modelUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                progressBar_reg.setVisibility(View.GONE);
                                                startActivity(new Intent(Signup.this, MainActivity.class));
                                                finish();
                                            } else {
                                                Toast.makeText(Signup.this, "Authentication failed." + task.getException(),Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });



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
