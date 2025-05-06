package cn.willow.demo.pattern;

import java.io.*;

/**
 * 静态内部类实现单例
 *
 * @author willow
 * @since 2025/1/8
 */
public class SingletonStaticClassDemo {
    public static class MyObject implements Serializable {
        private static class MyObjectHandler {
            private static final MyObject myObject = new MyObject();
        }

        public static MyObject getInstance() {
            return MyObjectHandler.myObject;
        }

        /**
         * 反序列化时被调用，用于反序列化时返回对象实例
         * @return
         */
        protected Object readResolve() {
            return getInstance();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MyObject myObject = MyObject.getInstance();
        String file = "myObjectFile.txt";
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(myObject);
        oos.close();
        fos.close();
        System.out.println(myObject.hashCode());

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        MyObject readObject = (MyObject) ois.readObject();
        ois.close();
        fis.close();
        System.out.println(readObject.hashCode());
    }
}
