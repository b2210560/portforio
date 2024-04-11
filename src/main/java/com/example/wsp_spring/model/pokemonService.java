package com.example.wsp_spring.model;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class pokemonService {

    private  final  pokemonItemDAO pokemonItemDAO;
    private UserValue userValue;

    public  pokemonService(pokemonItemDAO pokemonItemDAO){this.pokemonItemDAO=pokemonItemDAO;}
    public void savePokemonData(UserValue userValue,pokemonData pokemonData){
        pokemonItemDAO.insert(userValue,pokemonData);
    }

    public List<pokemonDeleteData> loadPokemonMessage(UserValue userValue) {
        this.userValue = userValue;
        List<pokemonItemValue>pokemonItemValueList = pokemonItemDAO.findAllOrderByTime();
        List<pokemonDeleteData> pokemonDataList=new ArrayList<>();
        for(pokemonItemValue pokemonItemValue:pokemonItemValueList){
            if(pokemonItemValue.userId()==userValue.userId()) {
                pokemonDeleteData pokemonDeleteData = null;
                pokemonDeleteData = new pokemonDeleteData(Integer.parseInt(pokemonItemValue.pokemonNumber()), pokemonItemValue.pokemonName(), pokemonItemValue.pokemonImage(), pokemonItemValue.posted_at());
                pokemonDataList.add(pokemonDeleteData);
            }
        }
        return pokemonDataList;
    }
    public void DeleteData(String delete_value){
        pokemonItemDAO.deletePokemonData(delete_value);
    }
}
