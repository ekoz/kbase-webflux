/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.kbasewebflux.web;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.eastrobot.kbasewebflux.domain.User;
import com.eastrobot.kbasewebflux.repository.UserRepository;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @date 2018年5月2日 下午1:33:57
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/list")
	public Flux<User> list(){
		return userRepository.findAll();
	}
	
	@PostMapping("/save")
	public Mono<Void> save(@RequestBody Publisher<User> userStream){
		return userRepository.insert(userStream).then();
	}
	
	@GetMapping("/get")
	public Mono<User> get(@RequestParam String id){
		return userRepository.findById(id);
	}
	
	@PostMapping("/delete")
	public Mono<Void> delete(@RequestParam(required=false) String id){
		if (StringUtils.isEmpty(id)){
			return userRepository.deleteAll();
		}else{
			return userRepository.deleteById(id);
		}
	}
}
