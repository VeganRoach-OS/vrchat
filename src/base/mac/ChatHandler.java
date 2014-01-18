package base.mac;

import base.jdog.Channel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * @author 2mac
 */
public class ChatHandler extends JavaPlugin {
	public final Logger log = Logger.getLogger("Minecraft");
    private Channel currentChannel = Channel.OOC;
	public static ChatHandler thisPlugin;

	public void onEnable(){}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		// commands useless in console
		if (sender instanceof Player){
			Player player = (Player) sender;
			// convert command input into lowercase string
			String command = cmd.getName().toLowerCase();
			// if no command args, send to channel
			if (args.length == 0){
				switch (command){
					case "ooc":
						currentChannel = Channel.OOC;
						break;
					case "s":
						currentChannel = Channel.SAY;
						break;
					case "y":
						currentChannel = Channel.YELL;
						break;
					case "w":
						currentChannel = Channel.WHISPER;
						break;
					case "l":
                        currentChannel = Channel.LOW_VOICE;
						break;
				}
			}
			// else, send a single message in the specified channel
			else {
				String message = "";
				for (String s : args){
					message += (s + " ");
				}
				switch (command){
					case "ooc":
						Channel.OOC.sendMessage(player, message);
						break;
					case "s":
						Channel.SAY.sendMessage(player, message);
						break;
					case "y":
						Channel.YELL.sendMessage(player, message);
						break;
					case "w":
						Channel.WHISPER.sendMessage(player, message);
						break;
					case "l":
						Channel.LOW_VOICE.sendMessage(player, message);
						break;
					case "e":
						Channel.EMOTE.sendMessage(player, message);
						break;
				}
			}
		}
		return false;
	}

	public void onDisable(){}
}
