package com.sidd.cashlo;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;

public class FetchdetailsActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    TextView name, Email;
    ImageView img;
    EditText number, Refer;
    Button button;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    FirebaseStorage storage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetchdetails);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        storage= FirebaseStorage.getInstance();
        name = findViewById(R.id.name);
        Email = findViewById(R.id.Email);
        button = findViewById(R.id.Submit);
        number = findViewById(R.id.MObileNumber);
        Refer = findViewById(R.id.Referral);
        img=findViewById(R.id.imageView3);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        gsc = GoogleSignIn.getClient(this, gso);



        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if (account != null) {
            Glide.with(this).load(account.getPhotoUrl()).into(img);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String Name = account.getDisplayName();
                    String Mail = account.getEmail();
                    String Number = number.getText().toString();
                    String refer = Refer.getText().toString();


                    name.setText(Name);
                    Email.setText(Mail);
                    if (Number.isEmpty()){
                        number.setError(("Required"));
                        return;


                    }
                    if (refer.isEmpty()){
                        Refer.setText("optional");
                    }

                    String ref=Mail.substring(0,Mail.lastIndexOf("@"));
                    String Refer=ref.replace(".","");

                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("name", Name);
                    hashMap.put("Email", Mail);
                    hashMap.put("number", Number);
                    hashMap.put("Refer", Refer);
                    hashMap.put("img","https://firebasestorage.googleapis.com/v0/b/cashlo-e8e5e.appspot.com/o/placeholder.png?alt=media&token=cdfee235-4822-4da9-b275-642773743431");

                    databaseReference.child("Users")
                            .child(Name)
                            .setValue(hashMap)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(FetchdetailsActivity.this, "Data Collected Sucessfully", Toast.LENGTH_SHORT).show();
                                    finish();
                                    Intent intent = new Intent(getApplicationContext(), Home_Activity.class);
                                    startActivity(intent);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(FetchdetailsActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    finish();
                                    Intent intent = new Intent(getApplicationContext(), Register.class);
                                    startActivity(intent);
                                }
                            });

                }
            });
        }


    }
}
