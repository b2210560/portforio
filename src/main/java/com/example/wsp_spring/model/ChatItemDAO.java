package com.example.wsp_spring.model;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ChatItemDAO {
    private final JdbcTemplate jdbcTemplate;

    public ChatItemDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void insert(UserValue userValue, String chatBody,String ChatGroup) {
        String sql = "insert into chat_item values(?,?,?,?)";
        String userId = userValue.userId();
        LocalDateTime timestamp = LocalDateTime.now();
        jdbcTemplate.update(sql, userId, timestamp, chatBody,ChatGroup);
    }

    public List<ChatItemValue> findALLOrderByTime() {
        //chat_itemとauthn_dataを結合しuser?idにあったuser_nameを一緒に検索する
        String sql = """
           select chat_item.user_id, user_name, chat_body, posted_at,chat_group
           from chat_item left join authn_data
           on chat_item.user_id =authn_data.user_id
           order by posted_at desc
           """;

    List<ChatItemValue> chatItemValueList
            = jdbcTemplate.query(sql, DataClassRowMapper.newInstance(ChatItemValue.
        class));
    return
    chatItemValueList;
    }
}

