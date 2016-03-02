package md.mi.model.factory;


import md.mi.domain.entity.User;
import md.mi.model.security.AuthUser;

import org.springframework.security.core.authority.AuthorityUtils;

public class AuthUserFactory {

    public static AuthUser create(User user) {
        return new AuthUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getLastPasswordReset(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities())
                );
    }

}
