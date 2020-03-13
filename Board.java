import javax.swing.*;
import java.awt.*;
public class Board {
    /**
     * Initliaises and encapsualates the instance variables
     * Encapsulation prevents the variables from being accessed outside the class
     */
    private JFrame guiWindow = new JFrame();
    private JPanel guiPanel = new JPanel();
    private GridLayout hopperBoard = new GridLayout(5,5);
    private Square[][] arraySqaure = new Square[5][5];
    
    //Sets the GUI & playspace for the board 
    public Board(){
        guiWindow.setTitle("Hoppers Jumping Game");
        guiWindow.setSize(750, 750);
        guiWindow.setContentPane(guiPanel);
        guiWindow.setVisible(true);
        guiWindow.setResizable(false);
        guiPanel.setLayout(hopperBoard);
        guiWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LevelConfig levels = new LevelConfig();
        drawBoard(levels.getLevel(1));
    }

    /**
     * drawBoard method takes a 2D array from the LevelConfig class
     * and then comapres the index's of the array against conditions 
     * to draw the level 
     */

/*
    Square getFileNames = new Square(null, 0, 0);
    String[] fileNames = getFileNames.getFileArray();

*/

    public void drawBoard(int levelArray [][]){
        for (int i = 0; i< 5; i++){
            for(int j =0; j<5; j++){      
            int arrayIndex = levelArray[i][j];
            arraySqaure[i][j] = new Square(arrayIndex,i,j);
            guiPanel.add(arraySqaure[i][j].getButton()); 
        }
        guiWindow.setVisible(true);
    }

    }
}

