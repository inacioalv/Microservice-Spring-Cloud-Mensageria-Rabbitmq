package com.inacioalves.pagamento.data.DTO;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.inacioalves.pagamento.model.Produto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({"id","estoque"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProdutoDTO extends RepresentationModel<ProdutoDTO> implements Serializable {

	private static final long serialVersionUID = -8169634160998032562L;

	@JsonProperty("id")	
	private Long id;
	
	@JsonProperty("estoque")
	private Integer estoque;
	
	public static ProdutoDTO create(Produto produto) {
		return new ModelMapper().map(produto, ProdutoDTO.class);
	}
}
