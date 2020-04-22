package org.example.spring.mvc.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabasePool {

    private static HikariDataSource hikariDataSource;

    public static HikariDataSource getHikariDataSource(){

        if(null != hikariDataSource){
            return  hikariDataSource;
        }

        synchronized (DatabasePool.class) {
            if (null == hikariDataSource) {

                String jdbcurl = "jdbc:mysql://127.0.0.1:3306/school?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
                String driverName = "com.mysql.cj.jdbc.Driver";

                HikariConfig hikariConfig = new HikariConfig();
                hikariConfig.setUsername("root");
                hikariConfig.setPassword("990619");
                hikariConfig.setDriverClassName(driverName);
                hikariConfig.setJdbcUrl(jdbcurl);

                hikariDataSource = new HikariDataSource(hikariConfig);

                return hikariDataSource;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        getHikariDataSource();
    }
}
