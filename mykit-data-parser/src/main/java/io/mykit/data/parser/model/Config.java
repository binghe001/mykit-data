package io.mykit.data.parser.model;

public class Config extends ConfigModel {

    private int refreshInterval = 10;

    public int getRefreshInterval() {
        return refreshInterval;
    }

    public void setRefreshInterval(int refreshInterval) {
        this.refreshInterval = refreshInterval;
    }
}