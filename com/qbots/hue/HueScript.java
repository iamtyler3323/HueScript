package com.qbots.hue;

import com.qbots.hue.tasks.EndIf;
import com.qbots.hue.tasks.If;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

import java.util.ArrayList;

/**
 * Created by Tyler on 2/18/2015.
 */
@ScriptManifest(author="qbots", category= Category.MISC, name="HueScript", version=1.0, description="Does stuff")
public class HueScript extends AbstractScript {
    public ArrayList<AbstractTask> tasks = new ArrayList<>();
    public boolean running = false;
    HueFrame frame;
    public void onStart() {
        frame = new HueFrame(this);
    }


    @Override
    public int onLoop() {
        if(running)
        for(int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).isIf()) {
                frame.list.setSelectedIndex(i);
                if(((If) tasks.get(i)).isTrue()) i = handleIfTrue(tasks.get(i+1),i+1);
                else i = handleIfFalse(tasks.get(i+1),i+1);
            } else {
                frame.list.setSelectedIndex(i);
                sleep(tasks.get(i).execute());
            }
        }
        return 100;
    }

    public int handleIfTrue(AbstractTask task, int i) {
        if(task instanceof EndIf) return i;
        else {
            if(tasks.get(i).isIf()) {
                if(((If) tasks.get(i)).isTrue()) return handleIfTrue(tasks.get(i+1),i+1);
                else return handleIfFalse(tasks.get(i+1),i+1);
            } else {
                frame.list.setSelectedIndex(i);
                sleep(task.execute());
                return handleIfTrue(tasks.get(i+1),i+1);
            }
        }
    }

    public int handleIfFalse(AbstractTask task, int i) {
        if(task.isEndIf()) {
            frame.list.setSelectedIndex(i);
            return i;
        }
        else return handleIfFalse(tasks.get(i+1),i+1);
    }
}
