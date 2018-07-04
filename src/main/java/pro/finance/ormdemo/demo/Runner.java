package pro.finance.ormdemo.demo;

import static pro.finance.ormdemo.demo.jooq.public_.tables.User.USER;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import pro.finance.ormdemo.demo.entity.User;

/**
 * Created by s.vasnev (s.vasnev@advcash.com) on 04.07.18.
 */

@Component
public class Runner implements ApplicationRunner {

	@Autowired
	private JdbcTemplate template;

	@Override
	public void run(ApplicationArguments args) {

		User john = new User();
		john.setId(UUID.randomUUID());
		john.setName("John " + LocalDateTime.now().getMinute());


		String insertStatement = getDSLContext().insertInto(USER, USER.ID, USER.NAME)
			.values(john.getId(), john.getName()).toString();

		template.execute(insertStatement);


		String selectStatement = getDSLContext().select().from(USER).toString();


		List<User> users = template.query(selectStatement,
			new BeanPropertyRowMapper(User.class));

		String secondSelectStatement = getDSLContext().select().from(USER).where(USER.ID.eq(john.getId())).toString();

		john = template.<User>queryForObject(secondSelectStatement,
			new BeanPropertyRowMapper(User.class));
	}


	@Bean
	public DSLContext getDSLContext() {
		return DSL.using(SQLDialect.POSTGRES);
	}
}
