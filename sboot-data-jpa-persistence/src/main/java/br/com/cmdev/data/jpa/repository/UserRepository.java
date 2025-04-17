package br.com.cmdev.data.jpa.repository;

import br.com.cmdev.data.jpa.entity.User;
import br.com.cmdev.data.jpa.utils.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds all users matching the specified role.
     *
     * @param role The UserRole to search for.
     * @return A List of User entities matching the role, or an empty list if none found.
     */
    List<User> findAllByRole(UserRole role);

}
