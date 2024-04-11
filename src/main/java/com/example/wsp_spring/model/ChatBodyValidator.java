package com.example.wsp_spring.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Pattern;

//メッセージ入力のルール
public class ChatBodyValidator implements  Validator<String>{
    private final Pattern pattern=Pattern.compile("(\\d|\\D){1,140}");
    //発言が最小文字数よりも小さく、最大文字数よりも大きい場合は例外を表示する
    @Override
    public  boolean isSatisfiedBy(String chatBody){
        //条件に合わない場合は400 BAD_REQEST を表示する

        if (!pattern.matcher(chatBody).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return true;
    }
}
