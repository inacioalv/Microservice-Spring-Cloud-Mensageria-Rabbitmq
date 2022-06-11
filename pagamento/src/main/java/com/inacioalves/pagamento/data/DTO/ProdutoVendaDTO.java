package com.inacioalves.pagamento.data.DTO;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.inacioalves.pagamento.model.ProdutoVenda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonPropertyOrder({ "id", "idProduto", "quantidade","nome_produto" })
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProdutoVendaDTO extends RepresentationModel<ProdutoVendaDTO> implements Serializable {

	private static final long serialVersionUID = 2048293510771579865L;

	@JsonProperty("id")
	private Long id;

	@JsonProperty("idProduto")
	private Long idProduto;

	@JsonProperty("quantidade")
	private Integer quantidade;
	
	@JsonProperty("nome_produto")
	private String nome_produto;

	public static ProdutoVendaDTO create(ProdutoVenda produtoVenda) {
		return new ModelMapper().map(produtoVenda, ProdutoVendaDTO.class);
	}

}
