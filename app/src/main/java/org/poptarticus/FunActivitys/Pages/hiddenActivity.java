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

import org.poptarticus.FunActivitys.R;

import java.util.Random;

public class hiddenActivity extends Activity {

    public static final String TAG = hiddenActivity.class.getSimpleName();

    private AdView mAdView;

    private allBooks mAllBooks = new allBooks();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden);

        final TextView dadJokeLabel = findViewById(R.id.dadJokeTextView);
        final TextView showNewDadJokeTextView = findViewById(R.id.showNewDadJokeTextView);
        final Button showNewDadJokeButton = findViewById(R.id.showNewDadJokeButton);


        Typeface myTypeFace = Typeface.createFromAsset(getAssets(), "Highjack.otf");
        dadJokeLabel.setTypeface(myTypeFace);


        // This sets the text view when you open the puns activity
        int randomIndexOpen = new Random().nextInt(mAllBooks.mDadJoke.length);
        final String randomString = mAllBooks.mDadJoke[randomIndexOpen];

        showNewDadJokeTextView.setText(randomString);


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // This sets the text view when the button is clicked
                int randomIndex = new Random().nextInt(mAllBooks.mDadJoke.length);
                String randomString = mAllBooks.mDadJoke[randomIndex];

                showNewDadJokeTextView.setText(randomString);


            }

        };
        showNewDadJokeButton.setOnClickListener(listener);
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

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent intent = new Intent(hiddenActivity.this, homeScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}
