package com.example.wsp_spring.model;

import java.time.LocalDateTime;

public record ChatItemValue(String userId, String userName, String chatBody, LocalDateTime postedAt,String chat_group) {
}
