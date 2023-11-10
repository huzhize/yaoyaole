package com.huzz.common;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteToDestTop {
    static String PATH = Config.DESKTOP_PATH + "\\output.txt";
    public static void writeToFile(List<String> list) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(PATH));
            for(String str:list){
                writer.write(str);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing writer: " + e.getMessage());
            }
        }
    }
}
