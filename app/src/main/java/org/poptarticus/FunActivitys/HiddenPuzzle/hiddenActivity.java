package org.poptarticus.FunActivitys.HiddenPuzzle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.poptarticus.FunActivitys.R;

public class hiddenActivity extends Fragment {
	
	
	
	private AdView mAdView;
	
	private String passcode = "1234";
	
	@Override
	public View onCreateView(
			LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState
	) {
		
		// Inflate the layout for this fragment
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_hidden, null);
		
		TextView puzzleCongratulationsTextView = root.findViewById(R.id.puzzleCongratulationsTextView);
		
		//Set Text view to string
		puzzleCongratulationsTextView.setText(R.string.congratulations);
		
		//add the ad on create
		AdView mAdView = root.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		mAdView.loadAd(adRequest);
		
		return root;
	}
	
	public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
		
		super.onViewCreated(view, savedInstanceState);
		
		final TextView puzzleEditTextPasscode = view.findViewById(R.id.puzzleEditTextPasscode);
		final TextView puzzleCongratulationsTextView = view.findViewById(R.id.puzzleCongratulationsTextView);
		view.findViewById(R.id.puzzleEnterButton).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				//get text change to string then make sure it is equal to passcode var
				if (puzzleEditTextPasscode.getText().toString().equals(passcode)) {
					
					Intent intent = new Intent(getActivity(), quizActivity.class);
					startActivity(intent);
					
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
	
}

