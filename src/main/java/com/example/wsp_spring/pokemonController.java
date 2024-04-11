package com.example.wsp_spring;

import com.example.wsp_spring.model.SignService;
import com.example.wsp_spring.model.UserValue;
import com.example.wsp_spring.model.pokemonService;
import org.springframework.stereotype.Controller;
import com.example.wsp_spring.model.pokemonApi;
import com.example.wsp_spring.model.pokemonData;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class pokemonController {

    private final SignService signService;

    private final pokemonService pokemonService;

    public pokemonController(SignService signService,pokemonService pokemonService){
        this.signService=signService;
        this.pokemonService=pokemonService;
    }

    @GetMapping("/pokemon")
    public String getPokemon(Model model){
        if (signService.isSigned()){
            UserValue signedUser=signService.getSignedUser();
            model.addAttribute("userValue",signedUser);
            pokemonData pokemonData=new pokemonData(0,"NonName","/image/ERROR.jpg");
            model.addAttribute("pokemonData",pokemonData);
            return "pokemon";
        }
        return "sign_in_form";
    }

    @PostMapping("pokemon")
    public String postPokemon(String p_number,Model model){
        if (signService.isSigned()){
            UserValue signedUser=signService.getSignedUser();
            model.addAttribute("userValue",signedUser);
            pokemonApi pokemonApi=new pokemonApi(p_number);
            model.addAttribute("pokemonData",pokemonApi.GET_pokemon());
            return "pokemon";
        }
        return "sign_in_form";
    }

    @PostMapping("pokemonKeep")
    public String postPokemonKeep(String data,Model model){
        if (signService.isSigned()){
            UserValue signedUser=signService.getSignedUser();
            model.addAttribute("userValue",signedUser);
            pokemonApi pokemonApi=new pokemonApi(data);
            model.addAttribute("pokemonData",pokemonApi.GET_pokemon());
            pokemonService.savePokemonData(signedUser,pokemonApi.GET_pokemon());
            model.addAttribute("UpData","保存しました");
            return "pokemon";
        }
        return "sign_in_form";
    }
}
