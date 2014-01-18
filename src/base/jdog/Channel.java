package base.jdog;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

/**
 * Created by Jordan on 1/17/14.
 */
public enum Channel
{
    OOC(ChatColor.DARK_GRAY),
    SAY(ChatColor.GREEN, 32),
    YELL(ChatColor.RED, 64),
    LOW_VOICE(ChatColor.DARK_PURPLE, 8),
    EMOTE(ChatColor.YELLOW, 32),
    WHISPER(ChatColor.BLUE, 2);

    private final double RANGE;
    private final ChatColor COLOR;
    Channel(ChatColor c)
    {
        //-1 denotes global chat
        this(c, -1);
    }
    Channel(ChatColor c, double range)
    {
        COLOR = c;
        RANGE = range;
    }

	public void sendMessage(Player x, String message)
    {
		int recipients = 0;

		//if not emote
		if (this != EMOTE)
		{
			//if range not global
			if (this != OOC)
			{
				for (Player y : Bukkit.getOnlinePlayers())
				{
					if (y.isOnline() && y != null)
					{
						if (x.getLocation().distance(y.getLocation()) <= RANGE)
						{
							y.sendMessage(getColor() + getTag() + ChatColor.GRAY + x.getName() + ": " + ChatColor.WHITE + message);
							recipients++;
						}
					}
				}
			}
			//else (if global)
			else
			{
				for (Player y : Bukkit.getOnlinePlayers())
				{
					if (y.isOnline() && y != null)
					{
						y.sendMessage(getColor() + getTag() + ChatColor.GRAY + x.getName() + ": " + ChatColor.WHITE + message);
						recipients++;
					}
				}
			}
		}
		//else (if emote)
		else
		{
			for (Player y : Bukkit.getOnlinePlayers())
			{
				if (y.isOnline() && y != null)
				{
					if (x.getLocation().distance(y.getLocation()) <= RANGE)
					{
						y.sendMessage(ChatColor.YELLOW + x.getName() + " " + message);
						recipients++;
					}
				}
			}
		}
		// let sender know if no one got their message
		if (recipients == 1)
        {
			x.sendMessage(ChatColor.DARK_GRAY + "No one " + (this == EMOTE ? "saw" : "heard") + " you.");
        }
    }

	public ChatColor getColor()
    {
		return COLOR;
	}

	public String getTag()
    {
		return "(" + super.toString().replace('_', ' ') + ")";
	}
}
