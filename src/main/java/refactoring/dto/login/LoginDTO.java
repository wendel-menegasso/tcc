package refactoring.dto.login;

import refactoring.entity.usuarios.Users;
import lombok.Getter;

@Getter
public class LoginDTO {

        private String username;
        private String password;
        private String token;
        private String name;

    public Users parseLoginDTOToUsers(){
        return new Users(this.username, this.password, this.token, this.name);
    }

}
