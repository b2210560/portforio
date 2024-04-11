package com.example.wsp_spring.model;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
@Repository
public class XreaUrlData {
    JSONObject data;
    public XreaUrlData(){
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
                    this.data=new JSONObject(line);
                    System.out.println(data);
                }
            }
        }catch (Exception e1){
        }
    }
    public String ReturnJson() {
        try {
            JSONObject data2 = data.optJSONObject("result");
            JSONObject data3 = data2.optJSONObject("2");
            String data4 = data3.getString("redirect_url");
            return data4;
        }catch (Exception e1){
            return "ERROR";
        }
    }
}
