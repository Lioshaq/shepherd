package md.mi.controller.rest;

import md.mi.domain.entity.Account;
import md.mi.model.json.response.AccountInfo;
import md.mi.model.security.AuthUser;
import md.mi.service.impl.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ProtectedController {

    /**
      This is an example of some different kinds of granular restriction for endpoints. You can use the built-in SPEL expressions
      in @PreAuthorize such as 'hasRole()' to determine if a user has access. However, if you require logic beyond the methods
      Spring provides then you can encapsulate it in a service and register it as a bean to use it within the annotation as
      demonstrated below with 'securityService'.
     **/
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @RequestMapping(value="/protected", method = RequestMethod.GET)
    //@PreAuthorize("hasRole('ADMIN')")
    /*Check in class SecurityServiceImpl*/
    @PreAuthorize("@securityService.hasProtectedAccess()")
    public ResponseEntity<?> getDaHoney() {
        return ResponseEntity.ok(":Ok");
    }



    @RequestMapping(value ="/account",method = RequestMethod.GET)
    public ResponseEntity<?> getAccount() {

        AuthUser user = (AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = userDetailsServiceImpl.findAccountByUserid(user.getId());
        AccountInfo accountResponse = new AccountInfo();
        accountResponse.setBalance(account.getBalance());
        accountResponse.setId(account.getId());
        return ResponseEntity.ok(accountResponse);
    } 
}
