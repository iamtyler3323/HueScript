package com.qbots.hue.tasks;

import com.qbots.hue.AbstractTask;
import org.dreambot.api.methods.MethodContext;

/**
 * Created by Tyler on 2/18/2015.
 */
public class DropItem extends AbstractTask {
    private String name;
    private MethodContext ctx;
    @Override
    public int execute() {
        ctx.getInventory().dropItem(name);
        return 1;
    }

    @Override
    public String getName() {
        return "Dropping "+name;
    }

    @Override
    public boolean isIf() {
        return false;
    }

    @Override
    public boolean isEndIf() {
        return false;
    }
}
