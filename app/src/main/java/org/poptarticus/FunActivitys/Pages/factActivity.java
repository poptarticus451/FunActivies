package org.poptarticus.FunActivitys.Pages;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.poptarticus.FunActivitys.Books.allBooks;
import org.poptarticus.FunActivitys.R;

import java.util.Random;

public class factActivity extends Activity {
    public static final String TAG = factActivity.class.getSimpleName();

    //Define the ad view
    private AdView mAdView;

    //private factBook mFactBook = new factBook();
    private allBooks mAllBooks = new allBooks();

    //when app opens run this code
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fact);

        //Intestate the buttons and labels
        final TextView factLabel = findViewById(R.id.funFactTextView);
        final TextView newFunFactTextView = findViewById(R.id.showNewFactTextView);
        final Button showFactButton = findViewById(R.id.showFactButton);

        //Change label to different font
        Typeface myTypeFace = Typeface.createFromAsset(getAssets(), "Highjack.otf");
        factLabel.setTypeface(myTypeFace);


        // This sets the text view when you open the puns activity
        int randomIndexOpen = new Random().nextInt(mAllBooks.mFact.length);
        String randomStringOpen = mAllBooks.mFact[randomIndexOpen];

        //when app gets opened change text to random array variable
        newFunFactTextView.setText(randomStringOpen);

        //add the ad on create
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // This sets the text view when the button is clicked
                int randomIndex = new Random().nextInt(mAllBooks.mFact.length);
                String randomString = mAllBooks.mFact[randomIndex];

                //on button click change text
                newFunFactTextView.setText(randomString);

            }

        };
        showFactButton.setOnClickListener(listener);

        //Toast.makeText(this, "Yay! Our Activity was created!", Toast.LENGTH_LONG).show();
        Log.d(TAG, "We're logging from the onCreate() method!");
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        // making notification bar transparent
        changeStatusBarColor();

    }


    //Making notification bar transparent

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent intent = new Intent(factActivity.this, homeScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
