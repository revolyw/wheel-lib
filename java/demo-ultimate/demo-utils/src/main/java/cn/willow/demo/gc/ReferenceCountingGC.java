package cn.willow.demo.gc;

/**
 * @author willow
 * @since 2024/12/26
 */
public class ReferenceCountingGC {
    public Object instance;

    public ReferenceCountingGC(String name) {}

    public static void main(String[] args) {
        ReferenceCountingGC a = new ReferenceCountingGC("objA");
        ReferenceCountingGC b = new ReferenceCountingGC("objB");

        a.instance = b;
        b.instance = a;

        /**
         * 最后 a、b 这 2 个对象都无法再被访问了，但是由于它们相互引用着对方，导致了它们的引用计数永远都不会为 0
         * 如果使用引用计数法，也就永远无法通知 GC 收集器回收它们
         */
        a = null;
        b = null;
    }
}
