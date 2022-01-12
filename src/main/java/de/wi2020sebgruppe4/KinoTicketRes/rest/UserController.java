package de.wi2020sebgruppe4.KinoTicketRes.rest;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.lang.Object;

import de.wi2020sebgruppe4.KinoTicketRes.SendingTicketsViaMail.ResetPassword.GenericResponse;
import Token.PasswordResetToken;
import de.wi2020sebgruppe4.KinoTicketRes.repositories.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import de.wi2020sebgruppe4.KinoTicketRes.model.User;
import de.wi2020sebgruppe4.KinoTicketRes.model.UserRequestObject;
import de.wi2020sebgruppe4.KinoTicketRes.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
@RestController
@CrossOrigin(origins = 
{"https://kinoticketres.web.app",
	"https://localhost/",
	"https://localhost:3000/",
	"https://localhost:3001/",
	"https://localhost:3002/",
	"http://localhost/",
	"http://localhost:3000/",
	"http://localhost:3001/",
	"http://localhost:3002/",
	"http://localhost:4200/"})
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository repo;

	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;

	@Autowired
	ApplicationEventPublisher applicationEventPublisher;
	
	
	@GetMapping("")
	public ResponseEntity<Iterable<User>> getUsers(){
		return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
	}	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getSpecific(@PathVariable UUID id){
		
		Optional<User> user = repo.findById(id);
		
		try {
			User toReturn = user.get();
			return new ResponseEntity<Object>(toReturn, HttpStatus.OK);
		}
		catch(NoSuchElementException e) {
			return new ResponseEntity<Object>("User "+id+" not found!", HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@PutMapping("/add")
	public ResponseEntity<Object> addUser(@RequestBody UserRequestObject uro){
		User toAddUser = new User();
		toAddUser.setUserName(uro.userName);
		toAddUser.setName(uro.name);
		toAddUser.setFirstName(uro.firstName);
		toAddUser.setEmail(uro.email);
		toAddUser.setPassword(uro.password);
		return new ResponseEntity<Object>( repo.save(toAddUser), HttpStatus.CREATED);
	}

	@PostMapping("user/resetPassword")
	public GenericResponse resetPassword(HttpServletRequest request, @RequestParam("email") String userEmail){
		Optional<User> user = repo.findUserByEmail(userEmail);
		if (user == null){
		}
		String token = UUID.randomUUID().toString();
		PasswordResetToken myToken = new PasswordResetToken();
		passwordResetTokenRepository.save(myToken);
		return null;
	}//Die Methode funktioniert nicht!



	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable UUID id){
		Optional<User> o = repo.findById(id);
		try {
			repo.deleteById(o.get().getId());
			return new ResponseEntity<>(id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("User "+id+" not found!", HttpStatus.NOT_FOUND);
		}
	}

}
