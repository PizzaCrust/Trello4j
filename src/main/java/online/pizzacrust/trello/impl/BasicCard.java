package online.pizzacrust.trello.impl;

import com.google.gson.annotations.SerializedName;

import online.pizzacrust.trello.Card;
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
    }

    public void setDescription(String string) throws Exception {
        if (!webService.isUsingAuthentication()) {
            throw new Exception();
        }
        webService.putRequest("https://api.trello.com/1/cards/" + id, "desc", string);
    }

    public static void main(String... args) throws Exception {
        BasicCard basicCard = new BasicCard("fHuGaT5h", new WebService(args[0], args[1]));
        System.out.println(basicCard.getName());
        System.out.println(basicCard.getDescription());
        basicCard.setDescription("modified");
    }

}
