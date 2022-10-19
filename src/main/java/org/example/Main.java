package org.example;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        HashMap<String, String> multiplierMap = new HashMap<String, String>();
        try {
            multiplierMap.put("half_damage_to", "0.5x");
            multiplierMap.put("double_damage_to", "2.0x");
            multiplierMap.put("no_to", "0.0x");
            System.out.println("Input types in a format:\n fire -> grass\n");
            Scanner scan = new Scanner(System.in);
            String test = scan.nextLine().toLowerCase();
            String[] types = test.split(" -> ");
            String attackertype = types[0];
            String defendertype = types[1];

            String host = "http://pokeapi.co/api/v2/type/" + attackertype + '/';

            HttpResponse<JsonNode> response = Unirest.get(host + "?").asJson();
            JSONObject damagerel = response.getBody().getObject().getJSONObject("damage_relations");
            String multiplier = "1x";
            for (Object damagemodstr : damagerel.keySet()) {
                if ((((String) damagemodstr)).contains("_to")) {
                    JSONArray ja = damagerel.getJSONArray((String) damagemodstr);
                    for (int i = 0; i <= ja.length() - 1; i++) {
                        JSONObject jo = (JSONObject) ja.get(i);
                        if (((String) jo.get("name")).equalsIgnoreCase(defendertype)) {
                            multiplier = multiplierMap.get(damagemodstr);
                            break;
                        }
                    }
                }

            }
            System.out.println(multiplier);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}