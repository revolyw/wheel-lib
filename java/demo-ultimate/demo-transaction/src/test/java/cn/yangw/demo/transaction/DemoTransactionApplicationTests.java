package cn.yangw.demo.transaction;

import cn.yangw.demo.domain.User;
import cn.yangw.demo.repository.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTransactionApplicationTests {
	@Autowired
	private UserRepository userRepository;

	private static Integer userPrimaryKey = null;
	private static String username = "yangw";

	@Before
	public void testInsert() {
		User user = new User();
		user.setName(username);
		user.setCreateTime(new Date());
		user.setModifyTime(user.getCreateTime());
		User save = userRepository.save(user);
		Assert.assertNotNull(save.getId());
		userPrimaryKey = save.getId();
	}

	@Test
	public void testQuery() {
		Optional<User> byId = userRepository.findById(userPrimaryKey);
		Assert.assertTrue(byId.isPresent());
		Assert.assertEquals(username, byId.get().getName());
	}

	@After
	public void testDelete() {
		userRepository.deleteById(userPrimaryKey);
		Optional<User> byId = userRepository.findById(userPrimaryKey);
		Assert.assertFalse(byId.isPresent());
	}
}
