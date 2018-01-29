package io.crscube.coop.cubetoken.cooperation.user.controller;

import io.crscube.coop.cubetoken.cooperation.base.controller.AbstractController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lee Tae Su 
 * @project CubeToken
 * @version 1.0
 * @since 2018-01-26
 */
@RestController
public class UserController extends AbstractController{
	
	@GetMapping(value = "/users")
	public Map<String, Object> getUsers(){
		Map<String, Object> result = new HashMap<>();
		result.put("users", "aefawf");
		return result;
	}
}
