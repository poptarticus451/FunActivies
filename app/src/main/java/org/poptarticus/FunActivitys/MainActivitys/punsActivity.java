package org.poptarticus.FunActivitys.MainActivitys;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Explode;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.poptarticus.FunActivitys.Book.allBooks;
import org.poptarticus.FunActivitys.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class punsActivity extends AppCompatActivity {
	
	
	
	//number that will display certain text telling user about hidden activity
	int numberToShowHint = 0;
	
	AdView mAdView;
	
	private allBooks mAllBooks = new allBooks();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_puns);
		
		final TextView punLabel = findViewById(R.id.punsTextView);
		final TextView showNewPunTextView = findViewById(R.id.showNewPunTextView);
		final Button showPunButton = findViewById(R.id.showPunButton);
		
		getWindow().setExitTransition(new Explode());
		
		numberToShowHint = 0;
		
		Typeface myTypeFace = Typeface.createFromAsset(getAssets(), "Highjack.otf");
		punLabel.setTypeface(myTypeFace);
		
		// This sets the text view when you open the puns activity
		int randomIndexOpen = new Random().nextInt(mAllBooks.mPuns.length);
		final String randomString = mAllBooks.mPuns[randomIndexOpen];
		
		showNewPunTextView.setText(randomString);
		
		mAdView = findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		mAdView.loadAd(adRequest);
		
		View.OnClickListener listener = new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				numberToShowHint += 1;
				
				if (numberToShowHint == 50) {
					punLabel.setText(R.string.hiddenActivityHint);
					punLabel.setTextSize(20);
					punLabel.setGravity(Gravity.CENTER);
					
					new Handler().postDelayed(new Runnable() {
						
						@Override
						public void run() {
							
							punLabel.setText(R.string.Puns);
						}
					}, 2500);
					
				} else {
					// This sets the text view when the button is clicked
					int randomIndex = new Random().nextInt(mAllBooks.mPuns.length);
					System.out.println(randomIndex);
					String randomString = mAllBooks.mPuns[randomIndex];
					
					int n = mAllBooks.mPuns.length;
					//if n=100 means it give random numb with no duplicate values with in the range 100.
					Random r = new Random();
					Set<Integer> positionValue = new HashSet<>();
					for (int i = 0; i < n; i++) {
						while (true) {
							int number = r.nextInt(n) + 1;
							if (!positionValue.contains(number)) {
								positionValue.add(number);
								break;
							}
						}
					}
					
					showNewPunTextView.setText(randomString);
				}
			}
			
		};
		showPunButton.setOnClickListener(listener);
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
		Intent intent = new Intent(punsActivity.this, homeScreen.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
}