package com.example.wsp_spring;
import com.example.wsp_spring.model.SignService;
import com.example.wsp_spring.model.UserValue;
import com.example.wsp_spring.model.mailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class mailController {
    private final mailService mailService;
    private final SignService signService;

    public mailController(mailService mailService, SignService signService){
        this.mailService=mailService;
        this.signService=signService;
    }


    @GetMapping("/mail")
    public String getMail(Model model){
        if (signService.isSigned()){
            UserValue signedUser=signService.getSignedUser();
            model.addAttribute("userValue",signedUser);
            model.addAttribute("textMessage","メールを入力してください");
            return "mail";
        }
        else {
            return "sign_in_form";
        }
    }

    @PostMapping("mail")
    public String postMail(Model model,String mainText, String titleText){
        if(signService.isSigned()){
            UserValue signedUser=signService.getSignedUser();
            model.addAttribute("userValue",signedUser);
            mailService.mailSend(mainText,titleText);
            model.addAttribute("textMessage","送信しました。");
            return "mail";
        }else {
            return "sign_in_form";
        }
    }

}
