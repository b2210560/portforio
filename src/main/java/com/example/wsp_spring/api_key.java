package com.example.wsp_spring;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.lang.*;
import java.io.*;
import java.net.*;

import java.util.HashMap;
import java.util.Map;


import javax.net.ssl.HttpsURLConnection;

public class api_key {
    public static void main(String[] args) {
        try {


            URL url = new URL("https://api.xrea.com/v1/site/list");
            String postData = "account=b2210560okichi&server_name=s324.xrea.com&api_secret_key=eKjBeY8LwHUmsIPVGGASIGQP3iYCCoHx";

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", Integer.toString(postData.length()));

            try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
                dos.writeBytes(postData);
            }

            try (BufferedReader bf = new BufferedReader(new InputStreamReader(
                    conn.getInputStream())))
            {
                String line;
                while ((line = bf.readLine()) != null) {
                    JSONObject data=new JSONObject(line);
                    System.out.println(data);
                    JSONObject data2=data.optJSONObject("result");
                    System.out.println(data2);
                    JSONObject data3=data2.optJSONObject("2");
                    System.out.println(data3);
                    String data4=data3.getString("redirect_url");
                    System.out.println(data4);

                }
            }
        }catch (Exception e1){
            System.out.println("ERROR1");
        }
    }
}
