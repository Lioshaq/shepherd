package md.mi.controller.rest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import md.mi.controller.rest.utils.RestUtils;
import md.mi.model.json.request.RegisterRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {


    @Autowired
    private RestUtils restUtils;


    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest, HttpServletRequest request) {
        return restUtils.registerNewUser(registerRequest);
    }
}
