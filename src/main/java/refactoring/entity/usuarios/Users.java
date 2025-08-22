package refactoring.entity.usuarios;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Getter
@NoArgsConstructor
public class Users {
	
	@Id
	@Column
    private long id;

    @Column
    private String username;
    
    @Column
    private String password;
    
    @Column
    private String token;

    @Column
    private String name;

    public Users(String username, String password, String token, String name){
        this.username = username;
        this.password = password;
        this.token = token;
        this.name = name;
    }

    public void setId(long id){
        this.id = id;
    }

}
