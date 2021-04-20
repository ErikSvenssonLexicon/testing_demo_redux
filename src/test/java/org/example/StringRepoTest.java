package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringRepoTest {

    @BeforeEach
    void setUp() {
        StringRepo.reset();
        StringRepo.initialize("java");
    }

    @Test
    @DisplayName("given guess guess() return expected message and updates internal state correctly")
    void guess() {
        char guess = 'j';
        String expected = StringRepo.MADE_A_GOOD_GUESS_WORD_CONTAINS + guess;
        String expectedGuesses = "[j]";
        String expectedCorrectGuesses = "j___";

        String actualMessage = StringRepo.guess(guess);

        assertEquals(expected, actualMessage);
        assertEquals(expectedGuesses, StringRepo.madeGuesses());
        assertEquals(expectedCorrectGuesses, StringRepo.getCorrectGuesses());

    }

    @Test
    @DisplayName("Given guess guess() return expected message and guess is not added")
    void guess_duplicate() {
        StringRepo.guess('a');
        char guess = 'a';
        String expectedMessage = StringRepo.YOU_HAVE_ALREADY_GUESSED + guess;
        String expectedGuesses = "[a]";
        String expectedCorrectGuesses = "_a_a";

        String actualMessage = StringRepo.guess(guess);

        assertEquals(expectedMessage, actualMessage);
        assertEquals(expectedGuesses, StringRepo.madeGuesses());
        assertEquals(expectedCorrectGuesses, StringRepo.getCorrectGuesses());
    }

    @Test
    @DisplayName("Given guess guess() return expected message and updates internal state correctly")
    void guess_char_incorrect() {
        char guess = 'h';
        String expectedMessage= StringRepo.BAD_GUESS_THE_WORD_DONT_CONTAINS + guess;
        String expectedGuesses = "[h]";
        String expectedCorrectGuesses = "____";

        String actualMessage = StringRepo.guess(guess);

        assertEquals(expectedMessage, actualMessage);
        assertEquals(expectedGuesses, StringRepo.madeGuesses());
        assertEquals(expectedCorrectGuesses, StringRepo.getCorrectGuesses());
    }

    @Test
    @DisplayName("Given correctGuess guess() return expected message")
    void guess_word_correct() {
        String correctGuess = "java";

        String expectedMessage = StringRepo.YOU_GUESSED_CORRECTLY;
        String expectedCorrectGuesses = "java";

        String actualMessage = StringRepo.guess(correctGuess);

        assertEquals(expectedMessage, actualMessage);
        assertEquals(expectedCorrectGuesses, StringRepo.getCorrectGuesses());
        assertTrue(StringRepo.isWin(expectedCorrectGuesses));
    }

    @Test
    @DisplayName("Given incorrectGuess guess() return expected message and updates nothing")
    void guess_word_incorrect() {
        String incorrectGuess = "fooBar";

        String expectedMessage = StringRepo.YOU_FAILED_TO_GUESS_THE_CORRECT_WORD;
        String expectedCorrectGuesses = "____";
        String expectedGuesses = "[]";

        String actualMessage = StringRepo.guess(incorrectGuess);

        assertEquals(expectedMessage, actualMessage);
        assertEquals(expectedCorrectGuesses, StringRepo.getCorrectGuesses());
        assertEquals(expectedGuesses, StringRepo.madeGuesses());
    }
}