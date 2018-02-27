package online.pizzacrust.trello.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import online.pizzacrust.trello.BoardList;
import online.pizzacrust.trello.Card;
import online.pizzacrust.trello.WebService;

public class BasicBoardList extends BasicIdentifiable implements BoardList {

    private WebService webService;

    private String name;

    public BasicBoardList() {}

    public BasicBoardList(String id,
                          WebService webService) throws Exception {
        super(id);
        this.webService = webService;
        refresh();
    }

    private List<Card> cards;

    public void refresh() throws Exception {
        String url = "https://api.trello.com/1/lists/" + id;
        BasicBoardList basicBoardList = webService.getRequest(url, BasicBoardList.class);
        this.name = basicBoardList.name;
        String cardsUrl = url + "/cards";
        BasicCard[] cards = webService.getRequest(cardsUrl, BasicCard[].class);
        this.cards = new ArrayList<Card>(Arrays.asList(cards));
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public static void main(String... args) throws Exception {
        BoardList boardList = new BasicBoardList("5a27db806b88a9f6f3ed8205", new WebService
                (args[0],
                args[1]));
        System.out.println("name: " + boardList.getName());
        for (Card card : boardList.getCards()) {
            System.out.println(card.getName());
            System.out.println(card.getDescription());
            System.out.println(card.getId());
        }
    }
}
