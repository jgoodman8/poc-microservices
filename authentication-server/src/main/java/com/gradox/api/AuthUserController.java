package com.gradox.api;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gradox.model.dao.IUserDAO;
import com.gradox.model.vo.User;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * REST endpoint to be used by other micro-services using SSO to validate the
 * authentication of the logged in user.
 * 
 * @author anilallewar
 *
 */
@RestController
@RequestMapping("/")
public class AuthUserController {

	@Autowired
	private IUserDAO userDAO;

	/**
	 * Return the principal identifying the logged in user
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/me")
	public Principal getCurrentLoggedInUser(Principal user) {
		return user;
	}
	
	@RequestMapping("/register")
	@ApiOperation( 
		    value = "Register", 
		    notes = "Register a new user", 
		    response = User.class 
		)
	public ResponseEntity<?> registerNewUser(User user) {
		try {
			User res = userDAO.register(user);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
