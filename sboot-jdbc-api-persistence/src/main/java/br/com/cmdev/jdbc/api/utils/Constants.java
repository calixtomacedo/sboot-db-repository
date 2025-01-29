package br.com.cmdev.jdbc.api.utils;

public class Constants {

    public static final String DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    public static final String DATETIME_MILLISECONDS_FORMAT = "dd/MM/yyyy HH:mm:ss:SSS";

    public static final String INSERT_USERS = "INSERT INTO users(name, email, password, role, is_active, creation_date) VALUES(:name, :email, :password, :role, :is_active, :creation_date)";
    public static final String SQL_GET_ALL_USERS = "SELECT * FROM users";
    public static final String SQL_GET_USERS_BY_ID = "SELECT * FROM users WHERE id = :id";
    public static final String SQL_UPDATE_USERS = "UPDATE users SET name = :name, email = :email, password = :password, role = :role, is_active = :is_active, change_date = :change_date WHERE id = :id";
    public static final String SQL_DELETE_USERS = "DELETE FROM users WHERE id = :id";
    public static final String SQL_GET_USERS_BY_NAME_AND_EMAIL = "SELECT * FROM users WHERE name = :name AND email = :email";

    /** Table users names columns */
    public static final String TABLE_USERS_ID = "id";
    public static final String TABLE_USERS_NAME = "name";
    public static final String TABLE_USERS_EMAIL = "email";
    public static final String TABLE_USERS_PASSWORD = "password";
    public static final String TABLE_USERS_ROLE = "role";
    public static final String TABLE_USERS_ISACTIVE = "is_active";
    public static final String TABLE_USERS_CREATIONDATE = "creation_date";
    public static final String TABLE_USERS_CHANGEDATE = "change_date";
}
