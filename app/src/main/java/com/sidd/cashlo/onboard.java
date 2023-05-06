package com.sidd.cashlo;

import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;



import java.util.ArrayList;
import java.util.List;

public class onboard extends AppCompatActivity {
    OnboardingAdapter onboardingAdapter;
    LinearLayout layoutOnboardingIndicator;
    Button buttonOnboardingAction;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);
        SharedPreferences Preferences=getSharedPreferences("PREFERENCE",MODE_PRIVATE);
        String FirstTime=Preferences.getString("FirstTimeInstall","");

        if(FirstTime.equals("Yes")){
            Intent intent=new Intent(onboard.this,Home_Activity.class);
            startActivity(intent);
            finish();

        }
        else {
            SharedPreferences.Editor editor=Preferences.edit();
            editor.putString("FirstTimeInstall","Yes");
            editor.apply();
        }

        layoutOnboardingIndicator=findViewById(R.id.layoutOnboardingIndicators);
         buttonOnboardingAction=findViewById(R.id.btnGetStarted);


        setOnboardingItems();
       final ViewPager2 onboardingViewpager=findViewById(R.id.onboardingViewpager);
        onboardingViewpager.setAdapter(onboardingAdapter);
        setupOnboardingIndicators();
        setCurrentOnboardingIndicators(0);


        onboardingViewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicators(position);
            }
        });

        buttonOnboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onboardingViewpager.getCurrentItem()+1<onboardingAdapter.getItemCount()) {
                    onboardingViewpager.setCurrentItem(onboardingViewpager.getCurrentItem() + 1);


                }
                else {
                    Intent iHome=new Intent(onboard.this,Register.class);
                    startActivity(iHome);
                    finish();

                    finish();
                }
            }
        });

    }
    private void setOnboardingItems(){
        List<OnboardingItems> onboardingItems=new ArrayList<>();
        OnboardingItems detail= new OnboardingItems();
        detail.setTitle("Cash Lo");
        detail.setDescription("Earn gift cards|Paytm cash");
        detail.setImage(R.drawable.screen1);

        OnboardingItems details2=new OnboardingItems();
        details2.setTitle("Earn easy money");
        details2.setDescription("Convert your Coins In Rewards");
        details2.setImage((R.drawable.screen2));

        OnboardingItems details3=new OnboardingItems();
        details3.setTitle("Refer friend ");
        details3.setDescription("Add Friends to earn more");
        details3.setImage((R.drawable.screen3));

        onboardingItems.add(detail);
        onboardingItems.add(details2);
        onboardingItems.add(details3);

        onboardingAdapter=new OnboardingAdapter(onboardingItems);

    }
    private void setupOnboardingIndicators(){
        ImageView[] indicators=new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for(int i=0;i<indicators.length;i++){
            indicators[i]=new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicators_inactive

            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicator.addView(indicators[i]);
        }
    }
    private void setCurrentOnboardingIndicators(int index){
        int childCount=layoutOnboardingIndicator.getChildCount();
        for(int i=0;i<childCount;i++){
            ImageView imageView=(ImageView) layoutOnboardingIndicator.getChildAt(i);
            if(i==index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(
                                getApplicationContext(),R.drawable.onboarding_indicators_activity)
                );
            }
            else{
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        getApplicationContext(),R.drawable.onboarding_indicators_inactive));
            }
        }

    }
}