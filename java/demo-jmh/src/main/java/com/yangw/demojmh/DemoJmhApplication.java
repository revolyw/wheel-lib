package com.yangw.demojmh;

import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class DemoJmhApplication {

//	/**
//	 * sleep 2000
//	 */
//	@Benchmark
//	@BenchmarkMode(Mode.AverageTime)
//	@OutputTimeUnit(TimeUnit.NANOSECONDS)
//	@Fork(value = 2,jvmArgs = {"-XX:InlineSmallCode=0"})
//	@Warmup(iterations = 5)
//	@Measurement(iterations = 2)
//	public void simple() {
//		doSomething();
//	}
//
//	public static void doSomething() {
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException ignored) {
//		}
//	}

	/**
	 * Scope.Thread：默认的State，每个测试线程分配一个实例；
	 * Scope.Benchmark：所有测试线程共享一个实例，用于测试有状态实例在多线程共享下的性能；
	 * Scope.Group：每个线程组共享一个实例；
	 */
	@State(Scope.Thread)
	public static class ExecutionPlan {

		/**
		 * 在@setup方法执行前转化为为对应的数据类型。
		 * 多个@Param注解的成员之间是乘积关系，
		 * 譬如有两个用@Param注解的字段，
		 * 第一个有5个值，第二个字段有2个值，那么每个测试方法会跑5*2=10次。
		 */
		@Param({"100", "200", "300", "500", "1000"})
		public int iterations;

		public Hasher murmur3;

		public String password = "4v3rys3kur3p455w0rd";

		/**
		 * 测试前准备
		 * Level.Trial 默认，全部Benchmark之前
		 * Level.Iteration 一组调用之前
		 * Level.Invocation 每个方法调用之前
		 */
		@Setup(Level.Invocation)
		public void setUp() {
			murmur3 = Hashing.murmur3_128().newHasher();
		}
	}

	@CompilerControl(value = CompilerControl.Mode.DONT_INLINE)
	@Fork(value = 1, warmups = 1)
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@Warmup(iterations = 1)
	@Measurement(iterations = 2)
	@Group(value = "group1")
	@GroupThreads(value = 2)
	public void benchMurmur3128(ExecutionPlan plan) {

		for (int i = plan.iterations; i > 0; i--) {
			plan.murmur3.putString(plan.password, Charset.defaultCharset());
		}
		plan.murmur3.hash();
	}

	public static void main(String[] args) throws IOException, RunnerException {
		org.openjdk.jmh.Main.main(args);
//		SpringApplication.run(DemoJmhApplication.class, args);
	}

}
