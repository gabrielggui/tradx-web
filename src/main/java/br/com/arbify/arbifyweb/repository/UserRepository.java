package br.com.arbify.arbifyweb.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.arbify.arbifyweb.model.User;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, UUID> {
    
    Optional<User> findByUsername(String username);

}
