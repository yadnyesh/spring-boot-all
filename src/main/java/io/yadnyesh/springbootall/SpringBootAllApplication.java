package io.yadnyesh.springbootall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@SpringBootApplication (exclude = {
		JdbcTemplateAutoConfiguration.class,
		DataSourceAutoConfiguration.class
})
public class SpringBootAllApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public SpringBootAllApplication(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAllApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("App Started....Fetching records from database.");

		//jdbcTemplate.update("insert into users(id, name, about) values (10,'Arpita', 'Developer')");

		List<Map<String, Object>> users = jdbcTemplate.queryForList("Select id, name,about from users");
		users.forEach((item) -> {
			System.out.println("Id -> " + item.get("id"));
			System.out.println("Name -> " + item.get("name"));
			System.out.println("About -> " + item.get("about"));
			System.out.println("----------------------------");
		});
	}
}
