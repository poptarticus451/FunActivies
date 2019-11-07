package org.poptarticus.FunActivitys.MainActivitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

import org.poptarticus.FunActivitys.R;


public class homeScreen extends Activity {

    //Backbutton message and timer
    boolean doubleBackToExitPressedOnce = false;
    int number = 0;
    private FirebaseAnalytics mFirebaseAnalytics;
    // Create an anonymous implementation of OnClickListener
    private View.OnClickListener hiddenActivity = new View.OnClickListener() {
        public void onClick(View v) {
            // do something when the button is clicked

            number += 1;

            System.out.println(number);

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    number = 0;
                }
            }, 2500);

            if (number == 3) {
                // Start NewActivity.class
                Intent myIntent = new Intent(homeScreen.this, org.poptarticus.FunActivitys.HiddenPuzzle.hiddenActivity.class);
                startActivity(myIntent);

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Typeface myTypeFace = Typeface.createFromAsset(getAssets(), "Highjack.otf");
        Button hiddenButton = findViewById(R.id.MenuButton);
        hiddenButton.setTypeface(myTypeFace);

        number = 0;

        hiddenButton.setOnClickListener(hiddenActivity);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        // making notification bar transparent
        changeStatusBarColor();

    }

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public void showPunsActivity(View view) {
        Intent intent = new Intent(this, punsActivity.class);
        startActivity(intent);
    }

    public void showIcebreakerActivity(View view) {
        Intent intent = new Intent(this, icebreakerActivity.class);
        startActivity(intent);
    }

    public void showFactActivity(View view) {
        Intent intent = new Intent(this, factActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


}
