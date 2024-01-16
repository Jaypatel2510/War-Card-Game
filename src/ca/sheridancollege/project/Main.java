package ca.sheridancollege.project;

import ca.sheridancollege.project.GameCards.Suit;
import ca.sheridancollege.project.GameCards.Value;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  Main class that get input from the user, divide deck for 2 players
 * 
 * @author Jay Paranjkumar Patel
 * @date: July 25, 2022
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        
        ArrayList<GamePlayer> players = new ArrayList<>();
        ArrayList<Card> deck = CardGenerator.generator();
        
        int cardsInDeck = Suit.values().length * Value.values().length;
        
        GroupOfCards cardDeck = new GroupOfCards(cardsInDeck, deck);
        cardDeck.shuffle();
        
        ArrayList<Card> player1Cards = new ArrayList<>();
        ArrayList<Card> player2Cards = new ArrayList<>();
        
        for(int i = 0; i < cardsInDeck; i++){
            if(i < cardsInDeck/2){
                player1Cards.add(deck.get(i));
            }else{
                player2Cards.add(deck.get(i));
            }
        }
        
        GroupOfCards player1Deck = new GroupOfCards(player1Cards.size(), player1Cards);
        GroupOfCards player2Deck = new GroupOfCards(player2Cards.size(), player2Cards);
         
        while(players.size() < 2){
            System.out.print("Enter Your Name: ");
            String playerName = input.next();
            
            if(playerName.length() > 0){
                boolean isExist = false;
                for(GamePlayer p : players){
                    if(p.getName().equalsIgnoreCase(playerName)){
                        isExist = true;
                    } 
                }
                if(!isExist){
                    if(players.isEmpty()){
                        GamePlayer addplayer = new GamePlayers(playerName, 0, player1Deck);
                        players.add(addplayer);
                        addplayer.play();
                    } else {
                        GamePlayer addplayer = new GamePlayers(playerName, 0, player2Deck);
                        players.add(addplayer);
                        addplayer.play();
                    }
                }else{
                    System.out.println("Player already exists");
                    System.out.println("GUEST name assigned by program.");
                    GamePlayer addplayer = new GamePlayers("Guest", 0, player2Deck);
                    players.add(addplayer);
                    addplayer.play();
                }
            }
        }
        
        input.close();
        
        if(players.size() == 2){
            GamePlay game = new GamePlay("WAR CARD GAME");
            System.out.printf("Game %s started", game.getName());
            
            game.setPlayers(players);
            
            try{
                game.play();
            }catch (Exception e){
                e.getMessage();
            }
        }
    }
}
