/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.kbasewebflux.handler;


import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.eastrobot.kbasewebflux.domain.User;
import com.eastrobot.kbasewebflux.repository.UserRepository;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @date 2018年4月26日 下午4:30:26
 * @version 1.0
 */
@Slf4j
@Component
public class UserHandler {
	
	@Autowired
	UserRepository userRepository;

	/**
	 * 获取用户清单
	 * @author eko.zhan at 2018年4月26日 下午4:30:25
	 * @param request
	 * @return
	 */
	public Mono<ServerResponse> list(ServerRequest request) {
		Flux<User> userStream = userRepository.findAll();
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(userStream, User.class);
	}

	/**
	 * 保存用户
	 * @author eko.zhan at 2018年4月26日 下午4:30:48
	 * @param request
	 * @return
	 */
	public Mono<ServerResponse> save(ServerRequest request) {
		log.debug("开始保存");
		Flux<User> userStream = request.bodyToFlux(User.class);
		return ServerResponse.ok().body(userRepository.insert(userStream), User.class);
	}

	/**
	 * 删除用户
	 * @author eko.zhan at 2018年4月26日 下午4:30:04
	 * @param request
	 * @return
	 */
	public Mono<ServerResponse> delete(ServerRequest request) {
		String id = request.pathVariable("id");
		log.debug("开始删除 [" + id + "]");
		return ServerResponse.ok().build(userRepository.deleteById(id));
	}
	
	/**
	 * 获取用户
	 * @author eko.zhan at 2018年4月26日 下午4:30:17
	 * @param request
	 * @return
	 */
	public Mono<ServerResponse> findById(ServerRequest request) {
		String id = request.queryParam("id").get(); //request.pathVariable("id");
		return userRepository.findById(id).flatMap(user -> ServerResponse.ok().body(Mono.just(user), User.class))
				.switchIfEmpty(ServerResponse.notFound().build());
	}
}
