package org.example;

import java.util.Arrays;

public class StringRepo {

    public static final String YOU_HAVE_ALREADY_GUESSED = "You have already guessed ";
    public static final String MADE_A_GOOD_GUESS_WORD_CONTAINS = "You made a good guess word contains ";
    public static final String BAD_GUESS_THE_WORD_DONT_CONTAINS = "You made a bad guess the word doesnt contains ";
    public static final String I_DONT_ACCEPT_NULL = "I dont accept null";
    public static final String YOU_GUESSED_CORRECTLY = "You guessed correctly";
    public static final String YOU_FAILED_TO_GUESS_THE_CORRECT_WORD = "You failed to guess the correct word";


    private static String theWord;
    private static char[] correctGuesses;

    private static char[] guesses;

    public static void initialize(String string){
        theWord = string;
        correctGuesses = new char[theWord.length()];
        Arrays.fill(correctGuesses, '_');
        guesses = new char[0];

    }

    public static String madeGuesses(){
        return Arrays.toString(guesses);
    }

    public static String getCorrectGuesses(){
        return String.copyValueOf(correctGuesses);
    }

    public static void reset(){
        theWord = null;
        correctGuesses = null;
        guesses = null;
    }



    public static String guess(char guess){
        if(guessIsDuplicate(guess)){
            return YOU_HAVE_ALREADY_GUESSED + guess;
        }

        updateGuesses(guess);

        if(correctGuess(guess)){
            updateCorrectGuesses(guess);

            return MADE_A_GOOD_GUESS_WORD_CONTAINS + guess;
        }

        return BAD_GUESS_THE_WORD_DONT_CONTAINS + guess;
    }

    private static void updateGuesses(char guess) {
        char[] temp = Arrays.copyOf(guesses, guesses.length +1);
        temp[temp.length-1] = guess;
        guesses = temp;
    }

    private static void updateCorrectGuesses(char guess) {
        for(int i=0 ; i<theWord.length(); i++){
            char letter = theWord.charAt(i);
            if(letter == guess){
                correctGuesses[i] = letter;
            }
        }
    }

    private static boolean correctGuess(char guess) {
        for(char c : theWord.toCharArray()){
            if(String.valueOf(c).equalsIgnoreCase(String.valueOf(guess))){
                return true;
            }
        }
        return false;
    }

    private static boolean guessIsDuplicate(char guess) {
        for(char c : guesses){
            if(guess == c){
                return true;
            }
        }
        return false;
    }

    public static String guess(String guess){
        if(guess == null) return I_DONT_ACCEPT_NULL;
        if(isWin(guess)){
            correctGuesses = theWord.toCharArray();
            return YOU_GUESSED_CORRECTLY;
        }
        return YOU_FAILED_TO_GUESS_THE_CORRECT_WORD;
    }

    public static boolean isWin(String string) {
        return string.equalsIgnoreCase(theWord);
    }


}
