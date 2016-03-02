package md.mi.model.json.request;

import javax.validation.constraints.NotNull;

import md.mi.model.base.ModelBase;

public class RegisterRequest extends ModelBase{


    /**
     * Register JSON
     */
    private static final long serialVersionUID = 1L;
    @NotNull
    private String phone;
    @NotNull
    private String email;
    @NotNull
    private String password;

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
