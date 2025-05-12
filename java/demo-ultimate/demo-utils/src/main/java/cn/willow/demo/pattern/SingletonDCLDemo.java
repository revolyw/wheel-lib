package cn.willow.demo.pattern;

import java.io.Serializable;

/**
 * 双重检查锁定（Double-Checked Locking）优化性能
 * @author willow
 * @since 2024/12/11
 */
public class SingletonDCLDemo {
    public static class Singleton implements Serializable {
        private static volatile Singleton instance;

        private Singleton() {
            //防止反射攻击
            if(null == instance){
                throw new IllegalStateException("singleton instance already exists");
            }
        }

        public static Singleton getInstance() {
            if (instance == null) { // 第一次检查。如果实例化过后续访问避免加锁提升开销
                synchronized (Singleton.class) {
                    if (instance == null) { // 第二次检查。如果多个线程通过第一次检查，确保被锁阻塞的其他线程不会再次进入创建实例
                        instance = new Singleton();
                    }
                }
            }
            return instance;
        }

        //防止反序列化创建新的实例
        private Object readResolve() {
            return instance;
        }
    }
}
