package md.mi.controller.rest;

import md.mi.controller.rest.utils.RestUtils;
import md.mi.model.json.request.TransactionRequest;
import md.mi.service.impl.DbService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class ProtectedController {

    @Autowired
    private RestUtils restUtils;

    /**
      This is an example of some different kinds of granular restriction for endpoints. You can use the built-in SPEL expressions
      in @PreAuthorize such as 'hasRole()' to determine if a user has access. However, if you require logic beyond the methods
      Spring provides then you can encapsulate it in a service and register it as a bean to use it within the annotation as
      demonstrated below with 'securityService'.
     **/
    Logger logger = Logger.getLogger("Protected Contrller");
    @Autowired
    private DbService userDetailsServiceImpl;

    @RequestMapping(value="/protected", method = RequestMethod.GET)
    //@PreAuthorize("hasRole('ADMIN')")
    /*Check in class SecurityServiceImpl*/
    @PreAuthorize("@securityService.hasProtectedAccess()")
    public ResponseEntity<?> getDaHoney() {
        return ResponseEntity.ok(":Ok");
    }



    @RequestMapping(value ="/account",method = RequestMethod.GET)
    public ResponseEntity<?> getAccount(HttpServletRequest request) {
        logger.info("Request " + request);
        return restUtils.getAccount();
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    public ResponseEntity<?> makeTransaction(@Valid TransactionRequest transaction) {

        return restUtils.makeTransaction(transaction);
    }
}
