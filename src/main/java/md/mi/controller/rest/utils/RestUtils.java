package md.mi.controller.rest.utils;

import md.mi.domain.entity.Account;
import md.mi.domain.entity.User;
import md.mi.domain.entity.UserBuilder;
import md.mi.model.json.request.RegisterRequest;
import md.mi.model.json.response.AccountInfo;
import md.mi.model.json.response.ErrorResponse;
import md.mi.model.json.response.RegisterResponse;
import md.mi.model.security.AuthUser;
import md.mi.service.impl.DBService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class RestUtils {

    @Autowired
    private DBService dbServiceImpl;
    ErrorResponse error;

    public ResponseEntity<?> registerNewUser(RegisterRequest registerRequest){
        try{
            dbServiceImpl.loadUserByUsername(registerRequest.getEmail());
            error = new ErrorResponse("User already exists");
            return new ResponseEntity<ErrorResponse>(error, HttpStatus.CONFLICT);
        }
        catch(UsernameNotFoundException ex) {
            UserBuilder userBuilder = new UserBuilder(registerRequest.getPhone(), registerRequest.getPassword(), registerRequest.getEmail());
            User newUser = dbServiceImpl.saveAndFlush(userBuilder.authorities("USER").build());
            Account newAccount = new Account(newUser.getId(), 0, 820, "Active");
            dbServiceImpl.saveAndFlushAccount(newAccount);
            return ResponseEntity.ok(new RegisterResponse(newUser.getUsername()));
        }

    }

    public ResponseEntity<?> getAccount() {
        AuthUser user = (AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = dbServiceImpl.findAccountByUserid(user.getId());
        if(account == null){
            error = new ErrorResponse("No account for " + user.getUsername());
            return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
        }
        AccountInfo accountResponse = new AccountInfo();
        accountResponse.setBalance(account.getBalance());
        accountResponse.setId(account.getId().toString());
        return ResponseEntity.ok(accountResponse);
    }

}
