package BackEnd;

import GUI.GUI;
import GUI.MazeGUI;

import java.io.FileNotFoundException;
import java.util.Random;

/**
 * Class for mazes
 */

public class Maze {
    private int height;
    private int length;
    private Field[] [] fields;
    private boolean [] [] availableFields;
    private MazeGUI mazeGUI;

    public Maze() throws FileNotFoundException {
        height=0;
        length=0;
        fields=new Field[0][0];
        availableFields= new boolean[0][0];
        mazeGUI=new MazeGUI(0, 0);
    }

    /**
     * makes a new maze and updates date for it
     */

    public void update(){
        generate();
        double coorXbegin=(1365-length* GUI.getFieldSize())/2;
        double coorYbegin=68+(500-height* GUI.getFieldSize())/2;
        mazeGUI.update(coorXbegin, coorYbegin, height, length, getFieldsType());
    }

    /**
     * generates the new maze
     */

    public void generate(){
        height= new Random().nextInt(4)+4;
        length= new Random().nextInt(9)+6;
        fields = new Field[height] [length];
        availableFields =new boolean[height][length];
        for(int i = 0; i< height; i++){
            for (int j = 0; j< length; j++) {
                fields[i] [j]= new Field();
                availableFields[i][j]=false;
            }
        }
        int i=0;
        int j=0;
        int idx=0;
        while (unavailableFields()!=0){
            while (availableFields[i][j]) {
                idx++;
                if (idx>= height * length ||idx<0) return;
                i=indexToI(idx);
                j=indexToJ(idx);
            }
            if (j==0) {
                if (i!=0) {
                    fields[i-1][0].removeWall(Direction.south);
                    fields[i][0].removeWall(Direction.north);
                }
            } else {
                fields[i][j-1].removeWall(Direction.east);
                fields[i][j].removeWall(Direction.west);
            }
            pathfinder(i, j);
        }
    }

    /**
     * makes "a way" in the maze in making stops if finds a field which has been already reached
     * @param i starting coordinates
     * @param j starting coordinates
     */
    private void pathfinder(int i, int j){
        int step=0;
        boolean stepAllowed;
        while (!availableFields[i][j]){
            stepAllowed=false;
            availableFields[i][j]=true;
            while (!stepAllowed) {
                step = new Random().nextInt(4);
                stepAllowed = (i > 0 || step != 0) && (i != height - 1 || step != 2) && (j > 0 || step != 3) && (j != length - 1 || step != 1);
            }
            switch (step){
                case 0:
                    fields[i][j].removeWall(Direction.north);
                    i--;
                    fields[i][j].removeWall(Direction.south);
                    break;
                case 1:
                    fields[i][j].removeWall(Direction.east);
                    j++;
                    fields[i][j].removeWall(Direction.west);
                    break;
                case 2:
                    fields[i][j].removeWall(Direction.south);
                    i++;
                    fields[i][j].removeWall(Direction.north);
                    break;
                case 3:
                    fields[i][j].removeWall(Direction.west);
                    j--;
                    fields[i][j].removeWall(Direction.east);
                    break;
                default:
                    break;


            }

        }
    }

    /**
     * @returns with the number of the unreached fields
     */

    private int unavailableFields(){
        int db=0;
        for (boolean [] bi: availableFields){
            for (boolean bj: bi) {
                if (!bj) db++;
            }
        }
        return db;
    }

    /**
     * two methods for converting a index to coordinates
     * @param idx
     * @return
     */
    private int indexToI(int idx){
        return idx/ length;
    }

    private int indexToJ(int idx){
        return  idx% length;
    }

    /**
     * returns a field's type
     * @return
     */

    private int [][] getFieldsType(){
        int [][] types= new int[height][length];
        for (int i=0; i<height; i++){
            for (int j=0; j<length; j++){
                types[i][j]=fields[i][j].getType();
            }
        }
        return types;
    }

    /**
     * getters
     * @param i
     * @param j
     * @return
     */

    public Field getField(int i, int j){
        return fields[i][j];
    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }
}