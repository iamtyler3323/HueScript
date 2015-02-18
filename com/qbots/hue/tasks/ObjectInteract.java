package com.qbots.hue.tasks;

import com.qbots.hue.AbstractTask;
import org.dreambot.api.methods.MethodContext;
import org.dreambot.api.methods.filter.Filter;
import org.dreambot.api.wrappers.interactive.GameObject;

/**
 * Created by Tyler on 2/18/2015.
 */
public class ObjectInteract extends AbstractTask {
    private String action,name;
    private MethodContext ctx;
    private Filter<GameObject> filter;
    public ObjectInteract(String action, String name, MethodContext c) {
        this.action = action;
        this.name = name;
        ctx = c;
        filter = new Filter<GameObject>() {
            @Override
            public boolean match(GameObject gameObject) {
                return gameObject != null && gameObject.getName() != null && gameObject.getName().equals(name);
            }
        };
    }

    @Override
    public int execute() {
        final GameObject object = ctx.getGameObjects().closest(filter);
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
