package com.inacioalves.pagamento.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.inacioalves.pagamento.data.DTO.VendaDTO;
import com.inacioalves.pagamento.exception.DataIntegrityException;
import com.inacioalves.pagamento.exception.ResourceNotFoundException;
import com.inacioalves.pagamento.model.ProdutoVenda;
import com.inacioalves.pagamento.model.Venda;
import com.inacioalves.pagamento.repository.ProdutoVendaRepository;
import com.inacioalves.pagamento.repository.VendaRepository;

@Service
public class VendaService {

	
	private final VendaRepository vendaRepository;
	private final ProdutoVendaRepository produtoVendaRepository;

	@Autowired
	public VendaService(VendaRepository vendaRepository,ProdutoVendaRepository produtoVendaRepository) {
		this.vendaRepository = vendaRepository;
		this.produtoVendaRepository =  produtoVendaRepository;
	}
	
	public VendaDTO create(VendaDTO vendaDTO) {
		Venda venda = vendaRepository.save(Venda.create(vendaDTO));
		
		List<ProdutoVenda> produtosSalvos =  new ArrayList<>();
		vendaDTO.getProdutos().forEach(p -> {
			ProdutoVenda pv = ProdutoVenda.create(p);
			pv.setVenda(venda);
			produtosSalvos.add(produtoVendaRepository.save(pv));
		});
		venda.setProdutos(produtosSalvos);
		
		return VendaDTO.create(venda);
	}
	
	public Page<VendaDTO> findAll(Pageable pageable) {
		var page = vendaRepository.findAll(pageable);
		return page.map(this::convertTovendaDTO);
	}

	private VendaDTO convertTovendaDTO(Venda venda) {
		return VendaDTO.create(venda);
	}
	
	public VendaDTO findById(Long id) {
		var entity = vendaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return VendaDTO.create(entity);
	}
	
	public VendaDTO update (VendaDTO vendaDTO) {
		Venda venda = vendaRepository.save(Venda.create(vendaDTO));
	    
		if(vendaDTO.getProdutos() != null) {
			List<ProdutoVenda> produtosSalvos =  new ArrayList<>();
			vendaDTO.getProdutos().forEach(p -> {
				ProdutoVenda pv = ProdutoVenda.create(p);
				pv.setVenda(venda);
				produtosSalvos.add(produtoVendaRepository.save(pv));
			});
			venda.setProdutos(produtosSalvos);
		}
		
		return VendaDTO.create(venda);
	}
	
	public void delete(Long id) {
		try {
			verifyIfExists(id);
			vendaRepository.deleteById(id);
		} catch (DataIntegrityViolationException  e) {
			throw new DataIntegrityException("Não é possível deletar um evento com atividades vinculadas a ele.");
		}
	}
	
	private Venda verifyIfExists(Long id) throws ResourceNotFoundException {
		return vendaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Venda not found with ID:"+id));
	}
}
