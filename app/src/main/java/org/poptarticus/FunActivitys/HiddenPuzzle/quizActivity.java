package org.poptarticus.FunActivitys.HiddenPuzzle;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.poptarticus.FunActivitys.MainActivitys.homeScreen;
import org.poptarticus.FunActivitys.R;

public class quizActivity extends Activity {
	
	
	
	AdView mAdView;
	
	int mScoreToWin = 4;
	
	private questionLibrary mQuestionLibrary = new questionLibrary();
	
	private TextView scoreTextView;
	
	private TextView quizTextView;
	
	private Button quizAnswerButton1;
	
	private Button quizAnswerButton2;
	
	private Button quizAnswerButton3;
	
	private Button quizAnswerButton4;
	
	private Button startQuizButton;
	
	private Button tryAgainButton;
	
	private String mAnswer;
	
	private int mScore = 0;
	
	private int mQuestionNumber = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		
		scoreTextView = findViewById(R.id.quizScoreTextView);
		quizTextView = findViewById(R.id.quizTextView);
		quizAnswerButton1 = findViewById(R.id.quizAnswerButton1);
		quizAnswerButton2 = findViewById(R.id.quizAnswerButton2);
		quizAnswerButton3 = findViewById(R.id.quizAnswerButton3);
		quizAnswerButton4 = findViewById(R.id.quizAnswerButton4);
		startQuizButton = findViewById(R.id.startQuizButton);
		tryAgainButton = findViewById(R.id.tryAgainButton);
		
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
		
		//Make Text View Display Quiz challenge string
		quizTextView.setText(R.string.quizTimeCongratulations);
		
		//hide All but one button until user clicks start
		quizAnswerButton1.setVisibility(View.GONE);
		quizAnswerButton2.setVisibility(View.GONE);
		quizAnswerButton3.setVisibility(View.GONE);
		quizAnswerButton4.setVisibility(View.GONE);
		startQuizButton.setVisibility(View.VISIBLE);
		
		//Start the quiz button by hiding start button
		startQuizButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View view) {
				
				//Hide start button and make all other buttons visible
				quizAnswerButton1.setVisibility(View.VISIBLE);
				quizAnswerButton2.setVisibility(View.VISIBLE);
				quizAnswerButton3.setVisibility(View.VISIBLE);
				quizAnswerButton4.setVisibility(View.VISIBLE);
				startQuizButton.setVisibility(View.GONE);
				tryAgainButton.setVisibility(View.GONE);
				
				//update Question method
				updateQuestion();
				updateScore(mScore);
				
				//Start of Button Listener for Button1
				quizAnswerButton1.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View view) {
						//My logic for Button goes in here
						
						if (quizAnswerButton1.getText() == mAnswer) {
							mScore = mScore + 1;
							updateScore(mScore);
							updateQuestion();
							//This line of code is options
							Toast.makeText(quizActivity.this, "correct", Toast.LENGTH_SHORT).show();
							
						} else {
							failedQuestion();
						}
					}
				});
				
				//End of Button Listener for Button1
				
				//Start of Button Listener for quizAnswerButton2
				quizAnswerButton2.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View view) {
						//My logic for Button goes in here
						
						if (quizAnswerButton2.getText() == mAnswer) {
							mScore = mScore + 1;
							updateScore(mScore);
							updateQuestion();
							//This line of code is options
							Toast.makeText(quizActivity.this, "correct", Toast.LENGTH_SHORT).show();
							
						} else {
							failedQuestion();
						}
					}
				});
				
				//End of Button Listener for quizAnswerButton2
				
				//Start of Button Listener for quizAnswerButton3
				quizAnswerButton3.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View view) {
						//My logic for Button goes in here
						
						if (quizAnswerButton3.getText() == mAnswer) {
							mScore = mScore + 1;
							updateScore(mScore);
							updateQuestion();
							//This line of code is optiona
							Toast.makeText(quizActivity.this, "correct", Toast.LENGTH_SHORT).show();
							
						} else {
							failedQuestion();
						}
					}
				});
				
				//End of Button Listener for quizAnswerButton3
				
				//Start of Button Listener for startQuizButton
				quizAnswerButton4.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View view) {
						//My logic for Button goes in here
						
						if (quizAnswerButton4.getText() == mAnswer) {
							mScore = mScore + 1;
							updateScore(mScore);
							updateQuestion();
							//This line of code is options
							Toast.makeText(quizActivity.this, "correct", Toast.LENGTH_SHORT).show();
							
						} else {
							failedQuestion();
						}
					}
				});
				
				//End of Button Listener for startQuizButton
				
			}
		});
		
	}
	
	private void updateQuestion() {
		
		quizTextView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
		quizAnswerButton1.setText(mQuestionLibrary.getChoice1(mQuestionNumber));
		quizAnswerButton2.setText(mQuestionLibrary.getChoice2(mQuestionNumber));
		quizAnswerButton3.setText(mQuestionLibrary.getChoice3(mQuestionNumber));
		quizAnswerButton4.setText(mQuestionLibrary.getChoice4(mQuestionNumber));
		
		mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
		mQuestionNumber++;
		
		System.out.println(mQuestionLibrary.mCorrectAnswers.length);
		
		if (mScore == mScoreToWin) {
			startActivity(new Intent(quizActivity.this, homeScreen.class));
		}
		
	}
	
	private void failedQuestion() {
		
		quizTextView.setText(R.string.failedTest);
		
		//hide All but one button until user clicks start
		quizAnswerButton1.setVisibility(View.GONE);
		quizAnswerButton2.setVisibility(View.GONE);
		quizAnswerButton3.setVisibility(View.GONE);
		quizAnswerButton4.setVisibility(View.GONE);
		startQuizButton.setVisibility(View.GONE);
		tryAgainButton.setVisibility(View.VISIBLE);
		
		//Start of Button Listener for tryAgainButton
		tryAgainButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				mQuestionNumber = 0;
				mScore = 0;
				
				//Hide start button and make all other buttons visible
				quizAnswerButton1.setVisibility(View.VISIBLE);
				quizAnswerButton2.setVisibility(View.VISIBLE);
				quizAnswerButton3.setVisibility(View.VISIBLE);
				quizAnswerButton4.setVisibility(View.VISIBLE);
				startQuizButton.setVisibility(View.GONE);
				tryAgainButton.setVisibility(View.GONE);
				
				//update Question method
				updateQuestion();
				updateScore(mScore);
				
			}
		});
		
		//End of Button Listener for tryAgainButton
		
	}
	
	private void updateScore(int point) {
		
		scoreTextView.setText("Score: " + mScore);
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
		Intent intent = new Intent(quizActivity.this, homeScreen.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
}