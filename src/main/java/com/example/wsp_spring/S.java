package com.example.wsp_spring;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class S {
    private static String FORMAT_URL="https://pokeapi.co/api/v2/pokemon/";
    private static String URL_CODE=FORMAT_URL+5;

    public static void main(String[] args) {
        String a=GET_pokemon();
        System.out.println(a);
    }

    public static String GET_pokemon(){
        String result = "";
        JsonNode root = null;
        try {
            URL url = new URL(URL_CODE); // リクエストパラメータ含めたAPIのURLを指定
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String tmp = "";

            while ((tmp = in.readLine()) != null) {
                result += tmp;
            }
            return  result;
        }catch(Exception e) {
            return "none";
        }

    }
}
