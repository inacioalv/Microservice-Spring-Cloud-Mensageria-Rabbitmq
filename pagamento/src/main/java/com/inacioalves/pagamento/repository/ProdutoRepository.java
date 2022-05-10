package com.inacioalves.pagamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inacioalves.pagamento.model.Produto;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long>{

}
