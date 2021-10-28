/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queries;

import Logger.Logger;
import Logger.Logger.Type;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import data.GeneralData;
import data.Member;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author STOECKLERKilianLeo
 */
public class QueryScore implements Query {

    private BufferedReader br;
    private String token;
    private ArrayList<Member> membersList = new ArrayList<>();

    public QueryScore(String token) {
        this.token = token;
    }

    @Override
    public String query() throws Exception {
        return this.buildOutput(membersList);
    }

    public void updateList() throws Exception {
        ArrayList<Member> list = this.getGuildList();
        list = this.setScore(list);
        membersList = list;
    }

    public ArrayList<Member> getGuildList() throws Exception {
        br = new BufferedReader(new InputStreamReader(new URL("https://eu.api.blizzard.com/wow/guild/aegwynn/Booncrew?fields=members&locale=de_EU&access_token=" + token).openStream(), "UTF-8"));
        JsonArray members = new JsonParser().parse(br.readLine()).getAsJsonObject().getAsJsonArray("members");
        ArrayList<Member> memberslist = new ArrayList<>();
        for (JsonElement member : members) {
            JsonObject character = member.getAsJsonObject().getAsJsonObject("character");
            if (character.getAsJsonPrimitive("level").getAsInt() == 120) {
                memberslist.add(new Member(character.getAsJsonPrimitive("name").getAsString(), character.getAsJsonPrimitive("class").getAsInt()));
            }
        }
        return memberslist;
    }

    public ArrayList<Member> setScore(ArrayList<Member> members) throws Exception {
        URL url;
        HttpURLConnection con;
        JsonObject score;
        ArrayList<Member> output = new ArrayList<>();
        Member standard = new Member(token, 0);
        standard.setScore(0);
        for (int i = 0; i < 10; i++) {
            output.add(standard);
        }
        for (Member member : members) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                url = new URL("https://raider.io/api/v1/characters/profile?region=eu&realm=aegwynn&name=" + URLEncoder.encode(member.getName(), "UTF-8") + "&fields=mythic_plus_scores");
                con = (HttpURLConnection) url.openConnection();
                con.setRequestProperty("User-Agent", GeneralData.USERAGENT);
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                score = new JsonParser().parse(br.readLine()).getAsJsonObject().getAsJsonObject("mythic_plus_scores");
                double mscore = score.getAsJsonPrimitive("all").getAsDouble();
                for (int i = 0; i < 10; i++) {
                    if (output.get(i).getScore() < mscore) {
                        member.setScore(mscore);
                        output.add(i, member);
                        break;
                    }
                }
            } catch (IOException e) {
                Logger.print(Type.ERROR, "Couldn't load member " + e.getMessage());
            }

        }
        members.clear();
        if (output.size() != 10) {
            for (int i = 0; i < GeneralData.SCOREPLAYERNUMBER; i++) {
                members.add(output.get(i));
            }
        }
        return members;
    }

    public String buildOutput(ArrayList<Member> members) {
        StringBuilder sb = new StringBuilder("Die   " + GeneralData.SCOREPLAYERNUMBER + " höchsten Spieler aus der Gilde:\n");
        for (int i = 0; i < GeneralData.SCOREPLAYERNUMBER; i++) {
            Member m = members.get(i);
            sb.append(String.format("%d. %s als %s mit einem Score von %.2f\n", i + 1, m.getName(), m.getSpec(), m.getScore()));
        }
        return sb.toString();
    }

}
