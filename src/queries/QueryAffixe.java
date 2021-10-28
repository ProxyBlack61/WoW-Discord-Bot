/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queries;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import data.GeneralData;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author STOECKLERKilianLeo
 */
public class QueryAffixe implements Query {

    private BufferedReader br;

    @Override
    public String query() throws Exception {
        String out = "";
        URL url = new URL("https://raider.io/api/v1/mythic-plus/affixes?region=eu&locale=de");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", GeneralData.USERAGENT);
        br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
        JsonArray affixe = (JsonArray) new JsonParser().parse(br.readLine()).getAsJsonObject().getAsJsonArray("affix_details");
        JsonObject jo;
        for (JsonElement jsonElement : affixe) {
            jo = jsonElement.getAsJsonObject();
            out += String.format("%s\n-->%s\n", jo.getAsJsonPrimitive("name").getAsString(), jo.getAsJsonPrimitive("description").getAsString());
        }
        return out;
    }

}
