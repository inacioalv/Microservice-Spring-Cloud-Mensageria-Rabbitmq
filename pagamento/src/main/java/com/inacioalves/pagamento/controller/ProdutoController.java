package com.inacioalves.pagamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inacioalves.pagamento.data.DTO.ProdutoDTO;
import com.inacioalves.pagamento.model.Produto;
import com.inacioalves.pagamento.services.ProdutoService;

@RestController
@RequestMapping("produto")
public class ProdutoController {
	
	
	private final ProdutoService produtoService;
	
	@Autowired
	public ProdutoController(ProdutoService produtoService) {
		super();
		this.produtoService = produtoService;
	}

	@GetMapping("produto/{id}")
	public ProdutoDTO produtoFindById(@PathVariable Long id) {
		return produtoService.produtofindById(id);
	}
	
	@GetMapping("/lista")
	public List<Produto> listaProduto(){
		return produtoService.listaProduto();
	}

}
