package com.inacioalves.pagamento.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inacioalves.pagamento.data.DTO.ProdutoDTO;
import com.inacioalves.pagamento.exception.ResourceNotFoundException;
import com.inacioalves.pagamento.model.Produto;
import com.inacioalves.pagamento.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	private final ProdutoRepository produtoRepository;

	@Autowired
	public ProdutoService(ProdutoRepository produtoRepository) {
		super();
		this.produtoRepository = produtoRepository;
	}
	

	public ProdutoDTO produtofindById(Long id) {
		var entity = produtoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return ProdutoDTO.create(entity);
	}
	
	public List<Produto> listaProduto(){
		return produtoRepository.findAll();
	}

}
