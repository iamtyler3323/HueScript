package com.qbots.hue.tasks;

import com.qbots.hue.AbstractTask;

/**
 * Created by Tyler on 2/18/2015.
 */
public class EndIf extends AbstractTask {
    @Override
    public int execute() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean isIf() {
        return false;
    }

    @Override
    public boolean isEndIf() {
        return true;
    }
}
