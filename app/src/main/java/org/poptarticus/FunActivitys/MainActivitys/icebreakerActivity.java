package org.poptarticus.FunActivitys.MainActivitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.poptarticus.FunActivitys.Book.allBooks;
import org.poptarticus.FunActivitys.R;

import java.util.Random;

public class icebreakerActivity extends Activity {
	
	
	
	int numberToShowHint = 0;
	
	AdView mAdView;
	
	private allBooks mAllBooks = new allBooks();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_icebreakers);
		
		final TextView icebreakerLabel = findViewById(R.id.icebreakerTextView);
		final TextView showNewIcebreakerTextView = findViewById(R.id.showNewIcebreakerTextView);
		final Button showIcebreakerButton = findViewById(R.id.showIcebreakerButton);
		
		Typeface myTypeFace = Typeface.createFromAsset(getAssets(), "Highjack.otf");
		icebreakerLabel.setTypeface(myTypeFace);
		
		numberToShowHint = 0;
		
		// This sets the text view when you open the puns activity
		int randomIndexOpen = new Random().nextInt(mAllBooks.mIcebreaker.length);
		String randomString = mAllBooks.mIcebreaker[randomIndexOpen];
		
		showNewIcebreakerTextView.setText(randomString);
		
		mAdView = findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		mAdView.loadAd(adRequest);
		
		View.OnClickListener listener = new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				numberToShowHint += 1;
				
				if (numberToShowHint == 40) {
					icebreakerLabel.setText(R.string.hiddenActivityHint);
					icebreakerLabel.setTextSize(20);
					icebreakerLabel.setGravity(Gravity.CENTER);
					
					new Handler().postDelayed(new Runnable() {
						
						@Override
						public void run() {
							
							icebreakerLabel.setText(R.string.Puns);
						}
					}, 2500);
				} else {
					// This sets the text view when the button is clicked
					int randomIndex = new Random().nextInt(mAllBooks.mIcebreaker.length);
					String randomString = mAllBooks.mIcebreaker[randomIndex];
					
					showNewIcebreakerTextView.setText(randomString);
				}
			}
			
		};
		showIcebreakerButton.setOnClickListener(listener);
		
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
		Intent intent = new Intent(icebreakerActivity.this, homeScreen.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
}
