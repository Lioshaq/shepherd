package md.mi.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * Spring Security logout handler, specialized for Ajax requests.
 */
@Component
public class AjaxLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler
implements LogoutSuccessHandler {

    public static final String BEARER_AUTHENTICATION = "Bearer ";


    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication)
                    throws IOException, ServletException {

        //logout do something with the token
        /*
         * 1) Simply remove the token from the client
         *
         *  Obviously this does nothing for server side security, but it does stop an attacker by removing the token from existence (ie. they would have to have stolen the token prior to logout).

            2) Create a token blacklist

            You could store the invalid tokens until their initial expiry date, and compare them against incoming requests. This seems to negate the reason for going fully token based in the first place though, as you would need to touch the database for every request. The storage size would likely be lower though, as you would only need to store tokens that were between logout & expiry time (this is a gut feeling, and is definitely dependent on context).

            3) Just keep token expiry times short and rotate them often

            If you keep the token expiry times at short enough intervals, and have the running client keep track and request updates when necessary, number 1 would effectively work as a complete logout system. The problem with this method, is that it makes it impossible to keep the user logged in between closes of the client code (depending on how long you make the expiry interval).
         * 
         * 
         * 
         * 
         * 
         * */
    }
}
