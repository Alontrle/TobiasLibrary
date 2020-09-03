package com.tobiassteely.tobiasapi.api.worker;

import com.tobiassteely.tobiasapi.TobiasAPI;
import com.tobiassteely.tobiasapi.api.manager.ManagerObjectInterface;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Worker extends Thread implements ManagerObjectInterface {

    private long timer;
    private long start;
    private ArrayList<Long> ticks;
    private boolean isAlive;
    private boolean isPaused;
    private String name;

    public Worker(String name, long timer) {
        this.name = name;
        this.ticks = new ArrayList<>();
        this.timer = timer;
        this.start = 0;
        this.isAlive = false;
        this.isPaused = false;
        WorkerManager.getInstance().addObject(this);
    }

    public Boolean runWorker(long start) {
        // OVERRIDE
        return false;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    @Override
    public void run() {
        long lastLoop = 0;
        isAlive = true;
        while (true) {
            if(!isAlive)
                break;

            if(timer > 0) {
                if (isPaused) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                    }
                    continue;
                }
            }

            if ((System.currentTimeMillis() - lastLoop) < timer) {
                if(!runWorker(System.currentTimeMillis())) {
                    TobiasAPI.getInstance().getLog().sendMessage(2, "Worker is not overridden! Stopping to preserve CPU.");
                    break;
                }
                tick();
            }
            if(timer > 0) {
                try {
                    long sleep = lastLoop + timer - System.currentTimeMillis() - 1;
                    if (sleep > 0)
                        Thread.sleep(sleep);
                } catch (InterruptedException ex) {
                }
            }
            lastLoop = System.currentTimeMillis();
        }
        isAlive = false;
    }

    public void stopWorker() {
        isAlive = false;
    }

    private void tick() {
        if(start == 0) {
            start = System.currentTimeMillis();
        } else {
            ticks.add(System.currentTimeMillis() - start);
            if(ticks.size() > 50)
                ticks.remove(0);
        }
    }

    public double[] getTPS() {
        ArrayList<Long> temp = new ArrayList<>(ticks);

        double totalTime = 0;
        double lastTick = 0;
        for(long tick : ticks) {
            totalTime += tick - lastTick;
            lastTick = tick;
        }

        double averageTime = totalTime / temp.size();
        return new double[] {1000 / averageTime, temp.size()};
    }


    public String getStatus() {
        double[] tpsResults = getTPS();
        return "Running at " + ((int)(tpsResults[0] * 10000) / 10000.0) + " TPS, expected TPS is " + ((int)((1000.0 / timer) * 10000) / 10000.0) + " TPS (" + (int)tpsResults[1] + " Ticks in cache)";
    }

    public boolean isWorkerAlive() {
        return isAlive;
    }

    public long getTimer() {
        return timer;
    }

    @Override
    public String getKey() {
        return name;
    }

    @Override
    public void setKey(String key) {
        this.name = key;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Name", name);
        jsonObject.put("Alive", isWorkerAlive());
        if(isWorkerAlive()) {
            jsonObject.put("Status", getStatus());
        }

        return jsonObject;
    }
}
