/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import Logger.Logger;
import data.QueryData;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import queries.QueryWoWToken;

/**
 *
 * @author STOECKLERKilianLeo
 */
public class CommandToken implements Command {

    private QueryWoWToken query = new QueryWoWToken(QueryData.getToken());

    @Override
    public void help(PrivateMessageReceivedEvent event) {
        event.getChannel().sendMessage("Gibt dir den Aktuellen WoW Token Preis für EU aus.").queue();
    }

    @Override
    public void command(PrivateMessageReceivedEvent event) {
        try {
            event.getChannel().sendMessage("Der Aktuelle Preis beträgt: " + query.query() + " Gold").queue();
        } catch (Exception ex) {
            Logger.print(Logger.Type.ERROR, "Could not query WoW token price");
            event.getChannel().sendMessage("Ein Fehler ist aufgetreten! Versuche es nochmal.").queue();
        }
    }

}
