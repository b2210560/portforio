package com.example.wsp_spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Demo {
    @GetMapping("/demo")
    @ResponseBody
    public String doGet(){
        String body= """
                <form action ="demo" method="POST">
                    <input type="text" name="yen"/>円
                    <button type="submit">両替</button>
                </form>
                """;
        return  body;
    }

    @PostMapping("/demo")
    @ResponseBody
    public String doPost(int yen){
        //データ処理：円をアルゼンチンペソに交換
        float ars = yen * 0.87f;
        //処理結果のリソース作成
        String body =String.format("<p> %d 円は %.2f アルゼンチンペソ<p>",yen,ars);
        return body;
    }


}
