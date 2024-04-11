package com.example.wsp_spring;

import com.example.wsp_spring.model.ChatMessage;
import com.example.wsp_spring.model.ChatService;
import com.example.wsp_spring.model.SignService;
import com.example.wsp_spring.model.UserValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {
    private final SignService signService;
    private final ChatService chatService;

    private ChatController(SignService signService,ChatService chatService){
        this.signService=signService;
        this.chatService=chatService;
    }

    @GetMapping("/chat")
    public String getChat(Model model,String chatGroup){
        if(signService.isSigned()){
            UserValue signedUser=signService.getSignedUser();
            model.addAttribute("userValue", signedUser);
            model.addAttribute("chatGroup",chatGroup);
            List<ChatMessage> AllchatMessageList = chatService.loadChatMessage(signedUser,chatGroup);
            List<ChatMessage> chatMessageList=new ArrayList<>();
            for (int i = 0; i < AllchatMessageList.size(); i++) {
                ChatMessage data=AllchatMessageList.get(i);
                if(chatGroup.equals(data.chatGroup())){
                    chatMessageList.add(data);
                }else {}
            }
            model.addAttribute("chatMessageList", chatMessageList);
            return  "chat";
        }
        return "sign_in_form";
    }

    @PostMapping("chat")
    public  String postChat(String chatGroup,String chatBody,Model model){
        if(signService.isSigned()) {
            //サインインしているユーザーの情報を私、メッセ―ジデータを記録する
            UserValue signedUser = signService.getSignedUser();
            chatService.saveChatBody(signedUser, chatBody,chatGroup);
            model.addAttribute("userValue", signedUser);
            model.addAttribute("chatGroup",chatGroup);
            //サインインしているユーザーの情報を渡し、メッセージデータを取得する
            List<ChatMessage> AllchatMessageList = chatService.loadChatMessage(signedUser,chatGroup);
            List<ChatMessage> chatMessageList=new ArrayList<>();
            for (int i = 0; i < AllchatMessageList.size(); i++) {
                ChatMessage data=AllchatMessageList.get(i);
                if(chatGroup.equals(data.chatGroup())){
                    chatMessageList.add(data);
                }else {}
            }
            model.addAttribute("chatMessageList", chatMessageList);
            return  "chat";
        }
        return  "sign_in_form";
    }
}
