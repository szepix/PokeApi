package org.example;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Scanner;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);

            String attackertype = "fire";
            String defendertype = "ice";

            String host = "http://pokeapi.co/api/v2/type/";
            String charset = "UTF-8";


            HttpResponse<JsonNode> response = Unirest.get(host + "?").asJson();
            JSONObject damagerel = response.getBody().getObject();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}