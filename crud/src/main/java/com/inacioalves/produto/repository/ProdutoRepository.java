package com.inacioalves.produto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inacioalves.produto.model.Produto;


public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
