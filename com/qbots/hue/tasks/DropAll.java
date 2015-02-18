package com.qbots.hue.tasks;

import com.qbots.hue.AbstractTask;
import org.dreambot.api.methods.MethodContext;

/**
 * Created by Tyler on 2/18/2015.
 */
public class DropAll extends AbstractTask {
    private MethodContext ctx;

    public DropAll(MethodContext c) {
        ctx = c;
    }

    @Override
    public int execute() {
        ctx.getItemContainer().getInventory().dropAll();
        return 1;
    }

    @Override
    public String getName() {
        return "Dropping All";
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