package cn.yangw.demo.transaction;

import cn.yangw.demo.domain.User;
import cn.yangw.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

/**
 * all transactions contain three sub transactions
 * all sub transactions contains a unit operation
 *
 * @author Willow
 * @date 2019-09-01
 */
@Service
public class TransactionService {
	@Autowired
	private UserRepository userRepository;

	/* cases */
	@Transactional
	public void transactionAndSub1StatePrivateAndThrowsException(String username1, String username2) {
		sub1StatePrivate(username1);
		sub2WithoutTransaction(username2);
		throw new RuntimeException();
	}

	@Transactional
	public void transactionWithoutAnyException(String username1, String username2) {
		sub1WithTransaction(username1);
		sub2WithTransaction(username2);
	}

	@Transactional
	public void transactionAndSubWithoutTransactionAndWithoutAnyException(String username1, String username2) {
		sub1WithoutTransaction(username1);
		sub2WithoutTransaction(username2);
	}

	@Transactional
	public void transactionWithExceptionAndSubWithoutTransactionAndWithoutAnyException(String username1, String username2) {
		sub1WithoutTransaction(username1);
		sub2WithoutTransaction(username2);
		throw new RuntimeException();
	}

	@Transactional
	public void transactionAndSub1WithTransactionAndThrowsException(String username1, String username2) {
		sub1WithTransactionAndThrowsException(username1);
		sub2WithTransaction(username2);
	}


	@Transactional
	public void transactionAndSub2WithTransactionAndThrowsException(String username1, String username2) {
		sub1WithTransaction(username1);
		sub2WithTransactionAndThrowsException(username2);
	}


	public void withoutTransactionAndSub1WithTransactionAndThrowsException(String username1, String username2) {
		sub1WithTransactionAndThrowsException(username1);
		sub2WithTransaction(username2);
	}

	public void withoutTransactionAndSub1WithTransactionAndSub2WithTransactionAndThrowsException(String username1, String username2) {
		sub1WithTransaction(username1);
		sub2WithTransactionAndThrowsException(username2);
	}

	@Transactional
	public void transactionAndSub1WithoutTransactionAndSub2WithTransactionAndThrowsException(String username1, String username2) {
		sub1WithoutTransaction(username1);
		sub2WithTransactionAndThrowsException(username2);
	}

	/* all sub transactions */

	private void sub1StatePrivate(String username) {
		{
			Optional<User> userOptional = userRepository.findFirstByOrderByCreateTimeDesc();
			System.out.println(userOptional.isPresent());
		}
		User user = new User();
		user.setName(username);
		user.setCreateTime(new Date());
		user.setModifyTime(user.getCreateTime());
		userRepository.save(user);
		{
			Optional<User> userOptional = userRepository.findFirstByOrderByCreateTimeDesc();
			System.out.println(userOptional.isPresent());
		}
	}

	@Transactional
	public void sub1WithTransaction(String username) {
		{
			Optional<User> userOptional = userRepository.findFirstByOrderByCreateTimeDesc();
			System.out.println(userOptional.isPresent());
		}
		User user = new User();
		user.setName(username);
		user.setCreateTime(new Date());
		user.setModifyTime(user.getCreateTime());
		userRepository.save(user);
		{
			Optional<User> userOptional = userRepository.findFirstByOrderByCreateTimeDesc();
			System.out.println(userOptional.isPresent());
		}
	}

	@Transactional
	public void sub2WithTransaction(String username) {
		{
			Optional<User> userOptional = userRepository.findFirstByOrderByCreateTimeDesc();
			System.out.println(userOptional.isPresent());
		}
		User user = new User();
		user.setName(username);
		user.setCreateTime(new Date());
		user.setModifyTime(user.getCreateTime());
		userRepository.save(user);
		{
			Optional<User> userOptional = userRepository.findFirstByOrderByCreateTimeDesc();
			System.out.println(userOptional.isPresent());
		}
	}

	@Transactional
	public void sub1WithTransactionAndThrowsException(String username) {
		{
			Optional<User> userOptional = userRepository.findFirstByOrderByCreateTimeDesc();
			System.out.println(userOptional.isPresent());
		}
		User user = new User();
		user.setName(username);
		user.setCreateTime(new Date());
		user.setModifyTime(user.getCreateTime());
		userRepository.save(user);
		{
			Optional<User> userOptional = userRepository.findFirstByOrderByCreateTimeDesc();
			System.out.println(userOptional.isPresent());
		}
		throw new RuntimeException();
	}

	@Transactional
	public void sub2WithTransactionAndThrowsException(String username) {
		{
			Optional<User> userOptional = userRepository.findFirstByOrderByCreateTimeDesc();
			System.out.println(userOptional.isPresent());
		}
		User user = new User();
		user.setName(username);
		user.setCreateTime(new Date());
		user.setModifyTime(user.getCreateTime());
		userRepository.save(user);
		{
			Optional<User> userOptional = userRepository.findFirstByOrderByCreateTimeDesc();
			System.out.println(userOptional.isPresent());
		}
		throw new RuntimeException();
	}

	public void sub1WithoutTransaction(String username) {
		{
			Optional<User> userOptional = userRepository.findFirstByOrderByCreateTimeDesc();
			System.out.println(userOptional.isPresent());
		}
		User user = new User();
		user.setName(username);
		user.setCreateTime(new Date());
		user.setModifyTime(user.getCreateTime());
		userRepository.save(user);
		{
			Optional<User> userOptional = userRepository.findFirstByOrderByCreateTimeDesc();
			System.out.println(userOptional.isPresent());
		}
	}

	public void sub2WithoutTransaction(String username) {
		{
			Optional<User> userOptional = userRepository.findFirstByOrderByCreateTimeDesc();
			System.out.println(userOptional.isPresent());
		}
		User user = new User();
		user.setName(username);
		user.setCreateTime(new Date());
		user.setModifyTime(user.getCreateTime());
		userRepository.save(user);
		{
			Optional<User> userOptional = userRepository.findFirstByOrderByCreateTimeDesc();
			System.out.println(userOptional.isPresent());
		}
	}
}
