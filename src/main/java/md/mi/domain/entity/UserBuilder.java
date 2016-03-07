package md.mi.domain.entity;

import java.util.Date;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserBuilder {

    private String builderPhone;
    private String builderPassword;
    private String builderEmail;
    private Date builderLastPasswordReset;
    private String builderAuthorities;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public UserBuilder(String builderPhone, String builderPassword,
            String builderEmail) {
        super();
        this.builderPhone = builderPhone;
        this.builderPassword = encoder.encode(builderPassword);
        this.builderEmail = builderEmail;
    }

    public UserBuilder lastPasswordReset(Date builderLastPasswordReset){
        this.builderLastPasswordReset = builderLastPasswordReset;
        return this;
    }

    public UserBuilder authorities(String builderAuthorities){
        this.builderAuthorities = builderAuthorities;
        return this;
    }

    public User build() {
        UUID uid = UUID.randomUUID();
        return new User(uid, builderEmail, builderPassword, builderPhone,
                builderEmail, builderLastPasswordReset, builderAuthorities);
    }






}
