package com.sidd.cashlo.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import com.sidd.cashlo.R;
import com.sidd.cashlo.Register;
import com.sidd.cashlo.databinding.FragmentProfileBinding;
import com.squareup.picasso.Picasso;


public class ProfileFragment extends Fragment {


    ImageView Profile;
    ImageView button;

    TextView name,Email;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    FirebaseStorage storage;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    FragmentProfileBinding binding;

    public ProfileFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        name=view.findViewById(R.id.name);
        Email=view.findViewById(R.id.Email);
        button=view.findViewById(R.id.termcondition5);
        Profile=view.findViewById(R.id.profile);

        firebaseDatabase = FirebaseDatabase.getInstance();

        auth = FirebaseAuth.getInstance();

        user = auth.getCurrentUser();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        gsc = GoogleSignIn.getClient(getActivity(), gso);




        if(user!=null) {

            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());

            if (account != null) {
                Glide.with(this).load(account.getPhotoUrl()).into(Profile);
                        String Name = account.getDisplayName();
                        String Mail = account.getEmail();

                name.setText(Name);
                Email.setText(Mail);


                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        FirebaseAuth.getInstance().signOut();
                        Intent intent=new Intent(ProfileFragment.this.getActivity(),Register.class);
                        startActivity(intent);
                        getActivity().finish();

                    }
                });


            }

        }








            return view;
        }




    }







