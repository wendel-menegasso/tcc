package br.com.mba.engenharia.de.software.entity.usuarios;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
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

}
