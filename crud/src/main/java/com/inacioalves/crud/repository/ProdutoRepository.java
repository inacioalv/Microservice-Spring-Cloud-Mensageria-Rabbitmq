package com.inacioalves.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inacioalves.crud.model.Produto;


public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
