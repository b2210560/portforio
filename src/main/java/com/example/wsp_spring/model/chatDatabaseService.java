package com.example.wsp_spring.model;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class chatDatabaseService {
    private final ChatGroupDAO chatGroupDAO;
    public  chatDatabaseService(ChatGroupDAO chatGroupDAO){
        this.chatGroupDAO=chatGroupDAO;
    }


    public List<ChatGroup> loadChatList(){
        return chatGroupDAO.groupList();
    }
}
