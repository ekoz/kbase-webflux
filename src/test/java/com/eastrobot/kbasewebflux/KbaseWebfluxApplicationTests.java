package com.eastrobot.kbasewebflux;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Mono;

import com.eastrobot.kbasewebflux.domain.User;
import com.eastrobot.kbasewebflux.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KbaseWebfluxApplicationTests {

	@Autowired
	private WebTestClient webTestClient;
	
	@Autowired
	UserRepository userRepository;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testList(){
		webTestClient.get().uri("/user/list")
			.accept(MediaType.APPLICATION_JSON_UTF8)
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
			.expectBodyList(User.class)
			.consumeWith(response -> {
				response.getResponseBody().forEach(user -> {
					System.out.println(user.getName() + " " + user.getId());
				});
			});
	}

	
	@Test
	public void testGet(){
		webTestClient.get().uri("/user/get?id=5ae99946525045381cfded02")
			.accept(MediaType.APPLICATION_JSON_UTF8)
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
			.expectBodyList(User.class)
			.consumeWith(response -> {
				response.getResponseBody().forEach(user -> System.out.println(user.getName()));
			});
	}
	
	@Test
	public void testSave(){
		User user = new User(null, "xiaoming", 1);
		webTestClient.post().uri("/user/save")
        	.accept(MediaType.APPLICATION_JSON_UTF8)
			.body(Mono.just(user), User.class)
			.exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
            .expectBody();
	}
	
	@Test
	public void testDelete(){
		webTestClient.post().uri("/user/delete/{id}", "5b0bece40f467433d8f38e45")
    	.accept(MediaType.APPLICATION_JSON_UTF8)
		.exchange();
	}
}
