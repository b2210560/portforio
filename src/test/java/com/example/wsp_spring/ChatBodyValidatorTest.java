package com.example.wsp_spring;

import com.example.wsp_spring.model.ChatBodyValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChatBodyValidatorTest {
     @Test
    @DisplayName("1mozi")
    public  void test01(){
        String arg=makeArgs(1);
        ChatBodyValidator sut =new ChatBodyValidator();
        sut.isSatisfiedBy(arg);
    }
    @Test
    @DisplayName("140mozi")
    public  void test02(){
        String arg=makeArgs(140);
        ChatBodyValidator sut =new ChatBodyValidator();
        sut.isSatisfiedBy(arg);
    }

    @Test
    @DisplayName("0mozi")
    public  void test03(){
        String arg=makeArgs(0);
        ChatBodyValidator sut =new ChatBodyValidator();
        assertThrows(ResponseStatusException.class,() ->sut.isSatisfiedBy(arg));
    }

    @Test
    @DisplayName("141mozi")
    public  void test04(){
        String arg=makeArgs(141);
        ChatBodyValidator sut =new ChatBodyValidator();
        assertThrows(ResponseStatusException.class,() ->sut.isSatisfiedBy(arg));
    }

    private String makeArgs(int n){
        String arg= "";
        for(int i=1;i<=n;i++){
            arg=arg+"あ";
        }
        return  arg;
    }
}
