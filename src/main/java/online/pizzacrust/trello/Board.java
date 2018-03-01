package online.pizzacrust.trello;

import java.util.List;

public interface Board extends Identifiable,Refreshable {

    List<BoardList> getLists();

}
