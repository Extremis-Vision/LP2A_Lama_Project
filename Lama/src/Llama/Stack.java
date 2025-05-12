package Llama;

public class Stack extends Deck{

    public Stack(){
        super();
        for (int diffCard = 1; diffCard <= 7; diffCard++) {
            for (int numberCard = 1; numberCard <= 8; numberCard++){
                if( diffCard == 7){
                    diffCard = 10;
                }
                deck.add(new Card(diffCard));
            }
        }

        this.shuffle();
    }
}