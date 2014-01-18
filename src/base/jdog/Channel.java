package base.jdog;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by Jordan on 1/17/14.
 */
public enum Channel
{
    OOC(ChatColor.DARK_GRAY),
    SAY(ChatColor.GREEN, 32),
    YELL(ChatColor.RED, 64),
    LOW_VOICE(ChatColor.DARK_PURPLE, 8),
    EMOTE(ChatColor.YELLOW ,32),
    WHISPER(ChatColor.BLUE, 2);

    private final double RANGE;
    private final ChatColor COLOR;
    Channel(ChatColor c)
    {
        this(c, -1);
		// TODO: initialize color and name
    }
    Channel(ChatColor c, double range)
    {
        COLOR = c;
        RANGE = range;
		// TODO: initialize color and name
    }

	public void sendMessage(Player x, String message)
    {
		int recipients = 0;

		// TODO: if not emote
		if (true)
		{
			// TODO: if range not global
			if (true)
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
			// TODO: else (if global)
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
		// TODO: else (if emote)
		else
		{
			for (Player y : Bukkit.getOnlinePlayers())
			{
				if (y.isOnline() && y != null)
				{
					if (x.getLocation().distance(y.getLocation()) <= RANGE)
					{
						y.sendMessage(ChatColor.YELLOW + x.getName() + message);
						recipients++;
					}
				}
			}
		}
		// let sender know if no one got their message
		if (recipients < 1)
        {
			x.sendMessage(ChatColor.DARK_GRAY + "No one heard you.");
        }
	}

	private ChatColor getColor()
    {
		return COLOR;
	}

	private String getTag()
    {
		return "(" + super.toString().replace('_', ' ') + ")";
	}
}
