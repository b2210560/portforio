package com.example.wsp_spring;

import com.example.wsp_spring.model.SignService;
import com.example.wsp_spring.model.UserValue;
import com.example.wsp_spring.model.XreaUrlData;
import org.json.JSONObject;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class XREAapiController {
    private  final SignService signService;
    private  final XreaUrlData xreaUrlData;

    private  XREAapiController(SignService signService,XreaUrlData xreaUrlData){
        this.signService=signService;
        this.xreaUrlData=xreaUrlData;
    }

    @GetMapping("/XREAapi")
    public String getXREA(Model model){
        if(signService.isSigned()){
            UserValue signedUser=signService.getSignedUser();
            model.addAttribute("userValue",signedUser);
            String data=xreaUrlData.ReturnJson();
            model.addAttribute("url",data);
            return "XREAapi";
        }
        return "sign_in_form";
    }
}
