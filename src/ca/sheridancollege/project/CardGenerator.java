package ca.sheridancollege.project;

import ca.sheridancollege.project.GameCards.Suit;
import ca.sheridancollege.project.GameCards.Value;
import java.util.ArrayList;

/**
 * 
 * A class is used to generate 52 cards and add it into an ArrayList
 *
 * @author Jay Paranjkumar Patel
 * @date: July 19, 2022
 */
public class CardGenerator {
    
    public static ArrayList<Card> generator(){
        
        ArrayList<Card> deck = new ArrayList<>();
        
        for(Suit suit : Suit.values()){
            for(Value value : Value.values()){
                GameCards card = new GameCards(suit, value);
                deck.add(card);
            }
        }
        return deck;
    }
}
