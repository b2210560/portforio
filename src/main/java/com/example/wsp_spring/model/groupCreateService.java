package com.example.wsp_spring.model;

import org.springframework.stereotype.Service;

@Service
public class groupCreateService {

    private final groupCreateDAO groupCreateDAO;
    public groupCreateService(groupCreateDAO groupCreateDAO){this.groupCreateDAO=groupCreateDAO;}

    public void createGroup(String groupName){
        groupCreateDAO.insert(groupName);
    }

    public boolean CheckGroup(String newGroup) {
        if (groupCreateDAO.query(newGroup)) {
            groupBodyValidator groupBodyValidator = new groupBodyValidator();
            return groupBodyValidator.isSatisfiedBy(newGroup);
        }
        return false;
    }
}
