package com.app.Hospital.Management.System.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
		@Id
		@Email(message = "Email should be valid")
	    @NotBlank(message = "Email is mandatory")
	    @Column(nullable=false, unique=true)
	    private String email;

	    @NotBlank(message = "Password is mandatory")
	    @Column(nullable=false)
	    private String password;
	    
}
