package com.example.wsp_spring.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.wsp_spring.model.UserIDValidator;
import com.example.wsp_spring.model.UserPasswordValidator;

@Service
public class SignService {

    private  final  AuthnDataDAO authnDataDAO;
    private final StateManager stateManager;
    @Autowired
    public SignService(AuthnDataDAO authnDataDAO,StateManager stateManager){

        this.authnDataDAO=authnDataDAO;
        this.stateManager=stateManager;
    }


    //ユーザーIDとパスワードで認証を行う
    public  UserValue sign(String userId,String userPassword){
        //userIdが自分の学生番号、パスワード　qwertの時だけ、
        //userをフィールドに持つUser Valueインスタンスを返す

        //各ルールをチェックする
        //(いわゆるSPECIFICATIONパターンと呼ばれるモノを授業ようにわかりやすさ重視で実装)
        try{
            AuthnValue authnValue=authnDataDAO.find(userId);
            if(userPassword.equals(authnValue.userPassword())){
                UserValue userValue=  new UserValue(userId,authnValue.userName());
                //照合が成功したときにSessionにUserValueインスタンスを一時記録する
                stateManager.signIn(userValue);
                return userValue;
            }
        }catch (EmptyResultDataAccessException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    public  boolean isSigned(){
        return  stateManager.hasSignedUser();
    }

    public UserValue getSignedUser(){
        return  stateManager.getSignedUser();
    }

    public void signOut(){
        stateManager.signOut();
    }
}
