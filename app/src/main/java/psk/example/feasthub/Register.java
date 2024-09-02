package psk.example.feasthub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.feasthub.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    ImageView back;
    TextView login;
    EditText pass, conpass, editemail, editpassword,username;


    CheckBox checkBox;

    Button signupbtn;

    FirebaseAuth auth;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        back = findViewById(R.id.backicon);
        login = findViewById(R.id.alreadytxt);
        checkBox = findViewById(R.id.chkbox);
        pass = findViewById(R.id.regpass);
        conpass = findViewById(R.id.regconfpass);

        editemail = findViewById(R.id.regemail);
        editpassword = findViewById(R.id.regpass);
        signupbtn = findViewById(R.id.SignUpbtn);

        username = findViewById(R.id.regusername);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                email = String.valueOf((editemail.getText()));
                password = String.valueOf((editpassword.getText()));

                //usernm = username.getText().toString();

                String usernm = username.getText().toString();

                StringHolder.getInstance().setMyString(usernm);

               // StringHolder.getInstance().setMystring2(usernm);

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Register.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Register.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent1 = new Intent(Register.this, Login.class);
                                    startActivity(intent1);
                                    finish();
                                } else {
                                    Toast.makeText(Register.this, "Authentication Failed", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    pass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    conpass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    pass.setInputType(129);
                    conpass.setInputType(129);
                }
            }

        });


    }
}