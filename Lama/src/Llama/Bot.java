package Llama;

public class Bot extends Player{
    private int level;


    public Bot(String name, int level) {
        super(name);
        this.level = level;
    }

    public int getLevel(){
        return this.level;
    }

    public int getPlayableCard(Card currentCard) {
        for (int i = 0; i < getDeck().getDeckSize(); i++) {
            Card card = getDeck().getCard(i);
            if ((card.getValue() >= currentCard.getValue()) || (card.getValue() == 1 && currentCard.getValue() == 10) || (card.getValue() == 10 && currentCard.getValue() == 10) || (card.getValue() == 6 && currentCard.getValue() == 10)) {
                return i;
            }
        }
        return -1;
    }

    public String chooseAction(Deck currentDeck, Stack stack){
        if (this.hasPlayableCard(currentDeck.getLastCard())){
            return "P";
        }
        else if (stack.getDeckSize() != 0){
            return "PC";
        }
        else{
            return "Q";
        }
    }
}
