package online.pizzacrust.trello.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import online.pizzacrust.trello.Board;
import online.pizzacrust.trello.BoardList;
import online.pizzacrust.trello.WebService;

public class BasicBoard extends BasicIdentifiable implements Board {

    public BasicBoard() {}

    private WebService webService;

    public BasicBoard(String id,
                      WebService webService) throws Exception {
        super(id);
        this.webService = webService;
        refresh();
    }

    private String name;

    private List<BoardList> boardLists;

    public void refresh() throws Exception {
        String url = "https://api.trello.com/1/boards/" + id;
        BasicBoard basicBoard = webService.getRequest(url, BasicBoard.class);
        this.name = basicBoard.name;
        String listsUrl = url + "/lists";
        BasicBoardList[] boardLists = webService.getRequest(listsUrl, BasicBoardList[].class);
        this.boardLists = new ArrayList<BoardList>(Arrays.asList(boardLists));
    }

    public List<BoardList> getLists() {
        return boardLists;
    }

    public String getName() {
        return name;
    }

    public static void main(String... args) throws Exception {
        BasicBoard basicBoard = new BasicBoard("WUuzcZf5", new WebService(null, null));
        System.out.println(basicBoard.name);
        for (BoardList boardList : basicBoard.getLists()) {
            boardList.refresh();
        }
    }
}
