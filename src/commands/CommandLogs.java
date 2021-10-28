/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import Logger.Logger;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import queries.QueryLogs;

/**
 *
 * @author STOECKLERKilianLeo
 */
public class CommandLogs implements Command {

    private QueryLogs query = new QueryLogs();

    @Override
    public void help(PrivateMessageReceivedEvent event) {
        event.getChannel().sendMessage("Gibt den Link des neuesten Raidlog der Gilde aus.").queue();
    }

    @Override
    public void command(PrivateMessageReceivedEvent event) {
        try {
            event.getChannel().sendMessage("Der neueste Log:\n" + query.query()).queue();
        } catch (Exception e) {
            Logger.print(Logger.Type.ERROR, "Could not query raidlog");
            event.getChannel().sendMessage("Keine Logs gefunden.").queue();
        }
    }

}
