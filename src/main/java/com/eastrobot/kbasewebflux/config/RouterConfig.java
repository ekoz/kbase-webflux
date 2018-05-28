/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.kbasewebflux.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.eastrobot.kbasewebflux.handler.UserHandler;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @date 2018年5月20日 下午12:53:16
 * @version 1.0
 */
@Configuration
public class RouterConfig {
	
	@Resource
	UserHandler userHandler;

	@Bean
	public RouterFunction<ServerResponse> personRouter() {
		return RouterFunctions.route(RequestPredicates.GET("/user/list"), userHandler::list)
				.andRoute(RequestPredicates.GET("/user/get"), userHandler::findById)
				.andRoute(RequestPredicates.POST("/user/save"), request -> {return userHandler.save(request);})
				//.andRoute(RequestPredicates.POST("/user/save").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler::save)
				.andRoute(RequestPredicates.POST("/user/delete/{id}"), userHandler::delete);
	}
}
