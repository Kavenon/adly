package pl.edu.agh.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.student.model.User;
import pl.edu.agh.student.service.UserService;

import java.security.Principal;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/user")
	@ResponseBody
	public Principal user(Principal user) {
		return user;
	}

	@RequestMapping("/userId")
	@ResponseBody
	public Long userId(@RequestParam(value = "user") String user) {
		return ((User) userService.loadUserByUsername(user)).getId();
	}

}
