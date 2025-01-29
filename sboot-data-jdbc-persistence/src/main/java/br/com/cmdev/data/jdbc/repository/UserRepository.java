package br.com.cmdev.data.jdbc.repository;

import br.com.cmdev.data.jdbc.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ListCrudRepository<User, Long> {

    Iterable<User> findAll(Pageable pageable);

}
