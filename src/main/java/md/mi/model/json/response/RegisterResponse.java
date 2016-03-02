
package md.mi.model.json.response;

public class RegisterResponse {

    private String username;



    public RegisterResponse(String username) {
        super();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
