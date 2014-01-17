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
		if (sender instanceof Player){
			Player player = (Player) sender;
			String command = cmd.getName().toLowerCase();
			switch (command){
				case "ooc":
					player.sendMessage(ChatColor.AQUA + "It works!");
					break;
			}
		}
		return false;
	}

	public void onDisable(){}
}
