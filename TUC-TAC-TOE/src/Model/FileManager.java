package Model;

import java.io.*;

public class FileManager implements Serializable {

    public static String fileName="tuctactoe.ser";

    // saving player roaster

    public static void saveFile(String fileName,PlayerRoaster playerRoaster){

        File file=new File(fileName);

        try(ObjectOutputStream OOS=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file),100000000))){

            for(int i=0;i<playerRoaster.nonNullPlayerElems();i++){

                OOS.writeObject(playerRoaster.getPlayer(i));
            }
        } catch (IOException e) {}
    }

    // loading and initializing player roaster

    public static void loadFile(String fileName,PlayerRoaster playerRoaster){

        File file=new File(fileName);

        try(ObjectInputStream OIS=new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))){

            int index=0;
            while (true){

                if(index==playerRoaster.nonNullPlayerElems())
                    playerRoaster.incrementedPlayerArray();
                playerRoaster.getPlayerList()[index]=(Player) OIS.readObject();
                index++;
            }
        } catch (IOException | ClassNotFoundException e) {}
    }
}