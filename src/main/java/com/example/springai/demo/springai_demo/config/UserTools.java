package com.example.springai.demo.springai_demo.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import com.example.springai.demo.springai_demo.application.domain.User;

@Component
public class UserTools {

	@Tool(description = "devuelve una lista de usuarios")
	List<User> listUsers() {
		return Arrays.asList(User.builder().name("Jose").mail("jose@mail.com").build(),
				User.builder().name("Lucia").mail("lucia@mail.com").build(),
				User.builder().name("David").mail("david@mail.com").build(),
				User.builder().name("Marga").mail("marga@mail.com").build());
	}

}
