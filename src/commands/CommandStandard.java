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
public class CommandStandard implements Command {

    @Override
    public void help(PrivateMessageReceivedEvent event) {

    }

    @Override
    public void command(PrivateMessageReceivedEvent event) {
        event.getChannel().sendMessage("Kein Befehl gefunden! Versuch mal !hilfe").queue();
    }

}
