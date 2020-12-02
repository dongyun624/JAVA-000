package com.partise.insertdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
@SpringBootApplication
public class InsertDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsertDemoApplication.class, args);
        batchInsert();
	}

    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/java_pratise";
        String user = "root";
        String password = "root";
        Connection connection = null;
        try {
            Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void batchInsert() {
        Connection conn = getConnection();
        Long startTime = System.currentTimeMillis();
        try {
            StringBuffer sql = new StringBuffer("INSERT INTO product_order(product_id, user_id, origin_price, purchase_price, order_msg, status, error_msg, business_date, ctime, utime) VALUES (?,?,?,?,?,?,?,?,?,?)");
            /**执行SQL预编译**/
            PreparedStatement preparedStatement = conn.prepareStatement(sql.toString());
            for (int i = 0; i < 200; i++) {
                StringBuffer insertSql = new StringBuffer("");
                for (int j = 1; j <= 5000; j++) {
                    preparedStatement.setLong(1, i*1000 + j);
                    preparedStatement.setLong(2, 1001);
                    preparedStatement.setBigDecimal(3, new BigDecimal("20.0"));
                    preparedStatement.setBigDecimal(4, new BigDecimal("20.0"));
                    preparedStatement.setString(5,"");
                    preparedStatement.setInt(6,3);
                    preparedStatement.setString(7,"");
                    preparedStatement.setString(8,"");
                    preparedStatement.setLong(9, System.currentTimeMillis());
                    preparedStatement.setLong(10, System.currentTimeMillis());
                    preparedStatement.addBatch();
                }


                /**设置不自动提交，以便于在出现异常的时候数据库回滚**/
                conn.setAutoCommit(false);

                // 执行操作
                int[] a = preparedStatement.executeBatch();
                // 提交事务
                conn.commit();

                preparedStatement.clearBatch();  // 耗时：103111 毫秒, 第二次：97731 毫秒
//                preparedStatement = conn.prepareStatement(sql.toString());  // 耗时：107487 毫秒
            }
            // 头等连接
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            log.error("error msg", e);
        }
        long endTime = System.currentTimeMillis();
        log.info("consume:{}", endTime -  startTime);
    }


}
