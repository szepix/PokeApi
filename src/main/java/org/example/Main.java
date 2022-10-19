package org.example;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public String getEffectiveness(String text)
    {
        HashMap<String, String> multiplierMap = new HashMap<>();
        try {
            multiplierMap.put("half_damage_to", "0.5x");
            multiplierMap.put("double_damage_to", "2.0x");
            multiplierMap.put("no_damage_to", "0.0x");

            if (text.length() == 0)
            {
                System.out.println("Input types in a format:\n fire -> grass poison\n");
                Scanner scan = new Scanner(System.in);
                text = scan.nextLine().toLowerCase();
            }
            String[] types = text.split(" -> ");
            String attackertype = types[0];
            String[] defendertype = types[1].split(" ");

            String host = "http://pokeapi.co/api/v2/type/" + attackertype + '/';

            HttpResponse<JsonNode> response = Unirest.get(host + "?").asJson();
            JSONObject damagerel = response.getBody().getObject().getJSONObject("damage_relations");
            double multiplierSum = 1;
            for(String defender : defendertype) {
                for (String damagemodstr : damagerel.keySet()) {
                    if (damagemodstr.contains("_to")) {
                        JSONArray wholeArray = damagerel.getJSONArray(damagemodstr);
                        for (int i = 0; i <= wholeArray.length() - 1; i++) {
                            JSONObject singleItem = (JSONObject) wholeArray.get(i);
                            if (((String) singleItem.get("name")).equalsIgnoreCase(defender)) {
                                multiplierSum *= Double.parseDouble(multiplierMap.get(damagemodstr).replace("x", ""));
                                break;
                            }
                        }
                    }
                }
            }
            return Double.toString(multiplierSum).replaceAll(".0", "") + "x";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
    public static void main(String[] args) {
        Main run = new Main();
        System.out.println(run.getEffectiveness(""));
    }
}