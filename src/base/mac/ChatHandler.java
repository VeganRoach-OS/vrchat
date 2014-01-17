package base.mac;

import org.bukkit.ChatColor;
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
						// TODO: set player's chat to OOC
						break;
					case "s":
						// TODO: set player's chat to SAY
						break;
					case "y":
						// TODO: set player's chat to YELL
						break;
					case "w":
						// TODO: set player's chat to WHISPER
						break;
					case "l":
						// TODO: set player's chat to LOW_VOICE
						break;
				}
			}
			// else, send a single message in the specified channel
			/*
			 * Emotes are also in this block, and they should stay
			 * in this block, because no one should be switching to
			 * an emote channel long-term.
			 */
			else {
				switch (command){
					case "ooc":
						// TODO: send a message in OOC
						break;
					case "s":
						// TODO: send a message in SAY
						break;
					case "y":
						// TODO: send a message in YELL
						break;
					case "w":
						// TODO: send a message in WHISPER
						break;
					case "l":
						// TODO: send a message in LOW_VOICE
						break;
					case "e":
						// TODO: send a local emote
						break;
				}
			}
		}
		return false;
	}

	public void onDisable(){}
}
