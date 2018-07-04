package pro.finance.ormdemo.demo.entity;

import java.util.UUID;

/**
 * Created by s.vasnev (s.vasnev@advcash.com) on 02.07.18.
 */
public class User {

	UUID id;

	String name;


	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("User{");
		sb.append("id=").append(id);
		sb.append(", name='").append(name).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
