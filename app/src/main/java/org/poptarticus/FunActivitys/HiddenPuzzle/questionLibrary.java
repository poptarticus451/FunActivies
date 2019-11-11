package org.poptarticus.FunActivitys.HiddenPuzzle;

public class questionLibrary {
	
	
	
	public String[] mCorrectAnswers = {"Answer", "Answer", "Answer", "Answer", "Answer"};
	
	public String[] mQuestions = {
			"Which part of the plant holds it in the soil?",
			"This part of the plant absorbs energy from the sun.",
			"This part of the plant attracts bees, butterflies and hummingbirds.",
			"The _______ holds the plant upright.",
			"This question is here so the app wont crash"
		
	};
	
	public String[][] mChoices = {
			{"Answer", "Stem", "Flower", "Cocaine"},
			{"Answer", "Leaves", "Seeds", "Hey"},
			{"Answer", "Flower", "Roots", "Hey"},
			{"Answer", "Leaves", "Stem", "Hey"},
			{"Answer", "Leaves", "Stem", "Hey"}
	};
	
	public String getQuestion(int a) {
		
		String question = mQuestions[a];
		return question;
	}
	
	public String getChoice1(int a) {
		
		String choice0 = mChoices[a][0];
		return choice0;
	}
	
	public String getChoice2(int a) {
		
		String choice1 = mChoices[a][1];
		return choice1;
	}
	
	public String getChoice3(int a) {
		
		String choice2 = mChoices[a][2];
		return choice2;
	}
	
	public String getChoice4(int a) {
		
		String choice3 = mChoices[a][3];
		return choice3;
	}
	
	public String getCorrectAnswer(int a) {
		
		String answer = mCorrectAnswers[a];
		return answer;
	}
	
}
