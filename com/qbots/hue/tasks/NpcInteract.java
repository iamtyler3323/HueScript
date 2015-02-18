package com.qbots.hue.tasks;

/**
 * Created by Tyler on 2/18/2015.
 */
import com.qbots.hue.AbstractTask;
import org.dreambot.api.methods.MethodContext;
import org.dreambot.api.methods.filter.Filter;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;

/**
 * Created by Tyler on 2/18/2015.
 */
public class NpcInteract extends AbstractTask {
    private String action,name;
    private MethodContext ctx;
    private Filter<NPC> filter;
    public NpcInteract(String action, String name, MethodContext c) {
        this.action = action;
        this.name = name;
        ctx = c;
        filter = new Filter<NPC>() {
            @Override
            public boolean match(NPC gameObject) {
                return gameObject != null && gameObject.getName() != null && gameObject.getName().equals(name);
            }
        };
    }

    @Override
    public int execute() {
        final NPC object = ctx.getNpcs().closest(filter);
        if(object != null) {
            object.interact(action);
        }
        return 1;
    }

    @Override
    public String getName() {
        return action + " -> " + name;
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