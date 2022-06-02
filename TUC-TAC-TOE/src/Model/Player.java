package Model;

        import java.io.Serializable;

public class  Player implements Serializable {

    private final String name;
    private final Game[] recent5Games;
    private final Game[] best5Games;
    private int gamesPlayed;
    private double wins;
    private double draws;

    public Player(String name){

        this.name=name;
        recent5Games=new Game[5];
        best5Games=new Game[5];
        gamesPlayed=0;
        wins=0;
        draws=0;
    }

    // finding player score
    public double score(){
        if(gamesPlayed==0)
            return 0;
        else
            return(50*(((2*wins)+(draws))/(gamesPlayed)));
    }

    // finding player score for 5 most recent games
    public double recentScore(){
        double recentDraws=0;
        double recentWins=0;
        int recentGamesPlayed=0;
        for(int i=0;i<recent5Games.length;i++){
            if(recent5Games[i]==null)
                break;
            else if(recent5Games[i].winner==this)
                recentWins++;
            else if(recent5Games[i].draw)
                recentDraws++;
            recentGamesPlayed++;
        }
        if(recentGamesPlayed==0)
            return 0;
        else
            return(50*(((2*recentWins)+(recentDraws))/(recentGamesPlayed)));
    }

    // checking if game should be placed among the 5 best and shifting right
    public void addBestGame(Game game) {

        int numOf5BestGames=Math.min(gamesPlayed-1, 5);

        if(gamesPlayed==1){
            best5Games[0]=game;
        }
        else {
            int index;
            for(index=0;index<numOf5BestGames;index++){
                if(Game.game1BetterThanGame2(this,game,best5Games[index])){
                    break;
                }
            }
            for(int j=numOf5BestGames;j>index;j--){

                best5Games[j]=best5Games[j-1];
            }
            best5Games[index]=game;
        }
    }

    // adding game to the most recent ones and shifting right
    public void addRecentGame(Game game){

        int numOfRecentGames=Math.min(gamesPlayed,4);

        for(int i=numOfRecentGames ;i>0;i--){

            recent5Games[i]=recent5Games[i-1];
        }
        recent5Games[0]=game;
    }

    public void updatePlayerStats(Game game ,boolean Won, boolean Draw){

        if(Won)
            wins++;
        else if(Draw)
            draws++;
        gamesPlayed++;
        addBestGame(game);
        addRecentGame(game);
    }

    public String getName() {
        return name;
    }

    public Game[] getBest5Games() {
        return best5Games;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public double getWins() {
        return wins;
    }

    public void setWins(double wins) {
        this.wins = wins;
    }

    public double getDraws() {
        return draws;
    }

    public void setDraws(double draws) {
        this.draws = draws;
    }
}


