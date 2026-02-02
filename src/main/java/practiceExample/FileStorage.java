package practiceExample;

import java.io.FileInputStream;
import java.io.IOException;

public class FileStorage {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("/Users/aayush/IdeaProjects/practice/src/IO/hello.txt")) {

            int i;

            while ((i = input.read()) != -1) {
                System.out.print(i);
                System.out.print((char) i);
                System.out.print(" \t");
            }
        } catch (IOException e) {
            System.out.println("Error reading file."+ e);
        }
    }
}