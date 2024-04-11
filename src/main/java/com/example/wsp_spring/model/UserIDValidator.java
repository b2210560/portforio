package com.example.wsp_spring.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Pattern;

/**
 * ユーザIDのチェックルールを管理するクラス
 */
public class UserIDValidator implements  Validator<String>{
    private final Pattern pattern=Pattern.compile("^b\\d{6}0$");
    //入力されたユーザーIDがルールと位置しない場合は例外を表示する
    @Override
    public boolean isSatisfiedBy(String userId){
        if(!pattern.matcher(userId).matches()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return true;
    }
}
