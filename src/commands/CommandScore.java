/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import data.QueryData;
import Logger.Logger;
import Logger.Logger.Type;
import data.GeneralData;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import queries.QueryScore;

/**
 *
 * @author STOECKLERKilianLeo
 */
public class CommandScore implements Command {

    public static QueryScore query = new QueryScore(QueryData.getToken());

    @Override
    public void help(PrivateMessageReceivedEvent event) {
        event.getChannel().sendMessage("Gibt die besten"+GeneralData.SCOREPLAYERNUMBER+" M+ Spieler der Gilde aus nach Raider.io score.").queue();
    }

    @Override
    public void command(PrivateMessageReceivedEvent event) {
        try {
            event.getChannel().sendMessage(query.query()).queue();
        } catch (Exception e) {
            Logger.print(Type.ERROR, "Couldn't query Scoreboard");
            event.getChannel().sendMessage("Ops da ist was schief gegangen. Versuchs nochmal").queue();
        }
    }

    
    

}
