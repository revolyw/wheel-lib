package cn.willow.demo.pattern;

/**
 * 饿汉：使用静态代码块实现
 * @author willow
 * @since 2025/1/8
 */
public class SingletonStaticDemo {
    public static class MyObject {
        private static MyObject instance = null;
        private MyObject() {}
        static {
            instance = new MyObject();
        }

        public static MyObject getInstance() {
            return instance;
        }
    }
}
