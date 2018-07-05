package pro.finance.ormdemo.demo;

import static pro.finance.ormdemo.demo.jooq.public_.tables.User.USER;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import pro.finance.ormdemo.demo.entity.User;

/**
 * Created by s.vasnev (s.vasnev@advcash.com) on 05.07.18.
 */
@Component
public class SimpleComponent {

	@Autowired
	private JdbcTemplate template;

	public String generateInsertStatement(User user) {
		return getDSLContext().insertInto(USER, USER.ID, USER.NAME)
			.values(user.getId(), user.getName()).toString();
	}

	public String generateSelectStatement() {
		return getDSLContext().select().from(USER).toString();
	}


	public String generateSingleSelectStatement(UUID id) {
		return getDSLContext().select().from(USER).where(USER.ID.eq(id)).toString();
	}


	public void insertUser(String statement) {
		template.execute(statement);
	}

	public User getUser(String statement) {
		return template.<User>queryForObject(statement,
			new BeanPropertyRowMapper(User.class));
	}


	public List<User> getUserList(String statement) {
		return template.query(statement,
			new BeanPropertyRowMapper(User.class));
	}


	@Bean
	public DSLContext getDSLContext() {
		return DSL.using(SQLDialect.POSTGRES);
	}

}
