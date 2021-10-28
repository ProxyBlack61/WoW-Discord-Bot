/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import Logger.Logger;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import queries.QueryAffixe;

/**
 *
 * @author STOECKLERKilianLeo
 */
public class CommandAffixe implements Command {

    private QueryAffixe query = new QueryAffixe();

    @Override
    public void help(PrivateMessageReceivedEvent event) {
        event.getChannel().sendMessage("Gibt die derzeitigen Affixe aus.").queue();
    }

    @Override
    public void command(PrivateMessageReceivedEvent event) {
        try {
            event.getChannel().sendMessage("Die Affixe diese Woche sind:\n" + query.query()).queue();
        } catch (Exception e) {
            Logger.print(Logger.Type.ERROR, "Couldn't query weekly affixe"+e.getMessage());
            event.getChannel().sendMessage("Ops das ist was schiefgelaufen. Probiers nochmal.").queue();
        }
    }

}
