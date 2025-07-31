package cn.yangw.demo.repository;

import cn.yangw.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * TODO comments
 *
 * @author Willow
 * @date 2019-09-01
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findFirstByOrderByCreateTimeDesc();

	List<User> findByNameIn(List<String> newArrayList);
}
