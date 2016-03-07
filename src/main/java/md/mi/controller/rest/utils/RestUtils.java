package md.mi.controller.rest.utils;

import md.mi.domain.entity.Account;
import md.mi.domain.entity.User;
import md.mi.domain.entity.UserBuilder;
import md.mi.model.json.request.RegisterRequest;
import md.mi.model.json.request.TransactionRequest;
import md.mi.model.json.response.AccountInfo;
import md.mi.model.json.response.ErrorResponse;
import md.mi.model.json.response.RegisterResponse;
import md.mi.model.security.AuthUser;
import md.mi.service.impl.DbService;

import md.mi.transaction.exceptions.TransactionTypeException;
import md.mi.transaction.handler.TransactionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class RestUtils {

    @Autowired
    private DbService dbService;

    ErrorResponse error;

    TransactionHandler handler;
/*
    @PostConstruct
    private void initHandlers(){
        handler = new EmailTransactionHandler();
        //Handler h2 = new SpecificHandler();
        //handler.setSuccessor(h2);...
    }
*/
    public ResponseEntity<?> registerNewUser(RegisterRequest registerRequest){
        try{
            dbService.loadUserByUsername(registerRequest.getEmail());
            error = new ErrorResponse("User already exists");
            return new ResponseEntity<ErrorResponse>(error, HttpStatus.CONFLICT);
        }
        catch(UsernameNotFoundException ex) {
            UserBuilder userBuilder = new UserBuilder(registerRequest.getPhone(), registerRequest.getPassword(), registerRequest.getEmail());
            User newUser = dbService.saveAndFlush(userBuilder.authorities("USER").build());
            Account newAccount = new Account(newUser.getId(), 0, 820, "Active");
            dbService.saveAndFlushAccount(newAccount);
            return ResponseEntity.ok(new RegisterResponse(newUser.getUsername()));
        }

    }

    public ResponseEntity<?> getAccount() {
        AuthUser user = (AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = dbService.findAccountByUserid(user.getId());
        if(account == null){
            error = new ErrorResponse("No account for " + user.getUsername());
            return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
        }
        AccountInfo accountResponse = new AccountInfo();
        accountResponse.setBalance(account.getBalance());
        accountResponse.setId(account.getId().toString());
        return ResponseEntity.ok(accountResponse);
    }

    public ResponseEntity<?> makeTransaction(TransactionRequest transactionRequest) {
        try {
            handler.handleRequest(transactionRequest);
            return null;
        }
        catch(TransactionTypeException ex) {
            ErrorResponse response = new ErrorResponse(ex.toString());
            response.setError("Wrong operation");
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setPath("/transaction");
            return new ResponseEntity<ErrorResponse>(response, HttpStatus.FORBIDDEN);
        }
        catch (Exception ex) {
            ErrorResponse response = new ErrorResponse(ex.toString());
            response.setError("Unknown");
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setPath("/transaction");

            return new ResponseEntity<ErrorResponse>(response, HttpStatus.FORBIDDEN);
        }
    }

}
