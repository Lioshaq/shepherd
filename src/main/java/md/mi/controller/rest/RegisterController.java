package md.mi.controller.rest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import md.mi.domain.entity.Account;
import md.mi.domain.entity.User;
import md.mi.domain.entity.UserBuilder;
import md.mi.model.json.request.RegisterRequest;
import md.mi.model.json.response.RegisterResponse;
import md.mi.service.impl.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;



    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest, HttpServletRequest request) {
        try{
            userDetailsServiceImpl.loadUserByUsername(registerRequest.getEmail());
            return ResponseEntity.ok(HttpStatus.CONFLICT);
        }
        catch(UsernameNotFoundException ex) {
            UserBuilder userBuilder = new UserBuilder(registerRequest.getPhone(), registerRequest.getPassword(), registerRequest.getEmail());
            User newUser = userDetailsServiceImpl.saveAndFlush(userBuilder.authorities("USER").build());
            Account newAccount = new Account(newUser.getId(), 0, 820, "Active");
            Account savedAccount = userDetailsServiceImpl.saveAndFlushAccount(newAccount);
            return ResponseEntity.ok(new RegisterResponse(newUser.getId()));
        }
    }
}
