package com.example.wsp_spring;

import com.example.wsp_spring.model.SignService;
import com.example.wsp_spring.model.UserValue;
import com.example.wsp_spring.model.pokemonDeleteData;
import com.example.wsp_spring.model.pokemonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class pokemon_history_Controller {
    private final SignService signService;
    private final pokemonService pokemonService;

    public  pokemon_history_Controller(SignService signService,pokemonService pokemonService){
        this.signService=signService;
        this.pokemonService=pokemonService;
    }

    @GetMapping("/pokemon_history")
    public String GetPokemonHistory(Model model){
        if (signService.isSigned()){
            UserValue signedUser=signService.getSignedUser();
            model.addAttribute("userValue",signedUser);
            List<pokemonDeleteData> pokemonList=pokemonService.loadPokemonMessage(signedUser);
            model.addAttribute("pokemonList",pokemonList);
            return "pokemon_history";
        }
        return "sign_in_form";
    }

    @PostMapping("pokemonDelete")
    public String PostPokemonHistory(String delete_value,Model model){
        if (signService.isSigned()){
            pokemonService.DeleteData(delete_value);
            UserValue signedUser=signService.getSignedUser();
            model.addAttribute("userValue",signedUser);
            List<pokemonDeleteData> pokemonList=pokemonService.loadPokemonMessage(signedUser);
            model.addAttribute("pokemonList",pokemonList);
            return "pokemon_history";
        }
        return "sign_in_form";
    }
}
