/**
 * 锁机制示例
 * synchronized
 * ReentrantLock、Condition（await、signal、signalAll）
 *  常用 API：
 *      lockInterruptibly(使用场景？)、tryLock、tryLock(long, TimeUnit)
 *      awaitUninterruptibly(使用场景？)、awaitUntil(long)
 *      getHoldCount、getQueueLength、getWaitQueueLength、getWaitQueueLength(Condition)
 *      hasQueuedThread(Thread)、hasQueuedThreads、hasWaiters(Condition)
 *      isFair、isHeldByCurrentThread、isLocked
 * 非公平锁与饥饿问题、公平锁
 * ReentrantReadWriteLock
 * @author willow
 * @date 2024/12/10
 */
package cn.willow.demo.concurrent.locks;