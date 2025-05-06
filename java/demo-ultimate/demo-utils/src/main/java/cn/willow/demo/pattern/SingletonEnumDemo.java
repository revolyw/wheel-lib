package cn.willow.demo.pattern;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 饿汉：枚举实现单例
 *
 * @author willow
 * @since 2025/1/8
 */
public class SingletonEnumDemo {
    public enum MyObject {
        myObject;
        private ReentrantLock lock;

        private MyObject() {
            System.out.println("调用 MyObject 构造");
            lock = new ReentrantLock();
//            String url = "jdbc:mysql://127.0.0.1:3306";
//            String username = "root";
//            String password = "willow";
//            String driver = "com.mysql.jdbc.Driver";
//            try {
//                Class.forName(driver);
//                connection = DriverManager.getConnection(url, username, password);
//            } catch (ClassNotFoundException | SQLException e) {
//                throw new RuntimeException(e);
//            }
        }

        public Lock getLock(){
            return lock;
        }

        public static Lock getInstance() {
            return myObject.getLock();
        }
    }

    public static void main(String[] args) {
        Runnable runnable = () -> {
            Lock instance = MyObject.getInstance();
            System.out.println(instance.hashCode());
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
