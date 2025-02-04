package ru.jr.kriger_cezarapp;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class DataValidator {
    private final List<String> supportedFormats = Arrays.asList(".txt");

    public boolean isFileValid(String filePath) {

        Path path = Path.of(filePath);

        // Проверяем существование файла
        if (Files.notExists(path)){
            System.out.println("Некорректный путь к файлу");
            return false;
        }

        // Проверяем возможность чтения
        if(!Files.isReadable(path)){
            System.out.println("Файл не читается!");
            return false;
        }

        // Проверяем формат файла
        String fileExtension = getFileExtension(path);
        if (!supportedFormats.contains(fileExtension)){
            System.out.println("Формат файла не поддерживается!");
            return false;
        }

        return true;

    }

    public boolean isKeyValid(int key){

        int alphabetCapacity = Alphabet.getAlphabetCapacity();

        if(key == 0 || key == Alphabet.getAlphabetCapacity()){
            System.out.println("Такой ключ не имеет смысла!");
            return false;
        } else if(key >= alphabetCapacity || key < -1){
            System.out.println("Значение ключа должно быть больше нуля " +
                    "и не больше количества символов в алфавите - " + alphabetCapacity);
            return false;
        }

        return true;

    }

    public boolean isDirectory(String filePath){

        File file = new File(filePath);
        return file.isDirectory();

    }

    private String getFileExtension(Path filePath){
        String name = filePath.toString();
        int dotPosition = name.lastIndexOf('.');
        return dotPosition > 0 ? name.substring(dotPosition) : "";
    }
}
