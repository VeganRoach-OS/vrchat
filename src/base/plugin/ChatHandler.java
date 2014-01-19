package base.plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
        //A player has to send this command
		if (sender instanceof Player)
        {
			Player player = (Player) sender;
            String name = player.getDisplayName();
            Channel c;

			// convert command input into lowercase string
			String command = cmd.getName().toLowerCase();

            switch (command)
            {
                case "ooc":
                    c = Channel.OOC;
                    break;
                case "say":
                case "s":
                    c = Channel.SAY;
                    break;
                case "yell":
                case "y":
                    c = Channel.YELL;
                    break;
                case "whisper":
                case "w":
                    c = Channel.WHISPER;
                    break;
                default:
                    player.sendMessage(ChatColor.RED + command + " is an invalid VRChat command. Please try again or type /help VRChat for help.");
                    return false;
            }

            //Check for channel redundancy. No need to switch to a channel the player is already in.
            if(c != players.get(name))
            {
                //No arguments. Send player to specified channel
                if (args.length == 0)
                {

                    if(c != players.get(name))
                    {
                        players.put(name, c);
                        player.sendMessage(c.getColor() + "You've switched to channel: " + c.name());
                    }
                    else
                    {
                        player.sendMessage(c.getColor() + "You're already in channel: " + c.name());
                    }
                }
                //Send a single message in the specified channel without switching
                else
                {
                    String message = "";
                    for (String s : args)
                    {
                        message += (s + " ");
                    }

                    c.sendMessage(player, message);
                }
            }
            else
            {
                player.sendMessage(ChatColor.RED + "You did not " + (args.length == 0 ? "switch into" : "speak in") + " channel: " + c.name() + " because you're already in it.");
            }
            return true;
		}

        sender.sendMessage("This command cannot be executed via console. Please log into an account and try again.");
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
