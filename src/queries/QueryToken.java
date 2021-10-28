/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queries;

import Logger.Logger;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 *
 * @author STOECKLERKilianLeo
 */
public class QueryToken implements Query {

    @Override
    public String query() {
        String token = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new URL("https://eu.battle.net/oauth/token?client_id=--OMITTED--&client_secret=--OMITTED--&grant_type=client_credentials").openStream(),"UTF-8"));
            token = (String) new JsonParser().parse(br.readLine()).getAsJsonObject().getAsJsonPrimitive("access_token").getAsString();
            Logger.print(Logger.Type.SUCCESS, String.format("Token generated: %s", token));
        } catch (Exception e) {
            Logger.print(Logger.Type.ERROR, "Couldn't generate token");
        }
        return token;
    }

}
