// my package
package ru.jr.kriger_cezarapp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CezarApp {
    public static void main(String[] args) {

        int operationMode = operationMode();
        switch (operationMode){
            case 1 -> { //Зашифровать файл(ключ)
                CezarCipher cipher = new CezarCipher();
                cipher.encryptWithKey();
            }
            case 2 -> { //Расшифровать файл(ключ)
                CezarCipher cipher = new CezarCipher();
                cipher.decryptWithKey();
            }
            case 3 -> { //Расшифровать файл(Brute force)
                CezarCipher cipher = new CezarCipher();
                cipher.decryptWithBruteForce();
                System.out.println("");
            }
            case 4 -> { //Статистический анализ"
                CezarCipher cipher = new CezarCipher();
                cipher.statisticalAnalysis();
            }
            case 0 -> {
                System.out.println("Вы закрыли программу");
            }
            default -> {
                System.out.println("Не верный вариант работы программы:" + operationMode);
            }
        }
        System.out.println("Работа программы завершена");
    }

    private static int operationMode(){

        System.out.println("Введите одну цифру");
        System.out.println("1. Зашифровать файл(ключ)");
        System.out.println("2. Расшифровать файл(ключ)");
        System.out.println("3. Расшифровать файл(Brute force)");
        System.out.println("4. Статистический анализ");
        System.out.println("0. Выход");

        Scanner console = new Scanner(System.in);
        int operationMode = 0;

        try {
            operationMode = console.nextInt();
        } catch (InputMismatchException e){
            System.out.println("Введите одну цифру от 0 до 4");
        }

        return operationMode;

    }
}
