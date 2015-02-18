package com.qbots.hue;

import org.dreambot.api.methods.MethodContext;

/**
 * Created by Tyler on 2/18/2015.
 */
public abstract class Condition {

    public abstract boolean validate(MethodContext ctx);
}
