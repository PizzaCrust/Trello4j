package online.pizzacrust.trello.impl;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;

import online.pizzacrust.trello.Card;

public class BasicCard extends BasicIdentifiable implements Card {

    private String name;

    @SerializedName("desc")
    private String description;

    public BasicCard() {}

    private String apiKey;

    private String userToken;

    public BasicCard(String id,
                     String apiKey,
                     String userToken) throws Exception {
        super(id);
        installMapper();
        this.apiKey = apiKey;
        this.userToken = userToken;
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
        HttpRequest request = Unirest.get(rootUrl);
        if (apiKey != null && userToken != null) {
            request = request.queryString("key", apiKey).queryString("token", userToken);
        }
        BasicCard basicCard = request.asObject(BasicCard.class).getBody();
        this.description = basicCard.getDescription();
        this.name = basicCard.getName();
    }

    public static void main(String... args) throws Exception {
        BasicCard basicCard = new BasicCard("fHuGaT5h", args[0], args[1]);
        System.out.println(basicCard.getName());
        System.out.println(basicCard.getDescription());
    }

    public static final boolean OBJECT_MAPPER_INSTALLED = false;

    public static void installMapper() {
        if (!OBJECT_MAPPER_INSTALLED) {
            Unirest.setObjectMapper(new ObjectMapper() {
                public <T> T readValue(String s, Class<T> aClass) {
                    return new Gson().fromJson(s, aClass);
                }

                public String writeValue(Object o) {
                    return new Gson().toJson(o);
                }
            });
        }
    }

}
