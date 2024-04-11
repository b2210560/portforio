package com.example.wsp_spring.model;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class groupCreateDAO {
    private final JdbcTemplate jdbcTemplate;
    public groupCreateDAO(JdbcTemplate jdbcTemplate){this.jdbcTemplate=jdbcTemplate;}

    public void insert(String newGroup){
        String sql="insert into chat_item values(?,?,?,?)";
        String userId="b2210560";
        LocalDateTime timeStamp=LocalDateTime.now();
        String startChat="チャットグループを開きました楽しみましょう";
        jdbcTemplate.update(sql,userId,timeStamp,startChat,newGroup);
    }

    public boolean query(String newGroup){
        String sql = """
           select chat_item.user_id, user_name, chat_body, posted_at,chat_group
           from chat_item left join authn_data
           on chat_item.user_id =authn_data.user_id
           order by posted_at desc
           """;
        List<ChatItemValue> chatItemValueList
                = jdbcTemplate.query(sql, DataClassRowMapper.newInstance(ChatItemValue.
                class));
        for (int i=0;i<chatItemValueList.size();i++){
            ChatItemValue chatItemValue=chatItemValueList.get(i);
            if(newGroup.equals(chatItemValue.chat_group())){
                return false;
            }
        }
        return true;
    }
}
