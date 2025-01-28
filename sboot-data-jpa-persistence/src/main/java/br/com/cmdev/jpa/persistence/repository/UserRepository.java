package br.com.cmdev.jpa.persistence.repository;

import br.com.cmdev.jpa.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
