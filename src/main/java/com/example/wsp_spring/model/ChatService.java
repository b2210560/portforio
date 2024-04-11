package com.example.wsp_spring.model;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ChatService {
    private final ChatItemDAO  chatItemDAO;

    public ChatService(ChatItemDAO chatItemDAO){
        this.chatItemDAO=chatItemDAO;
    }

    public void saveChatBody(UserValue userValue,String chatBody,String chatGroup){
        ChatBodyValidator chatBodyValidator=new ChatBodyValidator();
        chatBodyValidator.isSatisfiedBy(chatBody);
        //メッセいーじ用の発言を記録する
        chatItemDAO.insert(userValue,chatBody,chatGroup);
    }

    public List<ChatMessage> loadChatMessage(UserValue userValue,String chaGroup){
        //データベースから取り出したメッセ―ジデータを、表示用データに入れ替える
        List<ChatItemValue> chatItemValueList=chatItemDAO.findALLOrderByTime();
        List<ChatMessage> chatMessageList=new ArrayList<>();
        for(ChatItemValue chatItemValue:chatItemValueList){
            ChatMessage chatMessage =null;
            if(sameUserId(userValue.userId(),chatItemValue.userId())){
            chatMessage=new ChatMessage("あなた",chatItemValue.chatBody(),chatItemValue.chat_group());
            }else {
                chatMessage = new ChatMessage(chatItemValue.userName(), chatItemValue.chatBody(),chatItemValue.chat_group());
            }
            chatMessageList.add(chatMessage);
            }
        return chatMessageList;
        }

        //ユーザーIDを比較して一緒であればtrueを返す
    private boolean sameUserId(String userValueUserId,String chatItemUserId){
        return userValueUserId.equals(chatItemUserId);
    }
}
