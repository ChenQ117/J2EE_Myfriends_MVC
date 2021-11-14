package utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @version v1.0
 * @ClassName: ConnectionPool
 * @Description: 线程池
 * @Author: ChenQ
 * @Date: 2021/11/13 on 14:38
 */
public class ConnectionPool {
    private static ConnectionPool connectionPool;
    private List<Connection> pool;
    private int poolSize;
    private ConnectionPool(){
        pool = new ArrayList<>(poolSize);
        Connection conn = null;
        Properties properties = new Properties();
        try {
//            InputStream in = ClassLoader.getSystemResourceAsStream("jdbc.properties");
//            properties.load(in);
            InputStream in = new BufferedInputStream (new FileInputStream("E:\\学习资料\\J2EE\\J2EE_Myfriends_MVC\\jdbc.properties"));
            properties.load(in);
            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String dbName = properties.getProperty("dbName");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            poolSize = Integer.valueOf(properties.getProperty("poolSize"));
            for (int i=0;i<poolSize;i++){
                Class.forName(driver);
                conn = DriverManager.getConnection(url+"/"+dbName,user,password);
                pool.add(conn);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static ConnectionPool getInstance(){
        if (connectionPool == null){
            connectionPool = new ConnectionPool();
        }
        return connectionPool;
    }
    public synchronized Connection getConnection(){
        if (pool.size()>0){
            Connection conn = pool.get(0);
            pool.remove(conn);
            return conn;
        }else {
            return null;
        }
    }
    public synchronized void release(Connection conn){
        pool.add(conn);
    }
}
