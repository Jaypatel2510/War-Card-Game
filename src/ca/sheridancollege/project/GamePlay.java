package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * This is the class in which all operations are being performed. Main logic of
 * the WAR GAME, drew cards, compare with each other, display results
 * (Override Game class method) and finally declare WINNER.
 * 
 * @author Jay Paranjkumar Patel
 * @date: July 30, 2022
 */
public class GamePlay extends Game{
       
    private GamePlayers winner;
    
    ArrayList<GamePlayer> players;
    
    private GamePlayer player1;
    private GamePlayer player2;
    
    private Card c1;
    private Card c2;
    
    private GameCards cards1;
    private GameCards cards2;
    
    private GroupOfCards groupOfCard1;
    private GroupOfCards groupOfCard2;
    
    private ArrayList<Card> card1;
    private ArrayList<Card> card2;
    
    private final ArrayList<GameCards> p1Cards = new ArrayList<>();
    private final ArrayList<GameCards> p2Cards = new ArrayList<>();
    
    private int count1 = 0, count2 = 0, iterate = 0, beg1, beg2, end1, end2;
    private final int drawLimit = 3;

    public GamePlay(String name) {
        super(name);
    }

    public GamePlayers getWinner() {
        return winner;
    }

    public void setWinner(GamePlayers winner) {
        this.winner = winner;
    } 
    
    @Override
    public void play() {
        
        players = super.getPlayers();
      
        player1 = players.get(0);
        player2 = players.get(1);
        
        groupOfCard1 = (((GamePlayers) player1).getGroupOfCards());
        groupOfCard2 = (((GamePlayers) player2).getGroupOfCards());
        
        card1 = groupOfCard1.getCards();
        card2 = groupOfCard2.getCards();
        
        groupOfCard1.setSize((card1.size()));
        groupOfCard2.setSize((card2.size()));

        while(!card1.isEmpty() && !card2.isEmpty()){
            
            System.out.println("------------------------------------------------");
            System.out.println("Round: " + (++iterate));
            
            c1 = card1.get(card1.size() - 1);
            cards1 = ((GameCards) c1);
            
            c2 = card2.get(card2.size() - 1);
            cards2 = ((GameCards) c2);
            
            System.out.println(player1.getName()+" draws: "+c1);
            System.out.println(player2.getName()+" draws: "+c2);
            
            if(cardConversion(cards1) == cardConversion(cards2)){                
                this.warRound();
            } else if(cardConversion(cards1) > cardConversion(cards2)){
               System.out.println("---------"+player1.getName()+" WON ROUND---------");
               card1.add(0, cards1);
               card1.add(0, cards2);
               count1++;
               
               if(!card1.isEmpty()){
                   card1.remove(card1.size()-1);
               }
               if(!card2.isEmpty()){
                   card2.remove(card2.size()-1);
               }
            } else{
               System.out.println("---------"+player2.getName()+" WON ROUND---------");
               card2.add(0, cards2);
               card2.add(0, cards1);
               count2++;
               
               if(!card1.isEmpty()){
                   card1.remove(card1.size()-1);
               }
               if(!card2.isEmpty()){
                   card2.remove(card2.size()-1);
               }
            }
            
            System.out.println("Score: ["+player1.getName()+"="+count1+" | "+player2.getName()+"="+count2+"]");
            System.out.println("Total Cards: ["+player1.getName()+"="+card1.size()+" | "+player2.getName()+"="+card2.size()+"]");
        }
        
        if(card1.isEmpty()){
            ((GamePlayers) player2).setGroupOfCards(groupOfCard2);
            ((GamePlayers) player2).setScore(count2);
            setWinner((GamePlayers) player2);
            this.declareWinner();
        } else if(card2.isEmpty()){
            ((GamePlayers) player1).setGroupOfCards(groupOfCard1);
            ((GamePlayers) player1).setScore(count1);
            setWinner((GamePlayers) player1);
            this.declareWinner();
        }
    }
    
    private int cardConversion(GameCards card){
        int cardNo;
        
        switch (card.getValue()) {
            case ACE: 
                cardNo = 1;
                break;  
            case TWO: 
                cardNo = 2;
                break;
            case THREE: 
                cardNo = 3;
                break;
            case FOUR: 
                cardNo = 4;
                break; 
            case FIVE: 
                cardNo = 5;
                break;  
            case SIX: 
                cardNo = 6;
                break;  
            case SEVEN: 
                cardNo = 7;
                break;  
            case EIGHT: 
                cardNo = 8;
                break;   
            case NINE: 
                cardNo = 9;
                break;  
            case TEN: 
                cardNo = 10;
                break;  
            case JACK: 
                cardNo = 11;
                break;   
            case QUEEN: 
                cardNo = 12;
                break;                 
            case KING: 
                cardNo = 13;
                break; 
            default: 
                cardNo = 0;
                break;
        }
        return cardNo;
    }
    
    private void warRound(){
        
        if(card1.size() <= drawLimit || card2.size() <= drawLimit){
            
            if(card1.size() > card2.size()){
                System.out.println(player1.getName() + " has highest number of cards. So, " + player1.getName() + " won the round.");
                count1++;
                card1.addAll(card2);
                ((GamePlayers) player1).setGroupOfCards(groupOfCard1);
                ((GamePlayers) player1).setScore(count1);
                setWinner((GamePlayers) player1);
                card2.clear();
                this.declareWinner();
            } else if(card1.size() < card2.size()){
                System.out.println(player2.getName() + " has highest number of cards. So, " + player2.getName() + " won the round.");
                count2++;
                card2.addAll(card1);
                ((GamePlayers) player2).setGroupOfCards(groupOfCard2);
                ((GamePlayers) player2).setScore(count2);
                setWinner((GamePlayers) player2);
                card1.clear();
                this.declareWinner();
            }
        } else{
            beg1 = (card1.size() -1);
            end1 = beg1 - drawLimit;
            
            beg2 = (card2.size() -1);
            end2 = beg2 - drawLimit;
            
            for(int i = beg1; i > end1; i--){
                c1 = card1.get(i);
                cards1 = ((GameCards) c1);
                p1Cards.add(cards1);
                
                if(!card1.isEmpty()){
                    card1.remove(card1.size() - 1);
                }
            }
            System.out.println(player1.getName() + " put down 3 cards and take 1 card which is: " + p1Cards.get(p1Cards.size()-1));
            
            for(int i = beg2; i > end2; i--){
                c2 = card2.get(i);
                cards2 = ((GameCards) c2);
                p2Cards.add(cards2);
                
                if(!card2.isEmpty()){
                    card2.remove(card2.size() - 1);
                }
            }
            System.out.println(player2.getName() + " put down 3 cards and take 1 card which is: " + p2Cards.get(p2Cards.size()-1));
            
            if(this.cardConversion(p1Cards.get(p1Cards.size()-1)) == this.cardConversion(p2Cards.get(p2Cards.size()-1))){
                this.warRound();
            }else if(this.cardConversion(p1Cards.get(p1Cards.size()-1)) < this.cardConversion(p2Cards.get(p2Cards.size()-1))){
                System.out.println("-------"+player2.getName()+" WON ROUND-------");
                count2++;
                card2.addAll(p2Cards);
                card2.addAll(p1Cards);
                
                p1Cards.clear();
                p2Cards.clear();
            }else{
                System.out.println("-------"+player1.getName()+" WON ROUND-------");
                count1++;
                card1.addAll(p1Cards);
                card1.addAll(p2Cards);
                
                p1Cards.clear();
                p2Cards.clear();
            }
        }
    }    

    @Override
    public void declareWinner() {
        System.out.println("----------------WINNER DECLARED-----------------");
        System.out.println("Winner: "+ this.winner.getName());
        System.out.println("Score: "+ this.winner.getScore());
        System.out.println("Total Card: "+ this.winner.getGroupOfCards().getCards().size());
    }   
}
