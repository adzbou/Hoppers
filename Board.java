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
    private Square lilyPad;
    private Square water;
    private Square greenFrog;
    private Square redFrog;
    private Square greenFrogSelected;
    private Square redFrogSelected;
    private JButton lilyPadButton;
    private JButton waterButton;
    private JButton greenFrogButton;
    private JButton redFrogButton;

    private int [][] level1 = {
        {1, 0, 3, 0, 1}, 
        {0, 1, 0, 1, 0}, 
        {1, 0, 1, 0, 1}, 
        {0, 1, 0, 1, 0}, 
        {1, 0, 1, 0, 1}, 
    };
    
    //Sets the GUI & playspace for the board 
    public Board(){
    guiWindow.setTitle("Hoppers Jumping Game");
    guiWindow.setSize(750, 750);
    guiWindow.setContentPane(guiPanel);
    guiWindow.setVisible(true);
    guiPanel.setLayout(hopperBoard);
    guiWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    LevelConfig levels = new LevelConfig();
    drawBoard(levels.waterBoard);
    }

    /**
     * drawBoard method takes a 2D array from the LevelConfig class
     * and then comapres the index's of the array against conditions 
     * to draw the level 
     */
    public void drawBoard(int levelArray [][]){
        
        for (int i = 0; i< 5; i++){
            for(int j =0; j<5; j++){
            
            if (levelArray[i][j] == 0){
                lilyPad = new Square("resources/images/LilyPad.png",i,j);
                lilyPadButton = lilyPad.getButton();
                guiPanel.add(lilyPadButton); 
            }
            
            if (levelArray[i][j] == 1){
                water = new Square("resources/images/Water.png",i,j);
                waterButton = water.getButton();
                guiPanel.add(waterButton);
            }

            if (levelArray[i][j] == 2){
                greenFrog = new Square("resources/images/GreenFrog.png",i,j);
                greenFrogButton = greenFrog.getButton();
                guiPanel.add(greenFrogButton);
            }

            if (levelArray[i][j] == 3){
                redFrog = new Square("resources/images/RedFrog.png",i,j);
                redFrogButton = redFrog.getButton();
                guiPanel.add(redFrogButton);
            }
        }

        guiWindow.setVisible(true);
    }

    }
}

