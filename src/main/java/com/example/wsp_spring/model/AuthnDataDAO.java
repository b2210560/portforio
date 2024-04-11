package com.example.wsp_spring.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthnDataDAO {
    private  final JdbcTemplate jdbcTemplate;
    public  AuthnDataDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public  AuthnValue find(String userId){
        String sql= """
                select user_id, user_password, user_name
                from authn_data where user_id = ?
                """;

        AuthnValue authnValue=
                jdbcTemplate.queryForObject(sql,
                        DataClassRowMapper.newInstance(AuthnValue.class),userId);
        System.out.printf("%s, %s\n", authnValue.userId(), authnValue.userName());
    return authnValue;
    }

}