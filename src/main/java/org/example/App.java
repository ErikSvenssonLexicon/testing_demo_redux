package org.example;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main( String[] args )
    {
        String theVerySecretWord = "java";
        boolean running = true;
        while (running){
            System.out.println("You you want to play? (y/n)");
            String answer = SCANNER.nextLine().trim();

            if(answer.equalsIgnoreCase("y")){
                boolean winner = play(theVerySecretWord);
                if(winner){
                    System.out.println("Congrats you won WOO!");
                }else {
                    System.out.println("You lost");
                }
            }else {
                running = false;
            }
        }
    }

    private static boolean play(String theWord) {
        StringRepo.reset();
        StringRepo.initialize(theWord);
        boolean isWin = false;
        int maxGuesses = 6;
        int guessNumber = 1;
        boolean running = true;
        while(running){
            System.out.println("Guess " + guessNumber + " of " + maxGuesses);
            System.out.println(StringRepo.getCorrectGuesses());
            System.out.print("Please make a guess: ");
            String guess = SCANNER.nextLine();
            if(guess.length() > 1){
                System.out.println(StringRepo.guess(guess.trim()));
            }else{
                char charGuess = guess.charAt(0);
                System.out.println(StringRepo.guess(charGuess));
            }
            guessNumber++;

            isWin = StringRepo.isWin(StringRepo.getCorrectGuesses()) && guessNumber <= maxGuesses;
            if(isWin){
                running = false;
            }else if(guessNumber > maxGuesses){
                running = false;
            }
        }
        return isWin;
    }
}
