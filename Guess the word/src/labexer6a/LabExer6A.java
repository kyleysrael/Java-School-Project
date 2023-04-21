package labexer6a;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class LabExer6A {

    private static final String WORDS_FILE = "words.txt";

    private static ArrayList<String> wordsList = new ArrayList<>();

    private static String selectedWord;
    private static char[] currentGuess;

    public static void main(String[] args) {
        loadWords();
        selectWord();
        initializeGuess();
        playGame();
    }

    private static void loadWords() {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/hello/OneDrive/Documents/NetBeansProjects/LabExer6A/src/labexer6a/words.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                wordsList.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void selectWord() {
        Random random = new Random();
        selectedWord = wordsList.get(random.nextInt(wordsList.size()));
    }

    private static void initializeGuess() {
        currentGuess = new char[selectedWord.length()];
        for (int i = 0; i < selectedWord.length(); i++) {
            currentGuess[i] = '?';
        }
    }

private static void playGame() {
    Scanner scanner = new Scanner(System.in);
    int attempts = 0;
    while (true) {
        System.out.println("Current guess: " + String.valueOf(currentGuess));
        System.out.print("Guess a letter: ");
        if (!scanner.hasNext()) {
            System.out.println("Invalid input!");
            scanner.nextLine();
            continue;
        }
        char guess = scanner.nextLine().charAt(0);
        boolean correctGuess = false;
        for (int i = 0; i < selectedWord.length(); i++) {
            if (selectedWord.charAt(i) == guess) {
                currentGuess[i] = guess;
                correctGuess = true;
            }
        }
        if (correctGuess) {
            System.out.println("Correct guess!");
            if (String.valueOf(currentGuess).equals(selectedWord)) {
                System.out.println("Congratulations! You guessed the word!");
                break;
            }
        } else {
            System.out.println("Incorrect guess!");
            attempts++;
            if (attempts >= 6) {
                System.out.println("Game over! You ran out of attempts.");
                break;
            }
        }
    }
}

}
