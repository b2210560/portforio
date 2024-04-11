package com.example.wsp_spring;

import com.example.wsp_spring.model.UserValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.wsp_spring.model.SignService;

@Controller
public class SignController {

    private  SignService signService;

    public SignController(SignService signService){
        this.signService=signService;
    }

    @GetMapping("/signIn")
    public String getSignIn(Model model){
        //ユーザーが認証済みであれば、認証時の情報を用いて表示
        if(signService.isSigned()){
            UserValue signedUser=signService.getSignedUser();
            model.addAttribute("userValue",signedUser);
            return "signed";
        }
        return "sign_in_form";
    }

    @PostMapping("/signIn")
    public String postSignIn(String userId, String userPassword, Model model){
        UserValue userValue =signService.sign(userId,userPassword);

        //認証されたユーザーの情報を表示する
        model.addAttribute("userValue",userValue);
        return  "signed";
    }

    @GetMapping("signOut")
    public  String getSignOut(){
        signService.signOut();
        return "sign_in_form";
    }
}
