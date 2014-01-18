package base.plugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 2mac
 */
public class ChatHandler extends JavaPlugin
{
	private Map<String, Channel> players;

    public void onEnable()
    {
        players = new HashMap<>();

        for(Player p : Bukkit.getOnlinePlayers())
        {
            players.put(p.getDisplayName(), Channel.OOC);
        }

        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new PlayerChatListener(this), this);
        pm.registerEvents(new PlayerLoginListener(this), this);
    }

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        Channel c;
		// commands useless in console
		if (sender instanceof Player)
        {
			Player player = (Player) sender;
			// convert command input into lowercase string
			String command = cmd.getName().toLowerCase();
            // command aliasing
            switch (command)
            {
                case "say":
                    command = "s";
                    break;
                case "yell":
                    command = "y";
                    break;
                case "whisper":
                    command = "w";
                    break;
                case "emote":
                    command = "e";
                    break;
            }
			// if no command args, send to channel
			if (args.length == 0)
            {
				switch (command)
                {
					case "ooc":
						c = Channel.OOC;
						break;
					case "s":
						c = Channel.SAY;
						break;
					case "y":
						c = Channel.YELL;
						break;
					case "w":
						c = Channel.WHISPER;
						break;
					default:
                        player.sendMessage(command + " is an invalid chat command. Please try again or type /help VRChat for help.");
                        return false;
				}

                players.put(player.getDisplayName(), c);
                player.sendMessage(c.getColor() + "You've switched to channel: " + c.name().replace('_', ' '));
			}
			// else, send a single message in the specified channel
			else
            {
				String message = "";
				for (String s : args)
                {
					message += (s + " ");
				}
				switch (command)
                {
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
					case "e":
						Channel.EMOTE.sendMessage(player, message);
						break;
				}
			}
		}
		return false;
	}
    private class PlayerLoginListener implements Listener
    {
        private ChatHandler plugin;

        PlayerLoginListener(ChatHandler h)
        {
            plugin = h;
        }

        @EventHandler
        public void onPlayerLogin(PlayerLoginEvent event)
        {
            players.put(event.getPlayer().getDisplayName(), Channel.OOC);
        }
    }

    private class PlayerChatListener implements Listener
    {
        private ChatHandler plugin;
        PlayerChatListener(ChatHandler h)
        {
            plugin = h;
        }

        @EventHandler
        public void onPlayerChat(AsyncPlayerChatEvent event)
        {
            players.get(event.getPlayer().getDisplayName()).sendMessage(event.getPlayer(), event.getMessage());
            getLogger().info(players.get(event.getPlayer().getDisplayName()).getTag() + event.getPlayer().getDisplayName() + ": " + event.getMessage());
            event.setCancelled(true);
        }
    }
	public void onDisable(){}
}
