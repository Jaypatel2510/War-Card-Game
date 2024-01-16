package ca.sheridancollege.project;

/**
 * This class model cards equally between players and also count the score for them
 * 
 * @author Jay Paranjkumar Patel
 * @date: July 25, 2022
 */
public class GamePlayers extends GamePlayer{
    
    private int score;
    private GroupOfCards groupOfCards;
    
    public GamePlayers(String name){
        super(name);
    }

    public GamePlayers(String name, int score, GroupOfCards groupOfCards) {
        super(name);
        this.score = score;
        this.groupOfCards = groupOfCards;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public GroupOfCards getGroupOfCards() {
        return groupOfCards;
    }

    public void setGroupOfCards(GroupOfCards groupOfCards) {
        this.groupOfCards = groupOfCards;
    }

    @Override
    public void play() {
        System.out.println(super.getName() + " is in the game.");
    }
       
}
