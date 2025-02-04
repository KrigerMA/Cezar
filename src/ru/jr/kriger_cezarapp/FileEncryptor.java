package ru.jr.kriger_cezarapp;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class FileEncryptor {

    private static final List<Character> ALPHABET = Alphabet.getAlphabet();
    private static final int ALPHABET_CAPACITY = Alphabet.getAlphabetCapacity();

    private FileEncryptor() {
    }

    public static void encryptFile(String inputFilePath, String outputFilePath, int encryptKey){
        try (FileReader fileReader = new FileReader(inputFilePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader);
             FileWriter fileWriter = new FileWriter(outputFilePath);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)){

            while (bufferedReader.ready()){
                String lineToEncrypt = bufferedReader.readLine();
                bufferedWriter.write(encryptSymbolsInString(lineToEncrypt, encryptKey));
            }
        } catch (IOException e) {
            System.out.println("Проблема с чтением данных из файла");
        }
    }

    public static void decryptFile(String inputFilePath, String outputFilePath, int encryptKey){
        try (FileReader fileReader = new FileReader(inputFilePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader);
             FileWriter fileWriter = new FileWriter(outputFilePath);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)){

            while (bufferedReader.ready()){
                String lineToEncrypt = bufferedReader.readLine();
                bufferedWriter.write(decryptSymbolsInString(lineToEncrypt, encryptKey));
            }
        } catch (IOException e) {
            System.out.println("Проблема с чтением данных из файла");
        }
    }

    public static void decryptWithBruteForce (String inputFilePath, String outputFilePath){
        try (FileReader fileReader = new FileReader(inputFilePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader);
             FileWriter fileWriter = new FileWriter(outputFilePath);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)){

            while (bufferedReader.ready()){
                String value = bufferedReader.readLine();
                for (int possibleKey = 1; possibleKey < ALPHABET_CAPACITY; possibleKey++) {
                    String decryptedString = decryptsSymbolsWithBF(value, possibleKey);
                    bufferedWriter.write(decryptedString);
                }
            }

        } catch (IOException e) {
            System.out.println("Проблема с чтением данных из файла");
        }
    }


    private static String encryptSymbolsInString(String stringToEncrypt, int encryptKey) {

        StringBuilder stringBuilder = new StringBuilder();
        char[] charsToEncrypt = getCharsFromString(stringToEncrypt);

        for (int i = 0; i < charsToEncrypt.length; i++) {

            char charToEncrypt = charsToEncrypt[i];
            int alphabetPos = ALPHABET.indexOf(charToEncrypt);

            if(alphabetPos > 0){
                int outSymbolPos = alphabetPos + encryptKey;
                outSymbolPos =  outSymbolPos >= ALPHABET_CAPACITY ? outSymbolPos - ALPHABET_CAPACITY : outSymbolPos;
                stringBuilder.append(ALPHABET.get(outSymbolPos));
            } else {
                stringBuilder.append(charToEncrypt);
            }
        }

        return stringBuilder.toString();

    }

    private static String decryptSymbolsInString(String stringToDecrypt, int encryptKey) {

        StringBuilder stringBuilder = new StringBuilder();
        char[] charsToDecrypt = getCharsFromString(stringToDecrypt);

        for (int i = 0; i < charsToDecrypt.length; i++) {

            int alphabetPos = ALPHABET.indexOf(charsToDecrypt[i]);
            if(alphabetPos < 0){
                stringBuilder.append(charsToDecrypt[i]);
                continue;
            }

            int outSymbolPos = alphabetPos - encryptKey;
            if(outSymbolPos < 0){
                stringBuilder.append(ALPHABET.get(ALPHABET_CAPACITY + outSymbolPos));
            } else {
                stringBuilder.append(ALPHABET.get(outSymbolPos));
            }

        }

        return stringBuilder.toString();

    }

    private static String decryptsSymbolsWithBF(String stringToDecrypt, int possibleKey) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] charsToDecrypt = getCharsFromString(stringToDecrypt);
        for (int i = 0; i < charsToDecrypt.length; i++) {
            if (ALPHABET.indexOf(charsToDecrypt[i]) > 0) {
                char aChar = 'а';
                charsToDecrypt[i] = (char) (((charsToDecrypt[i] - aChar - possibleKey + ALPHABET_CAPACITY) % ALPHABET_CAPACITY) + aChar);
            }
            stringBuilder.append(charsToDecrypt[i]);
        }

        return "Ключ: " + possibleKey + " значение: " + stringBuilder.toString() + "\n";
    }

    private static char[] getCharsFromString(String string){
        char[] chars = new char[string.length()];
        string.getChars(0, string.length(), chars,0);
        return chars;
    }

}
