package online.pizzacrust.trello.impl;

import com.google.gson.annotations.SerializedName;

import online.pizzacrust.trello.Card;
import online.pizzacrust.trello.PluginData;
import online.pizzacrust.trello.WebService;

public class BasicCard extends BasicIdentifiable implements Card {

    private String name;

    @SerializedName("desc")
    private String description;

    public BasicCard() {}

    private WebService webService;

    public BasicCard(String id,
                     WebService webService) throws Exception {
        super(id);
        this.webService = webService;
        refresh();
    }

    private PluginData[] pluginData;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void refresh() throws Exception {
        String rootUrl = "https://api.trello.com/1/cards/" + id;
        BasicCard basicCard = webService.getRequest(rootUrl, BasicCard.class);
        this.description = basicCard.getDescription();
        this.name = basicCard.getName();
        String secondaryUrl = "https://api.trello.com/1/cards/" + id +"/pluginData";
        this.pluginData = webService.getRequest(secondaryUrl, BasicPluginData[].class);
    }

    public void setDescription(String string) throws Exception {
        if (!webService.isUsingAuthentication()) {
            throw new Exception();
        }
        webService.putRequest("https://api.trello.com/1/cards/" + id, "desc", string);
    }

    @Override
    public PluginData[] getPluginData() {
        return pluginData;
    }

    public static void main(String... args) throws Exception {
        BasicCard basicCard = new BasicCard("qeEKSxAS", new WebService(args[0], args[1]));
        for (PluginData pluginData : basicCard.getPluginData()) {
            System.out.println(pluginData.getValue());
        }
    }

}
