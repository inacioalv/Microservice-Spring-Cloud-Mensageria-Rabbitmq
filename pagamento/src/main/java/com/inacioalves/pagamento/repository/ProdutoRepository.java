package com.inacioalves.pagamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.inacioalves.pagamento.model.Produto;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long>{
	
	 @GetMapping(value = "/produto/{id}")
	 public Produto getProdutoById(@PathVariable Long id);
	 

}
