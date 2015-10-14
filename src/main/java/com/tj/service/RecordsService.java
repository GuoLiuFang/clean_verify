package com.tj.service;

import com.tj.beans.ErrorRecords;
import com.tj.beans.ErrorsGrep;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by LiuFangGuo on 10/13/15.
 */
public class RecordsService {
    private Properties properties;
    private String pathName;

    public RecordsService() {
        this.properties = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("records.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setPathName(properties.getProperty("pathName"));
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getPathName() {
        return pathName;
    }

    public List<ErrorRecords> getRecords(ResultSet resultSet) {
        List<ErrorRecords> result = new ArrayList();
        try {
            while (resultSet.next()) {
                ErrorRecords errorRecords = new ErrorRecords();

                errorRecords.setDid(resultSet.getString(this.properties.getProperty("did")));
                errorRecords.setRecord_time(resultSet.getString(this.properties.getProperty("record_time")));
                errorRecords.setSolution_id(resultSet.getString(this.properties.getProperty("solution_id")));
                errorRecords.setReader(resultSet.getString(this.properties.getProperty("reader")));
                errorRecords.setWriter(resultSet.getString(this.properties.getProperty("writer")));
                errorRecords.setShell_code(resultSet.getString(this.properties.getProperty("shell_code")));
                errorRecords.setPrepare_kernel_cred(resultSet.getString(this.properties.getProperty("prepare_kernel_cred")));
                errorRecords.setCommit_creds(resultSet.getString(this.properties.getProperty("commit_creds")));
                errorRecords.setTty_fasync(resultSet.getString(this.properties.getProperty("tty_fasync")));
                errorRecords.setPtmx_open(resultSet.getString(this.properties.getProperty("ptmx_open")));
                errorRecords.setTty_init_dev(resultSet.getString(this.properties.getProperty("tty_init_dev")));
                errorRecords.setTty_release(resultSet.getString(this.properties.getProperty("tty_release")));
                errorRecords.setPtmx_fops(resultSet.getString(this.properties.getProperty("ptmx_fops")));
                errorRecords.setPtmx_fops_address(resultSet.getString(this.properties.getProperty("ptmx_fops_address")));
                errorRecords.setHack_code(resultSet.getString(this.properties.getProperty("hack_code")));
                errorRecords.setSys_setresuid(resultSet.getString(this.properties.getProperty("sys_setresuid")));
                errorRecords.setHack_point(resultSet.getString(this.properties.getProperty("hack_point")));
                result.add(errorRecords);
                System.out.println(errorRecords.getString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<ErrorsGrep> verifyAll(List<ErrorRecords> errorRecordsList) {
        List<ErrorsGrep> result = new ArrayList<ErrorsGrep>();
        for (ErrorRecords record : errorRecordsList) {
            String solution_id = record.getSolution_id();
            if ("100".equals(solution_id) && verify100(record)) {//有问题放进去
                record.setError_info(this.properties.getProperty("error_100"));
                result.add(record);
            }
            if ("101".equals(solution_id) && verify101102(record)) {//有问题放进去
                record.setError_info(this.properties.getProperty("error_101-102"));
                result.add(record);
            }
            if ("102".equals(solution_id) && verify101102(record)) {//有问题放进去
                record.setError_info(this.properties.getProperty("error_101-102"));
                result.add(record);
            }
            if (verifyAddress(record)) {
                record.setError_info(this.properties.getProperty("error_address"));
                result.add(record);
            }
        }
        return result;
    }

    private boolean verifyShellCode(ErrorRecords record) {
        if ("0".equals(record.getShell_code())) {
            return false;
        } else {
            return true;
        }
    }

    private boolean verifyAddress(ErrorRecords record) {
        if (record.getCommit_creds() != null &&
                record.getHack_point() != null &&
                record.getPrepare_kernel_cred() != null &&
                record.getPtmx_fops() != null &&
                record.getPtmx_open() != null &&
                record.getTty_fasync() != null &&
                record.getTty_release() != null &&
                record.getTty_init_dev() != null
                ) {
            return false;
        } else {
            return true;
        }

    }

    private boolean verify101102(ErrorRecords record) {
        if (record.getReader() != null && record.getWriter() != null) {
            return false;
        } else {
            return true;
        }
    }

    private boolean verify100(ErrorRecords record) {
        if (record.getReader() == null && record.getWriter() == null) {
//        if ("null".equals(record.getReader()) && "null".equals(record.getWriter())) {
            return false;
        } else {
            return true;
        }
    }
}
