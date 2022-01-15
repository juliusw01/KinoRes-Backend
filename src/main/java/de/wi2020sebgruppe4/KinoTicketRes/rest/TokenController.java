package de.wi2020sebgruppe4.KinoTicketRes.rest;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.wi2020sebgruppe4.KinoTicketRes.model.PasswordResetObject;
import de.wi2020sebgruppe4.KinoTicketRes.model.Token;
import de.wi2020sebgruppe4.KinoTicketRes.model.TokenRequestObject;
import de.wi2020sebgruppe4.KinoTicketRes.model.User;
import de.wi2020sebgruppe4.KinoTicketRes.repositories.TokenRepository;
import de.wi2020sebgruppe4.KinoTicketRes.repositories.UserRepository;

@Controller
@RestController
@RequestMapping("/tokens")
public class TokenController {
		
	@Autowired
	private TokenRepository repo;
	
	@Autowired 
	private UserRepository userRepository;
	
	@GetMapping("")
	public ResponseEntity<Object> getAll() {
		return new ResponseEntity<Object>(repo.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/check/{id}")
	public ResponseEntity<Object> checkToken(@PathVariable UUID id) {
		Optional<Token> t = repo.findById(id);
		Token token;
		try {
			token = t.get();
			return new ResponseEntity<Object>(token.isValid(), HttpStatus.OK);
		}
		catch(NoSuchElementException e) {
			return new ResponseEntity<Object>("Token "+id+" not found :(", HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/reset")
	public ResponseEntity<Object> startReset(@RequestBody TokenRequestObject tro) {
		Token t = new Token();
		t.setValid(tro.valid);
		
		if(tro.userID == null) {
			return new ResponseEntity<Object>("Token must have userID", HttpStatus.NOT_ACCEPTABLE);
		}
		
		Optional<User> userSearch = userRepository.findById(tro.userID);
		
		try {
			User u = userSearch.get();
			u.setPassword("Ur not getting this so ez ;)");
			t.setUser(u);
		} 
		catch (NoSuchElementException e) { 
			return new ResponseEntity<Object>("UserID: "+ tro.userID +" not found :(", HttpStatus.NOT_FOUND);
		}
		Token saved = repo.save(t);
		
		//TODO: send mail with link to reset password! The mail should call the /reset/confirm method with the given token
		
		return new ResponseEntity<Object>(saved, HttpStatus.CREATED);
	}
	
	@PutMapping("/reset/confirm")
	public ResponseEntity<Object> doReset(@RequestBody PasswordResetObject pro) {
		Optional<Token> t = repo.findById(pro.tokenID);
		
		try {
			Token token = t.get();
			
			// Check if its the same user that requested the reset
			if(!pro.userID.toString().equals(token.getUser().getId().toString())) {
				return new ResponseEntity<Object>("UserID is not valid!", HttpStatus.UNAUTHORIZED);
			}
			
			// Check if Token was used before and therefore is not valid
			if(!token.isValid()) {
				return new ResponseEntity<Object>("Token is not valid! Was it used already?", HttpStatus.UNAUTHORIZED);
			}
			
			token.setValid(false);
			Optional<User> u = userRepository.findById(pro.userID);
			try {
				User user = u.get();
				user.setPassword(pro.password);
				userRepository.save(user);
			}
			catch (NoSuchElementException e) {
				return new ResponseEntity<Object>("UserID: "+ pro.userID +" not found :(", HttpStatus.NOT_FOUND);
			}
		}
		catch (NoSuchElementException e) { 
			return new ResponseEntity<Object>("TokenID: "+ pro.tokenID +" not found :(", HttpStatus.NOT_FOUND);
		}
		
		return null;
	}
	
	@PutMapping("/deleteAll")
	public ResponseEntity<Object> deleteAll() {
		repo.deleteAll();
		return new ResponseEntity<Object>("deleted all Tokens", HttpStatus.OK);
	}
	
}

