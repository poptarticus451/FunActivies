package org.poptarticus.FunActivitys.MainActivitys;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import org.poptarticus.FunActivitys.R;

import java.util.Objects;

public class homeScreen extends Fragment {
	
	
	
	//Back button message and timer
	private boolean doubleBackToExitPressedOnce = false;
	
	private int number = 0;
	
	@Override
	public View onCreateView(
			LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState
	) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.activity_home_screen, container, false);
	}
	
	public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			
			Objects.requireNonNull(getActivity()).getWindow().setStatusBarColor(getActivity().getColor(R.color.homeScreenStatusBar));
		}
		
		super.onViewCreated(view, savedInstanceState);
		
		view.findViewById(R.id.goFunPunsButton).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				NavHostFragment.findNavController(homeScreen.this)
						.navigate(R.id.action_homeScreen_to_punsActivity);
			}
		});
		
		view.findViewById(R.id.goIceBreakers).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				NavHostFragment.findNavController(homeScreen.this)
						.navigate(R.id.action_homeScreen_to_icebreakerActivity);
			}
		});
		
		view.findViewById(R.id.goFunFacts).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				NavHostFragment.findNavController(homeScreen.this)
						.navigate(R.id.action_homeScreen_to_factActivity);
			}
		});
		
		view.findViewById(R.id.hiddenMenuButton).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				number += 1;
				
				new Handler().postDelayed(new Runnable() {
					
					@Override
					public void run() {
						
						number = 0;
					}
				}, 500);
				
				if (number == 3) {
					NavHostFragment.findNavController(homeScreen.this)
							.navigate(R.id.action_homeScreen_to_hiddenActivity);
				}
			}
		});
	}
	
}
