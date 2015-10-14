package com.glf.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by LiuFangGuo on 10/13/15.
 */
public class GFile {
    public void writeFile(List<? extends Object> contents, String pathName) {
        File file = new File(pathName);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (Object line : contents) {
            try {
                bufferedWriter.write(line.toString());
                bufferedWriter.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("错误文件已经生成");
    }
}
