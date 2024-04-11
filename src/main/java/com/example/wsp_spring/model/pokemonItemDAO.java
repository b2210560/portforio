package com.example.wsp_spring.model;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class pokemonItemDAO {
    private final JdbcTemplate jdbcTemplate;

    public  pokemonItemDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public void insert(UserValue userValue,pokemonData pokemonData){
        String sql="insert into pokemon_data values(?,?,?,?,?)";
        String userId=userValue.userId();
        String pokemonNum=Integer.toString(pokemonData.ID());
        String pokemonName=pokemonData.Name();
        String pokemonImage=pokemonData.ImageURL();
        LocalDateTime timeStamp=LocalDateTime.now();
        jdbcTemplate.update(sql,userId,timeStamp,pokemonNum,pokemonName,pokemonImage);
    }

    public List<pokemonItemValue> findAllOrderByTime(){
        String sql = """
           select pokemon_data.user_id, user_name, pokemon_name, pokemon_image,pokemon_number, posted_at
           from pokemon_data left join authn_data
           on pokemon_data.user_id =authn_data.user_id
           order by posted_at desc
           """;
        List<pokemonItemValue> pokemonItemValueList
                =jdbcTemplate.query(sql, DataClassRowMapper.newInstance(pokemonItemValue.class));
        return pokemonItemValueList;
    }

    public void deletePokemonData(String delete_value){
    String sql="""
                    delete pokemon_data
                    where posted_at =values(?)""";
    jdbcTemplate.update(sql,delete_value);
    }
}
