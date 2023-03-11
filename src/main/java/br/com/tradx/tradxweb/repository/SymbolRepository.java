package br.com.tradx.tradxweb.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.tradx.tradxweb.model.Symbol;

@Repository
@Transactional
public interface SymbolRepository extends CrudRepository<Symbol, UUID> {
    
}
