package com.inacioalves.produto.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.inacioalves.produto.data.DTO.ProdutoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "produto")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Produto implements Serializable {

	private static final long serialVersionUID = -14620858697104911L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", nullable = false, length = 255)
	private String nome;
	
	@Column(name = "estoque", nullable = false, length = 10)
	private Integer estoque;
	
	@Column(name = "preco", nullable = false, length = 10)
	private Double preco;
	
	public static Produto create(ProdutoDTO produtoDTO) {
		return new ModelMapper().map(produtoDTO, Produto.class);
	}
}
