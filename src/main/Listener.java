package main;

import net.dv8tion.jda.core.hooks.ListenerAdapter;
import Logger.Logger;
import Logger.Logger.Type;
import commands.CommandAffixe;
import commands.CommandHelp;
import commands.CommandInfo;
import commands.CommandLogs;
import commands.CommandScore;
import commands.CommandStandard;
import commands.CommandToken;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;

/**
 * @author Kilian Stöckler
 */
public class Listener extends ListenerAdapter {

    private String command;
    private String keyword = null;
    private CommandHelp cdHelp = new CommandHelp();
    private CommandAffixe cdAffixe = new CommandAffixe();
    private CommandLogs cdLogs = new CommandLogs();
    private CommandToken cdToken = new CommandToken();
    private CommandStandard cdStandard = new CommandStandard();
    private CommandScore cdScore = new CommandScore();
    private CommandInfo cdInfo = new CommandInfo();

    @Override
    public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
        Logger.print(Type.CONSOLE, String.format("We received a message from %s in channel %s : %s", event.getAuthor().getName(), event.getChannel().getName(), event.getMessage().getContentDisplay()));

        try {
            command = event.getMessage().getContentDisplay().split(" ")[0];
            keyword = event.getMessage().getContentDisplay().split(" ")[1];
        } catch (Exception e) {
            keyword = "nein";
        }
        switch (command) {
            case "!hilfe":
                if (!keyword.equalsIgnoreCase("?")) {
                    cdHelp.command(event);
                } else {
                    cdHelp.help(event);
                }
                break;
            case "!token": {
                if (!keyword.equalsIgnoreCase("?")) {
                    cdToken.command(event);
                } else {
                    cdToken.help(event);
                }
                break;
            }
            case "!affixe":
                if (!keyword.equalsIgnoreCase("?")) {
                    cdAffixe.command(event);
                } else {
                    cdAffixe.help(event);
                }
                break;
            case "!logs":
                if (!keyword.equalsIgnoreCase("?")) {
                    cdLogs.command(event);
                } else {
                    cdLogs.help(event);
                }
                break;
            case "!score":
                if (!keyword.equalsIgnoreCase("?")) {
                    cdScore.command(event);
                } else {
                    cdScore.help(event);
                }
                break;
            case "!info":
                if (!keyword.equalsIgnoreCase("?")) {
                    cdInfo.command(event);
                } else {
                    cdInfo.help(event);
                }
                break;
            default:
                if (!keyword.equalsIgnoreCase("?")) {
                    cdStandard.command(event);
                } else {
                    cdStandard.command(event);
                }

        }
    }

}
