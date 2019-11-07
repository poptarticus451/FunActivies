package org.poptarticus.FunActivitys.Pages;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.poptarticus.FunActivitys.R;


public class hiddenActivity extends Activity {

    AdView mAdView;
    String passcode = "1234";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden);


        final TextView puzzleCongratulationsTextView = findViewById(R.id.puzzleCongratulationsTextView);
        final EditText passcodeEditText = findViewById(R.id.puzzlePasscode);
        final Button enterButton = findViewById(R.id.enterButton);

        //Set Text view to string
        puzzleCongratulationsTextView.setText(R.string.congratulations);


        //Load Ad
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        // making notification bar transparent
        changeStatusBarColor();


        //enter button on clock listener
        enterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                //get text change to string then make sure it is equal to passcode var
                if (passcodeEditText.getText().toString().equals(passcode)) {

                    passcodeEditText.setHint(passcode);
                    //get edit text text and clear
                    passcodeEditText.getText().clear();

                    //Hide Buttons
                    puzzleCongratulationsTextView.setVisibility(View.GONE);
                    //passcodeEditText.setVisibility(View.GONE);
                    enterButton.setVisibility(View.GONE);


                } else {
                    puzzleCongratulationsTextView.setText(R.string.Wrong);

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            puzzleCongratulationsTextView.setText(R.string.congratulations);
                        }
                    }, 2500);
                }

            }
        });

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
