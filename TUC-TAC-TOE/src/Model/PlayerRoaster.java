package Model;

import java.io.Serializable;

public class PlayerRoaster implements Serializable {

    private Player[] playerList;
    private final PerfectPlayer hal=new PerfectPlayer("Hal");
    private final MediocrePlayer mamalakis=new MediocrePlayer("Mamalakis");
    private final BadPlayer MrBean=new BadPlayer("Mr Bean");
    private final Hashing zobrist=new Hashing();

    public PlayerRoaster(){

        playerList=new Player[10];
        playerList[0]=hal;
        playerList[1]=mamalakis;
        playerList[2]=MrBean;
        zobrist.tableInit();
    }

    // returning number of non-null elements of playerRoaster
    public int nonNullPlayerElems(){
        int numOfNonNullElements=0;
        for (Player player : playerList) {
            if (player != null) {
                numOfNonNullElements++;
            }
        }
        return numOfNonNullElements;
    }

    public Player getPlayer(int index){
        try {
            return playerList[index];
        }catch (ArrayIndexOutOfBoundsException e){
            return null;
        }
    }

    // doubling array size
    public Player[] incrementedPlayerArray(){
        Player[] newArray=new Player[playerList.length*2];
        System.arraycopy(playerList, 0, newArray, 0, playerList.length);
        return newArray;
    }

    // adding player to roaster by name
    public boolean addPlayer(String playerName){
        if(uniqueName(playerName)&&validName(playerName)){
            if(nonNullPlayerElems()==playerList.length){
                playerList=incrementedPlayerArray();
            }
            playerList[nonNullPlayerElems()]=new Player(playerName);
            return true;
        }
        else
            return false;
    }

    public boolean uniqueName(String playerName){

        for(int i=0;i<nonNullPlayerElems();i++){
            if(playerList[i].getName().equals(playerName))
                return false;
        }
        return true;
    }

    public boolean validName(String playerName){
        if (playerName.toCharArray().length > 20 || playerName.startsWith(" ") || playerName.endsWith(" "))
            return false;
        else
            return true;
    }

    // sorting player array by score

    public Player[] sortedPlayerArray() {

        Player[] playerTemp=playerList;

        boolean swapped = true;
        int j = 0;
        Player tmp;
        while (swapped) {
            swapped = false;
            j++;
            for (int i = 0; i < nonNullPlayerElems() - j; i++) {
                if (playerTemp[i].score() < playerTemp[i + 1].score()) {
                    tmp = playerTemp[i];
                    playerTemp[i] = playerTemp[i + 1];
                    playerTemp[i + 1] = tmp;
                    swapped = true;
                }
            }
        }
        return playerTemp;
    }

    public String[] playerListNames(){

        String[] playerNames= new String[nonNullPlayerElems()];

        for(int i=0;i<nonNullPlayerElems();i++){
            playerNames[i]=playerList[i].getName();
        }
        return playerNames;
    }

    public Player[] getPlayerList() {
        return playerList;
    }

    public PerfectPlayer getHal() {
        return hal;
    }

    public MediocrePlayer getMamalakis() {
        return mamalakis;
    }

    public BadPlayer getMrBean() {
        return MrBean;
    }
}
