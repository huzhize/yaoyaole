package com.huzz.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFromDeskTop {
    static String PATH = Config.DESKTOP_PATH + "\\input.txt";

    public static List<String> readToList() throws IOException {
        List<String> list = new ArrayList<>();
        File file = new File(PATH);
        if(!file.exists()) return list;
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        while(line!=null&&line.length()>0){
            list.add(line);
            line = br.readLine();
        }
        br.close();
        return list;
    }

}
