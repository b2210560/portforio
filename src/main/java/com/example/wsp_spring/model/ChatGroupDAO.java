package com.example.wsp_spring.model;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatGroupDAO {
    private  final JdbcTemplate jdbcTemplate;
    public  ChatGroupDAO(JdbcTemplate jdbcTemplate){this.jdbcTemplate=jdbcTemplate;}

    public List<ChatGroup> groupList(){
        String sql = """
           select chat_group
           from chat_item left join authn_data
           group by chat_group
           """;

        List<ChatGroup> chatItemValueList
                = jdbcTemplate.query(sql, DataClassRowMapper.newInstance(ChatGroup.
                class));
        return chatItemValueList;
    }
}
