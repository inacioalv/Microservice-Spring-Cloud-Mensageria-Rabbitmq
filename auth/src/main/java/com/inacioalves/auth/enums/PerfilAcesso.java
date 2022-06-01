package com.inacioalves.auth.enums;

public enum PerfilAcesso {
	
	ADMINISTRADOR(1, "ROLE_ADMINISTRADOR"), 
	USUARIO(2, "ROLE_USUARIO");

	private int codigo;
	private String descricao;

	private PerfilAcesso(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static PerfilAcesso toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		for(PerfilAcesso o : PerfilAcesso.values()){
			if(codigo.equals(o.getCodigo())) {
				return o;
			}
		}
		
		throw new IllegalArgumentException("Código inválido: " + codigo);
	}

}
