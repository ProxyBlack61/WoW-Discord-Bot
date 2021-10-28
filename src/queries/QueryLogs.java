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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

/**
 *
 * @author STOECKLERKilianLeo
 */
public class QueryLogs implements Query {

    private BufferedReader br;

    @Override
    public String query() throws Exception {
        Calendar cal_1 = Calendar.getInstance();
        Calendar cal_2 = Calendar.getInstance();
        URL url = new URL("https://www.warcraftlogs.com/v1/reports/guild/Booncrew/aegwynn/eu?start=1523397600000&api_key=--OMITTED--");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", GeneralData.USERAGENT);
        br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
        StringBuilder sb = new StringBuilder();
        String in;
        while ((in = br.readLine()) != null) {
            sb.append(in);
        }
        JsonArray logs = (JsonArray) new JsonParser().parse(sb.toString()).getAsJsonArray();
        JsonObject obj = logs.get(0).getAsJsonObject();
        for (JsonElement log : logs) {
            cal_1.setTimeInMillis(log.getAsJsonObject().getAsJsonPrimitive("start").getAsInt());
            cal_2.setTimeInMillis(obj.getAsJsonPrimitive("start").getAsInt());
            if (cal_1.after(cal_2)) {
                obj = log.getAsJsonObject();
            }
        }
        return "https://www.warcraftlogs.com/reports/" + obj.getAsJsonPrimitive("id").getAsString();
    }

}
