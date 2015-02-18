package com.qbots.hue.tasks;

import com.qbots.hue.AbstractTask;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodContext;
import org.dreambot.api.wrappers.items.Item;

/**
 * Created by Tyler on 2/18/2015.
 */
public class DropAllExcept extends AbstractTask {
    private String[] names;
    private MethodContext ctx;

    public DropAllExcept(String[] names, MethodContext c) {
        ctx = c;
        this.names = names;
    }

    @Override
    public int execute() {
        ctx.getInventory().dropAllExcept(names);
        return 1;
    }

    @Override
    public String getName() {
        return "DropAllExcept shit";
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