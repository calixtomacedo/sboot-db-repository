package br.com.cmdev.jdbc.api.repository;

import br.com.cmdev.jdbc.api.dto.UserResponse;
import br.com.cmdev.jdbc.api.entity.User;
import br.com.cmdev.jdbc.api.utils.Constants;
import br.com.cmdev.jdbc.api.utils.PageAndSort;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final JdbcClient jdbcClient;

    public UserRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public void save(User user) {
        jdbcClient.sql(Constants.INSERT_USERS)
                .param(Constants.TABLE_USERS_NAME, user.getName())
                .param(Constants.TABLE_USERS_EMAIL, user.getEmail())
                .param(Constants.TABLE_USERS_PASSWORD, user.getPassword())
                .param(Constants.TABLE_USERS_ROLE, user.getRole().name())
                .param(Constants.TABLE_USERS_ISACTIVE, user.getActive())
                .param(Constants.TABLE_USERS_CREATIONDATE, user.getCreationDate())
                .update();

    }

    public Optional<UserResponse> findByNameAndEmail(User user) {
        return Optional.of(jdbcClient.sql(Constants.SQL_GET_USERS_BY_NAME_AND_EMAIL)
                .param(Constants.TABLE_USERS_NAME, user.getName())
                .param(Constants.TABLE_USERS_EMAIL, user.getEmail())
                .query(UserResponse.class).single());
    }

    public List<UserResponse> findAll(PageAndSort pageAndSort){
        return jdbcClient.sql(Constants.SQL_GET_ALL_USERS)
                .param(Constants.PAGE, pageAndSort.page() * pageAndSort.size())
                .param(Constants.SIZE, pageAndSort.size())
                .query(UserResponse.class).list();
    }

    public Optional<UserResponse> findById(Long id) {
        return jdbcClient.sql(Constants.SQL_GET_USERS_BY_ID)
                .param(Constants.TABLE_USERS_ID, id)
                .query(UserResponse.class).optional();
    }

    public void update(User user) {
        jdbcClient.sql(Constants.SQL_UPDATE_USERS)
                .param(Constants.TABLE_USERS_NAME, user.getName())
                .param(Constants.TABLE_USERS_EMAIL, user.getEmail())
                .param(Constants.TABLE_USERS_PASSWORD, user.getPassword())
                .param(Constants.TABLE_USERS_ROLE, user.getRole().name())
                .param(Constants.TABLE_USERS_ISACTIVE, user.getActive())
                .param(Constants.TABLE_USERS_CHANGEDATE, user.getChangeDate())
                .param(Constants.TABLE_USERS_ID, user.getId())
                .update();

    }

    public void delete(Long id) {
        jdbcClient.sql(Constants.SQL_DELETE_USERS).param(Constants.TABLE_USERS_ID, id).update();
    }

}
