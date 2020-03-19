package org.poptarticus.FunActivitys.MainActivitys;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.poptarticus.FunActivitys.Book.allBooks;
import org.poptarticus.FunActivitys.R;

import java.util.Objects;
import java.util.Random;

public class punsActivity extends Fragment {
	
	
	
	private int numberToShowHint = 0;
	
	private allBooks mAllBooks = new allBooks();
	
	// This sets the text view when you open the puns activity
	private int randomIndexOpen = new Random().nextInt(mAllBooks.mPuns.length);
	
	private String randomString = mAllBooks.mPuns[randomIndexOpen];
	
	//when app opens run this code
	@Override
	public View onCreateView(
			LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState
	) {
		
		numberToShowHint = 0;
		
		// Inflate the layout for this fragment
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_puns, null);
		
		TextView showPunTextView = root.findViewById(R.id.showPunTextView);
		
		showPunTextView.setText(randomString);
		
		//add the ad on create
		AdView mAdView = root.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		mAdView.loadAd(adRequest);
		
		return root;
	}
	
	public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			
			Objects.requireNonNull(getActivity()).getWindow().setStatusBarColor(getActivity().getColor(R.color.punsScreenStatusBar));
		}
		
		super.onViewCreated(view, savedInstanceState);
		
		final TextView punsTitle = view.findViewById(R.id.punsTitle);
		final TextView showPunTextView = view.findViewById(R.id.showPunTextView);
		
		view.findViewById(R.id.showPunButton).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				numberToShowHint += 1;
				
				if (numberToShowHint == 25) {
					punsTitle.setText(R.string.hiddenActivityHint);
					punsTitle.setTextSize(20);
					punsTitle.setGravity(Gravity.CENTER);
					
					new Handler().postDelayed(new Runnable() {
						
						@Override
						public void run() {
							
							punsTitle.setText(R.string.Puns);
						}
					}, 2500);
				} else {
					// This sets the text view when the button is clicked
					int randomIndex = new Random().nextInt(mAllBooks.mPuns.length);
					String randomString = mAllBooks.mPuns[randomIndex];
					
					//on button click change text
					showPunTextView.setText(randomString);
				}
			}
			
		});
		
	}
	
}