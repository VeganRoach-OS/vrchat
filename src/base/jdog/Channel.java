package base.jdog;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by Jordan on 1/17/14.
 */
public enum Channel
{
    OOC, SAY(32), YELL(64), LOW_VOICE(8), EMOTE(32), WHISPER(2);

    private final double RANGE;

    Channel()
    {
        this(-1);
		// TODO: initialize color and name
    }
    Channel(double range)
    {
        RANGE = range;
		// TODO: initialize color and name
    }

	public void sendMessage(Player x, String message){
		int recipients = 0;
		// TODO: if not emote
		// TODO: if range not global
		for (Player y : Bukkit.getOnlinePlayers()){
			if (y.isOnline() && y != null){
				if (x.getLocation().distance(y.getLocation()) <= RANGE){
					y.sendMessage(getColor() + getTag() + ChatColor.GRAY + x.getName() + ": " + ChatColor.WHITE + message);
					recipients++;
				}
			}
		}
		// TODO: else (if emote)
		for (Player y : Bukkit.getOnlinePlayers()){
			if (y.isOnline() && y != null){
				if (x.getLocation().distance(y.getLocation()) <= RANGE){
					y.sendMessage(ChatColor.YELLOW + x.getName() + message);
					recipients++;
				}
			}
		}
		// let sender know if no one got their message
		if (recipients < 1)
			x.sendMessage(ChatColor.DARK_GRAY + "No one heard you.");
	}

	private ChatColor getColor(){
		// TODO: return channel's color
		return null;
	}

	private String getTag(){
		// TODO: return name surrounded by parens
		return null;
	}
}
