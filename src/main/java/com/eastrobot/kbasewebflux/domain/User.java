/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.kbasewebflux.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @date 2018年4月25日 下午3:59:16
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

	@Id
	private String id;
	private String name;
	private Integer sex = 1;
}
