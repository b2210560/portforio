package com.example.wsp_spring.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Pattern;

/**
 * ユーザーパスワードのチェックルールを管理するクラス
 */
public class UserPasswordValidator implements Validator<String>{
    private  final Pattern pattern=Pattern.compile(".[5,11]");
    public boolean isSatisfiedBy(String userId){
        if(!pattern.matcher(userId).matches()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return true;
    }
}
