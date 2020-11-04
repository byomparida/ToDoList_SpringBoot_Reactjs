package com.deloitte.online.todo.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.deloitte.online.todo.entity.UserEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository repository;

	@Test
	public void test_when_find_by_user() {
		Optional<UserEntity> userEntity = repository.findByUserName("byom");
		assertEquals(userEntity, Optional.empty());
	}

	@Test
	public void test_when_user_exists() {
		assertFalse(repository.existsByUserName("byom"));
	}
}
