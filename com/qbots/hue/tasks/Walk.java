package com.qbots.hue.tasks;

import com.qbots.hue.AbstractTask;
import org.dreambot.api.methods.MethodContext;
import org.dreambot.api.methods.map.Tile;

/**
 * Created by Tyler on 2/18/2015.
 */
public class Walk extends AbstractTask {
    private Tile finish;
    private MethodContext ctx;
    public Walk(Tile f, MethodContext c) {
        ctx = c;
        finish = f;
    }


    @Override
    public int execute() {
        while(ctx.getLocalPlayer().getTile().distance(finish) > 1) {
            ctx.getWalking().walk(finish);
            ctx.sleepWhile(() -> {
                final Tile dest = ctx.getClient().getDestination();
                return dest != null && dest.distance(ctx.getLocalPlayer()) > 7;
            },2000);
        }
        return 10;
    }

    @Override
    public String getName() {
        return "Walking to " + finish.toString();
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
