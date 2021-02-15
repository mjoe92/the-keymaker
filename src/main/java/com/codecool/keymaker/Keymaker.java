package com.codecool.keymaker;

import java.util.Arrays;
import java.util.Scanner;

public class Keymaker {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = input.nextLine().toLowerCase();
        System.out.println("Your key: " + hashIt(name));
    }

    private static String hashIt(String word) {
        /*
         * hashIt("morpheus") -> "trowdo"
         */
        String padded = padUpTo(word, 15, 19);
        String elogenated = zigZagConcatenate(createMatrix(padded, abcMirror(padded)));
        String rotated = rotateRight(elogenated, 3000003);
        String cherryPicked = getSquareIndexChars(rotated);
        String halved = removeOddBlocks(cherryPicked, 3);
        String key = reduceToFixedWord(halved, 6);

        return key;
    }

    private static String shiftCharacters(String word, int shift) {
        /*
         * shiftCharacters("abby", 5) -> "fggd"
         */
        // TODO implement

        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        char[] alphabetInChars = alphabet.toCharArray();
        char[] wordInChars = word.toCharArray();
        int indexShiftedChar;
        for (int i = 0; i < word.length(); i++) {
            indexShiftedChar = (alphabet.indexOf(wordInChars[i]) + shift) % 26;
            wordInChars[i] = alphabetInChars[indexShiftedChar];
        }
        word = String.valueOf(wordInChars);
        return word;

        //throw new UnsupportedOperationException("Not implemented yet");
    }

    private static String padUpTo(String word, int shift, int n) {
        /*
         * padUpTo("abb", 5, 11) -> "abbfggkllpq"
         */
        // TODO implement

        double numberOfShifts = Math.ceil((double) n / word.length());
        String nextWord = word;
        for (int i = 0; i < numberOfShifts; i++) {
            nextWord = shiftCharacters(nextWord, shift);
            word += nextWord;
        }
        return word.substring(0, n);

        //throw new UnsupportedOperationException("Not implemented yet");
    }

    private static String abcMirror(String word) {
        /*
         * abcMirror("abcd") -> "zyxw"
         */
        // TODO implement

        char[] wordInChars = word.toCharArray();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String wordMirror = "";
        char wordCharMirrored;
        for (int i = 0; i < word.length(); i++) {
            wordCharMirrored = alphabet.charAt(25 - alphabet.indexOf(wordInChars[i]));
            wordMirror += wordCharMirrored;
        }

        return wordMirror;
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    private static String[] createMatrix(String word1, String word2) {
        /*
         * createMatrix("mamas", "papas") -> ["bpbph", "mamas", "bpbph", "mamas", "esesk"]
         */
        // TODO implement

        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] shifts = new int[word2.length()];
        String[] shiftedWordArray = new String[word2.length()];
        for (int i = 0; i < word2.length(); i++) {
            shifts[i] = alphabet.indexOf(word2.charAt(i));
            shiftedWordArray[i] = shiftCharacters(word1, shifts[i]);
        }

        return shiftedWordArray;
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    private static String zigZagConcatenate(String[] matrix) {
        /*
         * zigZagConcatenate(new String[] {"abc", "def", "ghi", "jkl"}) -> "adgjkhebcfil"
         */
        // TODO implement

        String wordInZigCon = "";
        for (int i = 0; i < matrix[0].length(); i++) {
            for (int j = 0; j < matrix.length; j++) {
                wordInZigCon += matrix[j].charAt(i);
            }
            i++;
            if (i < matrix[0].length()) {
                for (int j = matrix.length - 1; 0 <= j; j--) {
                    wordInZigCon += matrix[j].charAt(i);
                }
            }
        }
        return wordInZigCon;
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    private static String rotateRight(String word, int n) {
        /*
         * rotateRight("abcdefgh", 3) -> "fghabcde"
         */
        // TODO implement

        n = n % word.length();
        if (n < 0) {
            return word.substring(-n) + word.substring(0, -n);
        } else {
            return word.substring(word.length() - n) + word.substring(0, word.length() - n);
        }


        //throw new UnsupportedOperationException("Not implemented yet");
    }

    private static String getSquareIndexChars(String word) {
        /*
         * getSquareIndexChars("abcdefghijklm") -> "abej"
         */
        // TODO implement

        String newWord = "";
        for (int i = 0; i < word.length(); i++) {
            if (Math.pow((int)Math.sqrt(i),2) == i) {
                newWord += word.charAt(i);
            }
        }
        return newWord;

        //throw new UnsupportedOperationException("Not implemented yet");
    }

    private static String removeOddBlocks(String word, int blockLength) {
        /*
         * removeOddBlocks("abcdefghijklm", 3) -> "abcghim"
         */
        // TODO implement

        String newWord = "";
        // (i / blockLength) % 2 == 0
        for (int i = 0; i < word.length(); i++) {
            if ((i / blockLength) % 2 == 0) {
                newWord += word.charAt(i);
            }
        }

        return newWord;

        //throw new UnsupportedOperationException("Not implemented yet");
    }

    private static String reduceToFixedWord(String word, int n) {
        /*
         * reduceToFixed("abcdefghijklm", 6) -> "bafedc"
         */
        // TODO implement
        String newWord = "";
        word = rotateRight(word.substring(0, n), -n / 3);
        for (int i = word.length() - 1; 0 <= i; i--) {
            newWord += word.charAt(i);
        }
        return newWord;

        //throw new UnsupportedOperationException("Not implemented yet");
    }

}
