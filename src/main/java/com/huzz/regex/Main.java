package com.huzz.regex;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String str = "联合侦察监视、联合预警探测、联合投送、联合火力打击、联合兵力突击、联合网络作战、联合电子对抗、联合特种作战、联合防卫作战、无人作战、反无人作战、舆论战心理战、法律战";
        String[] strs = str.split("、");

        for(int i=0;i<strs.length;i++){
            System.out.print("\""+strs[i]+"\",");
        }



    }
}
