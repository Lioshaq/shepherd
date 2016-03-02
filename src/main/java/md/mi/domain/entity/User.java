package md.mi.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import md.mi.domain.base.DomainBase;



@Entity
@Table(schema = "public", name = "users")
public class User extends DomainBase {

    private static final long serialVersionUID = 2353528370345499815L;
    private String id;
    private String username;
    private String password;
    private String email;
    private Date lastPasswordReset;
    private String authorities;
    private String phone;



    public User() {
        super();
    }

    public User(String id,String username, String password, String phone, String email, Date lastPasswordReset, String authorities) {
        this.setId(id);
        this.setUsername(username);
        this.setPassword(password);
        this.setPhone(phone);
        this.setEmail(email);
        this.setLastPasswordReset(lastPasswordReset);
        this.setAuthorities(authorities);
    }

    @Id
    @Column(name = "id")
    //    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    //    @SequenceGenerator(name = "users_seq", sequenceName = "users_seq", allocationSize = 1)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "username")
    public String getUsername() {
        return this.username;
    }

    @Column(name= "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "last_password_reset")
    public Date getLastPasswordReset() {
        return this.lastPasswordReset;
    }

    public void setLastPasswordReset(Date lastPasswordReset) {
        this.lastPasswordReset = lastPasswordReset;
    }

    @Column(name = "authorities")
    public String getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

}
