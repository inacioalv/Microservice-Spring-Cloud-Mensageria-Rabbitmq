package com.inacioalves.pagamento.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inacioalves.pagamento.data.DTO.VendaDTO;
import com.inacioalves.pagamento.services.VendaService;

@RestController
@RequestMapping("/venda")
public class VendaController {

	@Value("${server.port}")
	String port;
	
	private final VendaService vendaService;
	private final PagedResourcesAssembler<VendaDTO> assembler;
	
	@Autowired
	public VendaController(VendaService vendaService, PagedResourcesAssembler<VendaDTO> assembler) {
		this.vendaService = vendaService;
		this.assembler = assembler;
	}
	
	@RequestMapping("/mostrarPorta")
	public String mostrarPorta() {
		return port;
	}
	
	@GetMapping(value = "/{id}", produces = {"application/json","application/xml","application/x-yaml"})
	public VendaDTO findById(@PathVariable("id")  Long id) {
		VendaDTO vendaDTO = vendaService.findById(id);
		vendaDTO.add(linkTo(methodOn(VendaController.class).findById(id)).withSelfRel());
		return vendaDTO;
	}
	
	@GetMapping(produces = {"application/json","application/xml","application/x-yaml"})
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page,limit, Sort.by(sortDirection,"data"));
		
		Page<VendaDTO> vendas = vendaService.findAll(pageable);
		vendas.stream()
				.forEach(p -> p.add(linkTo(methodOn(VendaController.class).findById(p.getId())).withSelfRel()));
		
		PagedModel<EntityModel<VendaDTO>> pagedModel = assembler.toModel(vendas);
		
		return new ResponseEntity<>(pagedModel,HttpStatus.OK);
	}
	
	@PostMapping(produces = {"application/json","application/xml","application/x-yaml"}, 
			     consumes = {"application/json","application/xml","application/x-yaml"})
	public VendaDTO create(@RequestBody VendaDTO vendaDTO) {
		VendaDTO proVo = vendaService.create(vendaDTO);
		proVo.add(linkTo(methodOn(VendaController.class).findById(proVo.getId())).withSelfRel());
		return proVo;
	}
	
	@PutMapping(produces = {"application/json","application/xml","application/x-yaml"}, 
		     consumes = {"application/json","application/xml","application/x-yaml"})
	public VendaDTO update(@RequestBody VendaDTO vendaDTO) {
		VendaDTO venDTO = vendaService.update(vendaDTO);
		venDTO.add(linkTo(methodOn(VendaController.class).findById(vendaDTO.getId())).withSelfRel());
		return venDTO;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		vendaService.delete(id);
		return ResponseEntity.ok().build();
	}
}
