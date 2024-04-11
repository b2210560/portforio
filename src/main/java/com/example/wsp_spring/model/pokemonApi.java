package com.example.wsp_spring.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;


public class pokemonApi {
    private String FORMAT_URL="https://pokeapi.co/api/v2/pokemon/";
    private String URL_CODE;

    private JSONObject data;
    public pokemonApi(String p_number){
        URL_CODE=FORMAT_URL+p_number;
    }

    public pokemonData GET_pokemon(){
        String result = "";
        try {
            URL url = new URL(URL_CODE); // リクエストパラメータ含めたAPIのURLを指定
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String tmp = "";

            while ((tmp = in.readLine()) != null) {
                result += tmp;
            }
            data=new JSONObject(result);
            int number=data.getInt("id");
            String name=data.getString("name");
            JSONObject sprites=data.getJSONObject("sprites");
            String front_default=sprites.getString("front_default");
            pokemonData pokemonData=new pokemonData(number,name,front_default);
            return  pokemonData;
        }catch(Exception e) {
            pokemonData pokemonData=new pokemonData(0,"NonName", "/image/ERROR.jpg");
            return pokemonData;
        }

    }
}
