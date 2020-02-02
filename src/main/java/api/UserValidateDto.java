
package api;

/**
 * @author rahul.kumar
 * @version $Id: UserValidateDto.java, v 0.1 2020-02-01 12:28 rahul.kumar Exp $$
 */
public class UserValidateDto {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}