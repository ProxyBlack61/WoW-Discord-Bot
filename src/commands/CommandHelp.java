/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;

/**
 *
 * @author STOECKLERKilianLeo
 */
public class CommandHelp implements Command {

    @Override
    public void help(PrivateMessageReceivedEvent event) {
        event.getChannel().sendMessage("Listet alle Befehle auf die es gibt.").queue();
    }

    @Override
    public void command(PrivateMessageReceivedEvent event) {
        event.getChannel().sendMessage("Befehle: \n!hilfe, !token, !affixe, !logs, !score, !info\n"
                + "Mit einem ? nach dem Befehl gibt es für den jeweiligen Befehl eine Hilfe").queue();
    }

}
