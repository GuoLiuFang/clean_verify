package com.tj.java;

import com.glf.db.GDb;
import com.glf.file.GFile;
import com.tj.beans.ErrorRecords;
import com.tj.beans.ErrorsGrep;
import com.tj.service.RecordsService;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        String recordDate = args[0];
        GDb gDb = GDb.getGDb();
        String sql = gDb.getSelectSql(recordDate,"0");
        System.out.println("-------组装的sql是" + sql);
        RecordsService recordsService = new RecordsService();
        List<ErrorRecords> recordsList = recordsService.getRecords(gDb.getResultSet(sql));
        List<ErrorsGrep> errorsGrepList = recordsService.verifyAll(recordsList);
        GFile gFile = new GFile();
        gFile.writeFile(errorsGrepList, recordsService.getPathName());
    }
}