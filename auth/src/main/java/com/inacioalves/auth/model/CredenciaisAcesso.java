package com.inacioalves.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredenciaisAcesso {
	
	private String email;
	private String senha;

}
