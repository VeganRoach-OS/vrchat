package base.mac;

/**
 * Created by Jordan on 1/17/14.
 */
public enum Channel
{
    OOC, SAY(32), YELL(64), LOW_VOICE(8), EMOTE(32);

    private final double RANGE;

    Channel()
    {
        this(-1);
    }
    Channel(double range)
    {
        RANGE = range;
    }
}
