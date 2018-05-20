/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.kbasewebflux.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.eastrobot.kbasewebflux.domain.User;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @date 2018年5月2日 上午11:47:06
 * @version 1.0
 */
@Repository
@Primary
public interface UserRepository extends ReactiveMongoRepository<User, String>{

}
