package com.github.zxhtom.dynic.datasource.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * TODO
 *
 * @author zxhtom
 * 2022/10/9
 */
public class SimpleQuery {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1、导入驱动jar包
        //2、注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //3、获取数据库的连接对象
        Connection con = DriverManager.getConnection("jdbc:mysql://dynic.zxhtom.com:3306/dy", "root", "123456");
        con.setAutoCommit(false);
        String sql = "update dynic_test set name = 'simplename' where id = '1' ";
        String sql2 = "update dynic_test set name = 'simplename' where id = '2' ";
        //5、获取执行sql语句的对象
        Statement stat = con.createStatement();
        //6、执行sql并接收返回结果
        int count = stat.executeUpdate(sql);
        int count2 = con.createStatement().executeUpdate(sql2);
        //7、处理结果
        System.out.println(count);
        //8、释放资源
        stat.close();
        //let see commit
        if (count == 2) {
            con.commit();
        }
        con.close();
    }
}
