package online.pizzacrust.trello;

import com.google.gson.Gson;

public interface PluginData {

    String getId();

    String getPluginId();

    String getScope();

    String getModelId();

    String getValue();

    default <T> T getValueAsObject(Class<T> clazz) {
        return new Gson().fromJson(getValue(), clazz);
    }

    String getAccess();

}
