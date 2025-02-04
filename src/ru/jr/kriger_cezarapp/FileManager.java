package ru.jr.kriger_cezarapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class FileManager {

    private FileManager() {
    }

    public static String getInputPath(){

        System.out.println("Введите путь к исходному файлу " +
                "или введите 0 для завершения работы программы");

        Scanner console = new Scanner(System.in);
        DataValidator dataValidator = new DataValidator();

        while (true) {
            String value = console.nextLine();
            if(dataValidator.isFileValid(value) || value.equals("0")) {
                return value;
            } else {
                System.out.println("Файл не прошел валидацию. \n" +
                        "Выберите корректный файл или введите 0 для завершения работы программы");
            }

        }
    }

    public static String getOutputPath(){

        System.out.println("Введите путь к файлу, в который нужно поместить результат работы программы.\n" +
                "Введите каталог, чтобы результат работы программы записался туда" +
                " или введите 0 для завершения работы программы");

        Scanner console = new Scanner(System.in);
        DataValidator dataValidator = new DataValidator();

        while (true) {
            String value = console.nextLine();
            if(dataValidator.isDirectory(value)){
                try {
                    value = createNewFile(value);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(dataValidator.isFileValid(value) || value.equals("0")) {
                return value;
            } else {
                System.out.println("Файл не прошел валидацию. \n" +
                        "Выберите корректный файл или введите 0 для завершения работы программы");
            }
        }
    }

    public static int getEncryptKey(){

        System.out.println("Введите ключ шифрования или введите -1 для завершения работы программы");

        Scanner console = new Scanner(System.in);
        DataValidator dataValidator = new DataValidator();
        while (true) {
            int value = console.nextInt();
            if(dataValidator.isKeyValid(value)) {
                return value;
            } else {
                System.out.println("Ключ не прошел валидацию.\n" +
                        "Введите корректный ключ или введите -1 для завершения работы программы");
            }
        }
    }

    private static String createNewFile(String directoryPath) throws IOException {
        String fullFileName = directoryPath + "CezarCipherResult.txt";
        Path filePath = Path.of(fullFileName);
        if (Files.notExists(filePath)) {
            return Files.createFile(filePath).toString();
        } else {
            System.out.println("Файл уже существует! Данные будут перезаписаны!");
            return fullFileName;
        }
    }
}
