package cn.yangw.demo.transaction;

import cn.yangw.demo.domain.User;
import cn.yangw.demo.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

/**
 * 事务测试用例
 *
 * @author Willow
 * @date 2019-09-01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionalTests {
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private UserRepository userRepository;

	private static final String USERNAME_1 = "yangw1";
	private static final String USERNAME_2 = "yangw2";

	/**
	 * 入口方法声明事务
	 * 子方法皆声明事务
	 * 不包含任何异常
	 * <p>
	 * 预期：子方法操作均成功提交
	 * 说明：是否开启事务未知，子方法是否加入事务未知
	 */
	@Test
	public void transactionWithoutAnyException() {
		transactionService.transactionWithoutAnyException(USERNAME_1, USERNAME_2);
		List<User> byName = userRepository.findByNameIn(Lists.newArrayList(USERNAME_1, USERNAME_2));
		Assert.assertNotNull(byName);
		Assert.assertFalse(byName.isEmpty());
		Assert.assertEquals(2, byName.size());
		Assert.assertEquals(USERNAME_1, byName.get(0).getName());
		Assert.assertEquals(USERNAME_2, byName.get(1).getName());
	}

	/**
	 * 入口方法声明事务
	 * 子方法皆声明事务
	 * 子方法 1 抛出异常
	 * <p>
	 * 预期：子方法操作均提交失败
	 * 说明：事务开启；子方法 1 加入了事务，子方法 1 操作回滚，子方法 2 的操作由于异常中断并未执行
	 */
	@Test
	public void transactionAndSub1WithTransactionAndThrowsException() {
		try {
			transactionService.transactionAndSub1WithTransactionAndThrowsException(USERNAME_1, USERNAME_2);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		List<User> byName = userRepository.findByNameIn(Lists.newArrayList(USERNAME_1, USERNAME_2));
		Assert.assertNotNull(byName);
		Assert.assertTrue(byName.isEmpty());
	}

	/**
	 * 入口方法声明事务
	 * 子方法皆声明事务
	 * 子方法 2 抛出异常
	 * <p>
	 * 预期：子方法操作均提交失败
	 * 说明：事务开启；子方法均加入了事务，子方法操作均回滚
	 */
	@Test
	public void transactionAndSub2WithTransactionAndThrowsException() {
		try {
			transactionService.transactionAndSub2WithTransactionAndThrowsException(USERNAME_1, USERNAME_2);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		List<User> byName = userRepository.findByNameIn(Lists.newArrayList(USERNAME_1, USERNAME_2));
		Assert.assertNotNull(byName);
		Assert.assertTrue(byName.isEmpty());
	}

	/**
	 * 入口方法声明事务
	 * 子方法不声明事务
	 * 不包含任何异常
	 * <p>
	 * 预期：子方法操作均提交成功
	 * 说明：是否开启事务未知，子方法是否加入事务未知
	 */
	@Test
	public void transactionAndSubWithoutTransactionAndWithoutAnyException() {
		transactionService.transactionAndSubWithoutTransactionAndWithoutAnyException(USERNAME_1, USERNAME_2);
		List<User> byName = userRepository.findByNameIn(Lists.newArrayList(USERNAME_1, USERNAME_2));
		Assert.assertNotNull(byName);
		Assert.assertFalse(byName.isEmpty());
		Assert.assertEquals(2, byName.size());
		Assert.assertEquals(USERNAME_1, byName.get(0).getName());
		Assert.assertEquals(USERNAME_2, byName.get(1).getName());
	}

	/**
	 * 入口方法声明事务
	 * 子方法不声明事务
	 * 入口方法结束前抛出异常
	 * <p>
	 * 预期：子方法操作均提交失败
	 * 说明：事务开启；子事务均加入事务；子方法操作均回滚
	 * 进一步说明：只要入口方法声明了事务，则子方法默认加入事务
	 */
	@Test
	public void transactionWithExceptionAndSubWithoutTransactionAndWithoutAnyException() {
		try {
			transactionService.transactionWithExceptionAndSubWithoutTransactionAndWithoutAnyException(USERNAME_1, USERNAME_2);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		List<User> byName = userRepository.findByNameIn(Lists.newArrayList(USERNAME_1, USERNAME_2));
		Assert.assertNotNull(byName);
		Assert.assertTrue(byName.isEmpty());
	}

	/**
	 * 入口方法不声明事务
	 * 子方法均声明事务
	 * 子方法 1 抛出异常
	 * <p>
	 * 预期：子方法 1 操作提交成功，子方法 2 操作未执行
	 * 说明：事务是否开启未知；子方法是否加入事务未知；因子方法 1 抛出异常，中断了子方法 2 操作
	 */
	@Test
	public void withoutTransactionAndSub1WithTransactionAndThrowsException() {
		try {
			transactionService.withoutTransactionAndSub1WithTransactionAndThrowsException(USERNAME_1, USERNAME_2);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		List<User> byName = userRepository.findByNameIn(Lists.newArrayList(USERNAME_1, USERNAME_2));
		Assert.assertNotNull(byName);
		Assert.assertFalse(byName.isEmpty());
		Assert.assertEquals(1, byName.size());
		Assert.assertEquals(USERNAME_1, byName.get(0).getName());
	}

	/**
	 * 入口方法不声明事务
	 * 子方法均声明事务
	 * 子方法 2 抛出异常
	 * <p>
	 * 预期：子方法操作均提交成功
	 * 说明：事务未开启；子方法均未加入事务；子方法操作均提交成功，子方法 2 抛出的异常不影响任何结果；
	 * 进一步说明：入口方法必须声明事务，否则事务层内部方法间调用将不传递事务，即便被调用的方法声明了事务
	 */
	@Test
	public void withoutTransactionAndSub1WithTransactionAndSub2WithTransactionAndThrowsException() {
		try {
			transactionService.withoutTransactionAndSub1WithTransactionAndSub2WithTransactionAndThrowsException(USERNAME_1, USERNAME_2);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		List<User> byName = userRepository.findByNameIn(Lists.newArrayList(USERNAME_1, USERNAME_2));
		Assert.assertNotNull(byName);
		Assert.assertFalse(byName.isEmpty());
		Assert.assertEquals(2, byName.size());
		Assert.assertEquals(USERNAME_1, byName.get(0).getName());
		Assert.assertEquals(USERNAME_2, byName.get(1).getName());
	}

	/**
	 * 入口方法声明事务
	 * 子方法 1 不声明事务，子方法 2 声明事务
	 * 子方法 2 抛出异常
	 * <p>
	 * 预期：子方法操作均提交失败
	 * 说明：事务开启；子方法均加入事务，因子方法 2 抛出异常，子方法操作均回滚
	 * 进一步说明：只要入口方法声明了事务，则子方法默认加入事务
	 */
	@Test
	public void transactionAndSub1WithoutTransactionAndSub2WithTransactionAndThrowsException() {
		try {
			transactionService.transactionAndSub1WithoutTransactionAndSub2WithTransactionAndThrowsException(USERNAME_1, USERNAME_2);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		List<User> byName = userRepository.findByNameIn(Lists.newArrayList(USERNAME_1, USERNAME_2));
		Assert.assertNotNull(byName);
		Assert.assertTrue(byName.isEmpty());
	}


	@Before
	public void beforeEveryTestCase() {
		deleteTestUsers();
	}

	@After
	public void afterEveryTestCase() {
		deleteTestUsers();
	}

	private void deleteTestUsers() {
		{
			List<User> users = userRepository.findByNameIn(Lists.newArrayList(USERNAME_1, USERNAME_2));
			Optional.of(users).ifPresent(u -> u.forEach(userRepository::delete));
		}
		{
			List<User> users = userRepository.findByNameIn(Lists.newArrayList(USERNAME_1, USERNAME_2));
			Assert.assertTrue(users.isEmpty());
		}
	}

}
