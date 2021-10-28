/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queries;

import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 *
 * @author STOECKLERKilianLeo
 */
public class QueryWoWToken implements Query {

    private String token;
    private BufferedReader br;
    public QueryWoWToken(String token) {
        this.token = token;
    }
    
    @Override
    public String query() throws Exception {
        br = new BufferedReader(new InputStreamReader(new URL("https://eu.api.blizzard.com/data/wow/token/?namespace=dynamic-eu&locale=de_EU&access_token=" + token).openStream(),"UTF-8"));
        String price = String.format("%d", new JsonParser().parse(br.readLine()).getAsJsonObject().getAsJsonPrimitive("price").getAsInt());
        return price.substring(0, price.length() - 4);    
    }

}
