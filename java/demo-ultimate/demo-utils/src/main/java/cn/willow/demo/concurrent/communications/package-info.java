/**
 * 线程间通信。大大提高 CPU 的利用率
 * 通过循环轮询检查共享数据的方式进行线程间通信，CPU 空转，浪费资源
 * wait/notify 通信机制。必须在同步方法或同步块中（synchronized）调用。
 *  wait：用来将当前线程置入“预执行队列”中，并且在 wait 所在的代码行处挂起执行，直到接到 notify。执行 wait 后，线程会放弃锁，进入锁的“等待队列”，等待被“唤醒”，在被唤醒前，线程与其他线程竞争重新获得锁。如果调用 wait 时没有持有适当的锁，会抛出异常 {@link java.lang.IllegalMonitorStateException}。
 *  notify：用来通知那些可能等待该对象的对象锁的其他线程，如果有多个等待线程，则由线程规划器随机挑选一个呈 wait 状态的线程，对其发出 notify，并使它等待获取该对象的对象锁。注意在执行 notify 方法后，当前线程不会马上释放锁，呈 wait 状态的线程也不能马上获取锁，要等到执行 notify 方法的线程将程序执行完，也就是退出 synchronized 代码块后才可以。没有收到 notify 的线程将一直阻塞在 wait。 如果发出 notify 时没有处于阻塞的线程，那就忽略
 *  总结：wait 可以使处于临界区内的线程进入等待状态，同时释放锁。而 notify 可以唤醒一个因调用了 wait 操作而处于阻塞状态的线程，使其
 * 生产者/消费者模式的实现
 *  一生产者一消费者-操作值
 *  多生产者多消费者-操作值-假死-notifyAll
 *  一生产者一消费者-操作栈
 *  多生产者多消费者-操作栈
 * 方法 join 的使用。 释放锁，内部使用 wait 实现。{@link cn.willow.demo.concurrent.communications.JoinDemo}
 * ThreadLocal、InheritableThreadLocal 的使用。{@link cn.willow.demo.concurrent.communications.ThreadLocalDemo}
 *
 * @author willow
 * @date 2025/1/1
 */
package cn.willow.demo.concurrent.communications;