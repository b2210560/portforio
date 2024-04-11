package com.example.wsp_spring.model;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public class StateManager {
    //サーバーのSession領域
    private final HttpSession session;

    private static final String SIGNED_USER_KEY ="signedUser";

    public StateManager(HttpSession session){
        this.session=session;
    }

    //Sessionに、SessionIDに紐づいたユーザー情報を一時記録する
    public void signIn(UserValue userValue){
        session.setAttribute(SIGNED_USER_KEY,userValue);
    }
    //Sessionに、SessionIDに紐づいたユーザー情報を一時記録する
    public boolean hasSignedUser(){
        return  session.getAttribute(SIGNED_USER_KEY)!=null;
    }
    //Sessionに、SessionIDと紐付いて一時記録されているユーザー情報を参照する
    public  UserValue getSignedUser(){
        if(hasSignedUser()){
            return (UserValue) session.getAttribute(SIGNED_USER_KEY);
        }
        throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }

    //Sessionから、SessionIDと紐づいたユーザー情報を破棄する
    public  void signOut(){
        session.invalidate();
    }
}
