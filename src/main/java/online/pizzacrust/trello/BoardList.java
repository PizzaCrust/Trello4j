package online.pizzacrust.trello;

import java.util.List;

public interface BoardList extends Identifiable,Refreshable {

    List<Card> getCards();

}
