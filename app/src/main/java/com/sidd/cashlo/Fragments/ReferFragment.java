package com.sidd.cashlo.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sidd.cashlo.R;


public class ReferFragment extends Fragment {

    TextView Copycodetv,refer,Cointv;
    Button Invitenow,redeemtv;

    private FirebaseAuth auth;
    private FirebaseUser user;

    private DatabaseReference reference;

    public ReferFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_refer, container, false);

        refer=view.findViewById(R.id.refer_code);
        Copycodetv=view.findViewById(R.id.copycode);
        Cointv=view.findViewById(R.id.coins);
        Invitenow=view.findViewById(R.id.button);
        redeemtv=view.findViewById(R.id.reedem);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference().child("Users");



        loadData();
        clickListner();

        return view;
    }
    public void  loadData(){
        reference.child(user.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String Refer=snapshot.child("Refer").getValue(String.class);
                         refer.setText(Refer);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getActivity(), "Error:"+error.getMessage(), Toast.LENGTH_SHORT).show();
                         getActivity().finish();
                    }
                });


    }

    public void clickListner(){
        Invitenow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Refer=refer.getText().toString();

                String sharebuddy="Hey there ,have you heard about awesome app. Join using my refer code to instantly get 120"+"" +
                        " coins.My invite code "+Refer+"\n"+
                        "Download from Play Store\n"+
                        "https://play.google.com/store/apps/details?id+"+
                        getActivity().getPackageName();

                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,sharebuddy);
                startActivity(intent);
            }
        });
        redeemtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText=new EditText(getActivity());
                editText.setText("abc12");
            }
        });
    }



}