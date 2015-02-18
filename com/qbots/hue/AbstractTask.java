package com.qbots.hue;

/**
 * Created by Tyler on 2/18/2015.
 */
public abstract class AbstractTask {
    public abstract int execute();
    public abstract String getName();
    public abstract boolean isIf();
    public abstract boolean isEndIf();
}
