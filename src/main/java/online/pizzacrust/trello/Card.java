package online.pizzacrust.trello;

public interface Card extends Identifiable,Refreshable {

    String getDescription();

    void setDescription(String newDesc) throws Exception;

    PluginData[] getPluginData();

}
