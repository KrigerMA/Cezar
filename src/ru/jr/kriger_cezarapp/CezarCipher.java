package ru.jr.kriger_cezarapp;

public class CezarCipher {

    public void encryptWithKey() {

        String fileInputPath = FileManager.getInputPath();
        String fileOutputPath = FileManager.getOutputPath();
        int encryptKey = FileManager.getEncryptKey();

       FileEncryptor.encryptFile(fileInputPath, fileOutputPath, encryptKey);

    }

    public void decryptWithKey() {

        String fileInputPath = FileManager.getInputPath();
        String fileOutputPath = FileManager.getOutputPath();
        int encryptKey = FileManager.getEncryptKey();

        FileEncryptor.decryptFile(fileInputPath, fileOutputPath, encryptKey);

    }
    public void decryptWithBruteForce() {

        String fileInputPath = FileManager.getInputPath();
        String fileOutputPath = FileManager.getOutputPath();
        FileEncryptor.decryptWithBruteForce(fileInputPath, fileOutputPath);
        System.out.println("В файл записаны все возможные варианты. Используйте визуальный анализ для определения верного ключа!");

    }
    public void statisticalAnalysis(){
        System.out.println("Coming soon(no)");
    }

}
