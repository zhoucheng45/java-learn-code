package cn.com.benny.learn;

import java.sql.Connection;
import java.sql.*;
/**
 * Hello world!
 *
 */
public class App 
{

    static String jdbcurl = "jdbc:mysql://localhost:3306/test";
    static final String USER = "root";
    static final String PASSWORD = "benny123";
    static final String DRIVER = "com.mysql.jdbc.Driver";

    public static void main( String[] args ) throws Exception
    {
//        String changeTableName = replaceUnderLineAndUpperCase(tableName);

        String tableName = "student";
        Connection connection = getConnection();
        DatabaseMetaData databaseMetaData = connection.getMetaData();

        ResultSet rs = databaseMetaData.getColumns(null,"%", tableName,"%");

        ResultSetMetaData m = rs.getMetaData();
        int columns=m.getColumnCount();
        for (int i=1; i<=columns; i++){
            System.out.print(m.getColumnName(i));
            System.out.print(",");
        }
        System.out.println();
        //显示表格内容
        while(rs.next())
        {
            for(int i=1;i<=columns;i++)
            {
                System.out.print(rs.getString(i));
                System.out.print(",");
            }
            System.out.println();
        }
        System.out.println(rs);
    }


    public static Connection getConnection() throws Exception{
        Class.forName(DRIVER);
        Connection connection= DriverManager.getConnection(jdbcurl, USER, PASSWORD);
        return connection;
    }

}
