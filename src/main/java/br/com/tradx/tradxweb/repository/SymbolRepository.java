package br.com.tradx.tradxweb.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.tradx.tradxweb.model.Symbol;

@Repository
@Transactional
public interface SymbolRepository extends CrudRepository<Symbol, UUID> {

    @Query("SELECT s FROM Symbol s " +
            "JOIN s.exchange e " +
            "WHERE s.baseCurrency IN (" +
            "SELECT s2.baseCurrency FROM Symbol s2 JOIN s2.exchange e2 GROUP BY s2.baseCurrency " +
            "HAVING COUNT(DISTINCT e2) = (SELECT COUNT(*) FROM Exchange))")
    public List<Symbol> listCommonCurrencyPairsAmongExchanges();
}
