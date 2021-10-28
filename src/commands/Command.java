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
public interface Command {
        
    //Methode zur Ausgabe der Hilfe des jeweiligen Befehls
    public void help(PrivateMessageReceivedEvent event);
    //Jeweiliges event des Befehls
    public void command(PrivateMessageReceivedEvent event);
    
}
