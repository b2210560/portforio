package com.example.wsp_spring;

import com.example.wsp_spring.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class chatDatabaseController {
    private final SignService signService;
    private final chatDatabaseService chatDatabaseService;
    private final ChatService chatService;

    private final groupCreateService groupCreateService;

    private chatDatabaseController(SignService signService,chatDatabaseService chatDatabaseService,ChatService chatService
                                   ,groupCreateService groupCreateService){
        this.signService=signService;
        this.chatDatabaseService=chatDatabaseService;
        this.chatService=chatService;
        this.groupCreateService=groupCreateService;
    }

    @GetMapping("/chatDatabase")
    public  String getChatDatabase(Model model){
        if(signService.isSigned()) {
            UserValue signedUser = signService.getSignedUser();
            model.addAttribute("userValue", signedUser);
            List<ChatGroup> chatgroupList = chatDatabaseService.loadChatList();
            model.addAttribute("chatGroupList",chatgroupList);
            return "chatDatabase";
        }
        return  "sign_in_form";
    }

    @PostMapping("chatDatabase")
    public String getChat(String chatGroup,Model model){
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
                }
            }
            model.addAttribute("chatMessageList", chatMessageList);
            return  "chat";
        }
        return "sign_in_form";
    }

    @PostMapping("newGroup")
    public String createChat(String newGroup,Model model){
        if(signService.isSigned()) {
            UserValue signedUser = signService.getSignedUser();
            if (groupCreateService.CheckGroup(newGroup)) {
                groupCreateService.createGroup(newGroup);
                model.addAttribute("chatGroup", newGroup);
                model.addAttribute("userValue", signedUser);
                List<ChatMessage> AllchatMessageList = chatService.loadChatMessage(signedUser, newGroup);
                List<ChatMessage> chatMessageList = new ArrayList<>();
                for (int i = 0; i < AllchatMessageList.size(); i++) {
                    ChatMessage data = AllchatMessageList.get(i);
                    if (newGroup.equals(data.chatGroup())) {
                        chatMessageList.add(data);
                    } else {}
                }
                model.addAttribute("chatMessageList", chatMessageList);
                return "chat";
            }
            model.addAttribute("userValue", signedUser);
            List<ChatGroup> chatgroupList = chatDatabaseService.loadChatList();
            model.addAttribute("chatGroupList",chatgroupList);
            model.addAttribute("chatFalse","失敗しました");
            return "chatDatabase";
        }
        return "sign_in_form";

    }

}
