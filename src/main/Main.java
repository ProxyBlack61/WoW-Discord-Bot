/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Logger.Logger;
import Logger.Logger.Type;
import commands.CommandScore;
import data.GeneralData;
import data.QueryData;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import queries.QueryToken;

/**
 *
 * @author kilia
 */
public class Main {

    public static void main(String[] args) throws LoginException {
        QueryData data = new QueryData();
        QueryToken token = new QueryToken();
        data.setToken(token.query());
        LocalDateTime time = LocalDateTime.now();
        try {
            CommandScore.query.updateList();
            Logger.print(Type.SUCCESS, "Scorelist loaded");
        } catch (Exception e) {
            Logger.print(Type.ERROR, "Couldn't load Scorelist");
        }
        JDA jda = new JDABuilder(GeneralData.BOTTOKEN).build();
        jda.addEventListener(new Listener());
        while (true) {
            if (time.plusHours(6).isBefore(LocalDateTime.now())) {
                try {
                    CommandScore.query.updateList();
                    time = time.plusHours(6);
                    Logger.print(Type.SUCCESS, "Updated Scorelist");
                } catch (Exception e) {
                    Logger.print(Type.ERROR, "Couldn't update Scorelist");
                }
            } else {
                try {
                    TimeUnit.HOURS.sleep(12);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
