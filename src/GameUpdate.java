public interface GameUpdate {

    void handleNextPlayerEvent(NextPlayerEvent e);
    void handleDrawCardEvent(DrawCardEvent e);
    void handlePlaceCardEvent(PlaceCardEvent e);
}
