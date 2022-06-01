package com.inacioalves.auth.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import com.inacioalves.auth.enums.PerfilAcesso;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User implements Serializable{
	
	private static final long serialVersionUID = 2099843992076296774L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "senha")
	private String senha;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable
	private Set<Integer> perfis = new HashSet<>();
	
	public void addPerfil(PerfilAcesso perfilAcesso) {
		perfis.add(perfilAcesso.getCodigo());
	}
	
	public Set<PerfilAcesso> getPerfis(){
		return perfis.stream().map(x -> PerfilAcesso.toEnum(x)).collect(Collectors.toSet());
	}
	
	

}
