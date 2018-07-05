package pro.finance.ormdemo.demo;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import pro.finance.ormdemo.demo.entity.User;

/**
 * Created by s.vasnev (s.vasnev@advcash.com) on 04.07.18.
 */


@Component
public class Runner implements ApplicationRunner {

	@Autowired
	private SimpleComponent component;

	private AtomicLong userNUmber = new AtomicLong(0l);

	@Override
	public void run(ApplicationArguments args) throws InterruptedException {


		for (int i = 1000; i > 0; i--) {

			User user = new User();
			user.setName("User " + userNUmber.incrementAndGet());
			user.setId(UUID.randomUUID());

			component.insertUser(component.generateInsertStatement(user));

			List<User> users = component.getUserList(component.generateSelectStatement());

			System.out.println(users.stream().map(User::toString).collect(Collectors.joining(", ")));

			user = component.getUser(component.generateSingleSelectStatement(user.getId()));

			System.out.println(user);

			Thread.sleep(100);
		}
	}

}
