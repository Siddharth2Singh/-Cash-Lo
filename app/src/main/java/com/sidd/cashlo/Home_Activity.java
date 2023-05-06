package com.sidd.cashlo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.sidd.cashlo.Fragments.HomeFragment;
import com.sidd.cashlo.Fragments.ProfileFragment;
import com.sidd.cashlo.Fragments.ReedemFragment;
import com.sidd.cashlo.Fragments.ReferFragment;
import com.sidd.cashlo.Fragments.TaskOfferFragment;


public class Home_Activity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment=new HomeFragment();
    TaskOfferFragment taskOfferFragment=new TaskOfferFragment();
    ReferFragment referFragment=new ReferFragment();
    ReedemFragment reedemFragment=new ReedemFragment();
    ProfileFragment profileFragment=new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView=findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {

                switch (item.getItemId()){
                    case R.id.homeid:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();

                        return true;

                    case R.id.TaskOffer:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,taskOfferFragment).commit();
                        return true;
                    case R.id.ReferNow:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,referFragment).commit();
                        return true;
                    case R.id.Reedem:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,reedemFragment).commit();
                        return true;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,profileFragment).commit();
                        return true;

                }
                return false;

            }
        });
    }
}