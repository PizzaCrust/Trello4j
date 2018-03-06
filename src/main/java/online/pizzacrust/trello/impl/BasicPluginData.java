package online.pizzacrust.trello.impl;

import online.pizzacrust.trello.PluginData;

public class BasicPluginData implements PluginData {

    private String id;
    private String idPlugin;
    private String scope;
    private String idModel;
    private String value;
    private String access;

    @Override
    public String getId() {
        return id;
    }

    public String getPluginId() {
        return idPlugin;
    }

    @Override
    public String getScope() {
        return scope;
    }

    public String getModelId() {
        return idModel;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getAccess() {
        return access;
    }
}
