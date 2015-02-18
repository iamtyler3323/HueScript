package com.qbots.hue.tasks;

import com.qbots.hue.AbstractTask;
import com.qbots.hue.Condition;
import org.dreambot.api.methods.MethodContext;

/**
 * Created by Tyler on 2/18/2015.
 */
public class If extends AbstractTask {
    private MethodContext ctx;
    Condition condition;
    public If(Condition condition, MethodContext c) {
        ctx = c;
        this.condition = condition;
    }

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
        return true;
    }

    @Override
    public boolean isEndIf() {
        return false;
    }

    public boolean isTrue() {
        return condition.validate(ctx);
    }
}
