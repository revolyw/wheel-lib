/**
 * 线程使用示例
 * 线程创建：Thread
 * 线程运行：{@link java.lang.Thread#start()}
 * 线程停止：throws new InterrupterException()/interrupt()
 * 线程暂停：suspend&resume
 * CPU 让渡：yield();
 * 设置优先级：setPriority(int newPriority); 优先级可继 承，优先级与执行顺序具有不确定性
 * 守护线程：当进程中没有非守护线程了，守护线程自动销毁，例如：GC 线程
 * 线程同步见 {@link cn.willow.demo.concurrent.synchronizers}
 * 线程通信：{@link cn.willow.demo.concurrent.communications}
 * @author willow
 * @date 2024/12/31
 */
package cn.willow.demo.concurrent.thread;