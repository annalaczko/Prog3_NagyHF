package Files;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Class for reading and writing files
 */

public class TankFile {
    private static final int maxUnclosedBattles=5;

    /**
     * sets the filenames so there are in order
     */
    public static void updateFiles(){
        String fileName;
        Path path;
        int gap=0;
        for (int i=0; i<maxUnclosedBattles; i++){
            fileName= "saves\\unclosed" + i + ".txt";
            path= Paths.get(fileName);
            if(!Files.exists(path)){
                gap++;
            }else{
                int newI=i-gap;
                File oldFile=new File(fileName);
                File newFile=new File("saves\\unclosed" + newI + ".txt");
                if(!oldFile.renameTo(newFile)){
                }
            }
        }
    }

    /**
     * saves data
     * @param battleData data
     * @throws IOException
     */
    public static void saveUnclosedBattle(BattleData battleData) throws IOException {
        String fileName;
        Path path;
        for (int i=0; i<maxUnclosedBattles; i++){
            fileName= "saves\\unclosed" + i + ".txt";
            path= Paths.get(fileName);
            if (!Files.exists(path)){
                ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(fileName));
                out.writeObject(battleData);
                out.close();
                return;
            }
        }
    }

    /**
     * loads all unclosed data
     * @return arraylist of the data
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public static ArrayList<BattleData> loadAllUnclosedBattle() throws IOException, ClassNotFoundException {
        ArrayList<BattleData> allUnclosedBattle= new ArrayList<>();
        String fileName;
        Path path;
        for (int i=0; i<maxUnclosedBattles; i++){
            fileName= "saves\\unclosed" + i + ".txt";
            path= Paths.get(fileName);
            if (Files.exists(path)){
                ObjectInputStream in=new ObjectInputStream(new FileInputStream(fileName));
                allUnclosedBattle.add((BattleData) in.readObject());
                in.close();
            }
        }
        return allUnclosedBattle;
    }

    /**
     * loads all closed data
     * @return arrylist for data
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static ArrayList<BattleData> loadClosedBattleData() throws IOException, ClassNotFoundException {
        String fileName= "saves\\closedbattles.txt";
        if (new File(fileName).length()==0){
            return new ArrayList<>();
        }
        ObjectInputStream in=new ObjectInputStream(new FileInputStream(fileName));
        ArrayList<BattleData> ab=(ArrayList<BattleData>) in.readObject();
        in.close();
        return ab;
    }

    /**
     * saves closed data
     * @param closedBattleData
     * @throws IOException
     */

    public static void saveClosedBattleData(ArrayList<BattleData> closedBattleData) throws IOException {
        String fileName= "saves\\closedbattles.txt";
        ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject(closedBattleData);
        out.close();
    }

    /**
     * deletes all the closed battledata
     * @throws FileNotFoundException
     */
    public static void clearClosedBattleData() throws FileNotFoundException {
        String fileName= "saves\\closedbattles.txt";
        PrintWriter writer = new PrintWriter(fileName);
        writer.close();
    }

    /**
     * deletes one unclosed dataa
     * @param idx
     */
    public static void deleteUnclosedBattle(int idx){
        String fileName= "saves\\unclosed" + idx + ".txt";
        File file= new File(fileName);
        if(!file.delete()){
            System.out.println("Deleting file failed");
        }
        updateFiles();
    }

    /**
     * two last methods
     * converts data to string for visual
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public static ArrayList<String> unclosedBattlesToString() throws IOException, ClassNotFoundException {
        ArrayList<BattleData>allUnclosedBattle=TankFile.loadAllUnclosedBattle();
        ArrayList<String> stringArrayList=new ArrayList<>();
        for(BattleData b: allUnclosedBattle){
            stringArrayList.add(b.getNames().get(0)+" - "+b.getNames().get(1)+"       "+b.getPoints()[0]+" - "+b.getPoints()[1]);
        }
        return stringArrayList;
    }

    public static ArrayList<String> closedBattlesToString() throws IOException, ClassNotFoundException {
        ArrayList<BattleData> ALB=loadClosedBattleData();
        ArrayList<String> stringArrayList=new ArrayList<>();
        for (BattleData b: ALB){
            stringArrayList.add(b.getNames().get(0)+" - "+b.getNames().get(1)+"       "+b.getPoints()[0]+" - "+b.getPoints()[1]);
        }
        return stringArrayList;
    }
}