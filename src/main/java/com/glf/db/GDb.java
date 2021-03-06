package com.glf.db;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * Created by LiuFangGuo on 10/12/15.
 * <p/>
 * com.glf.db.GDb.Db实现的目标是：在db.properties中配置要需要的字段和条件，然后返回ResultSet。
 */
public class GDb {
    private Properties properties;
    private static GDb GDb;

    private GDb() {
        this.properties = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("测试：" + properties.getProperty("protocol"));
    }

    public static synchronized GDb getGDb() {
        if (GDb == null) {
            GDb = new GDb();
        }
        return GDb;
    }

    public ResultSet getResultSet(String sql) {
        Connection connection = getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(this.properties.getProperty("className"));
            String url = this.properties.getProperty("protocol") + this.properties.getProperty("hostNamePort") + "/" + this.properties.getProperty("scheme") + "?user=" + this.properties.getProperty("user") + "&password=" + this.properties.getProperty("password");
            System.out.println("组装的url是" + url);
            try {
                connection = DriverManager.getConnection(url);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public String getSelectSql(String... whereVar) {//与db.properties的内容顺序保持一致。
        String whereFields = this.properties.getProperty("whereFields");
        String[] fieldArray = whereFields.split("[,]");
        String whereFunctions = this.properties.getProperty("whereFunctions");
        String[] functionArray = whereFunctions.split("[,]");
        String whereFunctionParameters = this.properties.getProperty("whereFunctionParameters");
        String[] parametersArray = whereFunctionParameters.split("[,]");

        String whereCondition = this.properties.getProperty("whereCondition");
        String[] conditionArray = whereCondition.split("[|]");

        StringBuffer whereClause = new StringBuffer("where 1=1 ");
        for (int i = 0; i < fieldArray.length; i++) {
            whereClause.append(" and " + this.composeFieldWithFunction(functionArray[i], fieldArray[i], parametersArray[i]) + "=" + whereVar[i]);
        }
        for (String contion : conditionArray) {
            whereClause.append(" and " + contion);
        }
        String sql = "select " + this.properties.getProperty("fields") + " from " + this.properties.getProperty("query_table") + " " + whereClause + this.properties.getProperty("groupBy") + " " + this.properties.getProperty("orderBy") + " " + this.properties.getProperty("limit") + ";";
        return sql;
    }

    public String getSelectSql() {
        return this.properties.getProperty("sql");
    }

    private String composeFieldWithFunction(String function, String field, String parameters) {
        if (function != null && !"null".equals(function)) {
            if (parameters != null && !"null".equals(parameters)) {
                String[] parameterArray = parameters.split("[|]");
                StringBuffer parameter = new StringBuffer();
                System.out.println(field + "-------" + parameterArray.length);
                if (parameterArray.length > 1 || !"null".equals(parameterArray[0])) {
                    for (String p : parameterArray) {
                        parameter.append(",").append(p);
                    }
                } else {
                    parameter.append(parameterArray[0]);
                }
                return function + "(" + field + parameter.toString() + ")";
            } else {
                return function + "(" + field + ")";
            }
        } else {
            return field;
        }
    }

    public void insertData(List<? extends Object> contents) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            for (Object line : contents) {
                String sql = "insert " + this.properties.getProperty("insert_table") + " values" + composeValues(line) + ";";
                System.out.println("--逐条执行的sql---" + sql);
                statement.execute(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadDataInfile(String pathName) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "load data infile '" + pathName + "' into table " + this.properties.getProperty("insert_table") + " fields terminated by ',';";
            System.out.println("---通过文件导入----" + sql);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private String composeValues(Object line) {
        String[] contentArray = line.toString().split("[,]");
        StringBuffer result = new StringBuffer();
        for (String element : contentArray) {
            result.append("'").append(element).append("',");
        }
        return "(" + result.toString().substring(0, result.toString().length() - 1) + ")";
    }

    public static void main(String[] args) {
//        GDb GDb = GDb.getGDb();
        GDb GDb2 = new GDb();
//        String sql = GDb2.getSelectSql("'2015-09-28'", "289");
        String sql = GDb2.getSelectSql("'2015-09-28'", "0");
//        String sql = GDb2.getSelectSql();
        System.out.println("组装的测试sql是" + sql);
        ResultSet resultSet = GDb2.getResultSet(sql);
        try {
            while (resultSet.next()) {
                String record_time = resultSet.getString("record_time");
                String apil = resultSet.getString("apil");
                System.out.println(record_time + "--------apil------" + apil);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteData(String deleteDate) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "delete from " + this.properties.getProperty("insert_table") + " where date(record_time) ='" + deleteDate + "';";
            System.out.println("---要删除的sql语句是----" + sql);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}