package com.inacioalves.crud.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.inacioalves.crud.data.DTO.ProdutoDTO;
import com.inacioalves.crud.exception.ResourceNotFoundException;
import com.inacioalves.crud.message.ProdutoSendMessage;
import com.inacioalves.crud.model.Produto;
import com.inacioalves.crud.repository.ProdutoRepository;

@Service
public class ProdutoService {

	private final ProdutoRepository produtoRepository;
	private final ProdutoSendMessage produtoSendMessage;

	@Autowired
	public ProdutoService(ProdutoRepository produtoRepository, ProdutoSendMessage produtoSendMessage) {
		this.produtoRepository = produtoRepository;
		this.produtoSendMessage = produtoSendMessage;
	}
	
	public ProdutoDTO create(ProdutoDTO produtoDTO) {
		ProdutoDTO proDTORetorno = ProdutoDTO.create(produtoRepository.save(Produto.create(produtoDTO)));
	 	produtoSendMessage.sendMessage(proDTORetorno);
		return proDTORetorno;
	}
	
	public Page<ProdutoDTO> findAll(Pageable pageable) {
		var page = produtoRepository.findAll(pageable);
		return page.map(this::convertToprodutoDTO);
	}

	private ProdutoDTO convertToprodutoDTO(Produto produto) {
		return ProdutoDTO.create(produto);
	}

	public ProdutoDTO findById(Long id) {
		var entity = produtoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return ProdutoDTO.create(entity);
	}
	
	public ProdutoDTO update(ProdutoDTO produtoDTO) {
		final Optional<Produto> optionalProduto = produtoRepository.findById(produtoDTO.getId());
		
		if(!optionalProduto.isPresent()) {
			new ResourceNotFoundException("No records found for this ID");
		}
		
		return ProdutoDTO.create(produtoRepository.save(Produto.create(produtoDTO)));
	}
	
	public void delete(Long id) {
		var entity = produtoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		produtoRepository.delete(entity);
	}
}
