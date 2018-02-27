package online.pizzacrust.trello;

public interface Card extends Identifiable {

    String getDescription();

    void setDescription(String newDesc) throws Exception;

}
