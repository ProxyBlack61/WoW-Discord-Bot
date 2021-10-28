package commands;

import data.GeneralData;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;

/**
 * @author Kilian Stöckler
 */
public class CommandInfo implements Command {

    private MessageBuilder info = new MessageBuilder("Alle Infos zum Bot:\nDieser Bot wurde von ProxyBlack aka Bløødfist geschrieben.");

    {
        info.append("Er wurde für die Gilde Booncrew auf Aegwynn geschrieben.\n\n");
        info.append("Tester:\n", MessageBuilder.Formatting.BOLD);
        info.append("Tímmus\n\n");
        info.append("Credits:\n", MessageBuilder.Formatting.BOLD);
        info.append("Blizzard für das benutzen der API https://www.blizzard.com \n"
                + "Raider.io für das benutzen der API https://raider.io \n"
                + "Warcraftlogs für das benutzen der API https://www.warcraftlogs.com/ \n\n");
        info.append("Sollten Fehler oder Bugs auftreten bitte kontaktiert mich auf Discord ProxyBlack#7754\n",MessageBuilder.Formatting.BOLD,MessageBuilder.Formatting.ITALICS);
        info.append( "Version "+GeneralData.VERSION, MessageBuilder.Formatting.BOLD);
    }

    @Override
    public void help(PrivateMessageReceivedEvent event) {
        event.getChannel().sendMessage(info.toString()).queue();
    }

    @Override
    public void command(PrivateMessageReceivedEvent event) {
        event.getChannel().sendMessage(info.getStringBuilder().toString()).queue();
    }

}
