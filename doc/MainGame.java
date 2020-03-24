import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;

/**
 * MainGame (Driver)
 *  
 * @author Adam Boudjemaa
 * @version 1.0
 * @since 1.0
 * 
 */
public class MainGame{
    private static LevelConfig levels = new LevelConfig();
    private static JFrame winMessage;
    
   /**
    * Plays background music and sound fx for the frogs
    * @param soundName The file path to the audio 
    */
    public static void playSound(final String soundName) {
        try {
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            final Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(-1);

        } catch (Exception ex) {
            System.out.println("ERROR: COULD NOT PLAY SOUND !!!");
            ex.printStackTrace();
        }
    }

    public static void main(final String[] array) {
        
        boolean done = false;
       

        int numberOfLevels = levels.levelLength() - 1;
        


        while (!done) {
            try {
                JFrame inputLevel = new JFrame();         
                String level = JOptionPane.showInputDialog(inputLevel, "Enter Level Number To Start From 1 - " + numberOfLevels + "\nTHIS GAME WAS MADE BY ADAM :)");
                if (level == null) {
                    System.exit(0);
                }
                else {
                    int a = Integer.parseInt(level);
                    int b = levels.levelLength() - 1;
    
                if (a > b || a <= 0) {           
                    winMessage = new JFrame();
                    JOptionPane.showMessageDialog(winMessage, "Invalid level number");        
                }

                else {
                    done = true;
                    new Board(a);
                    playSound("resources/sounds/background2.wav");
                }
            }

            } catch (Exception e) {
                winMessage = new JFrame();
                JOptionPane.showMessageDialog(winMessage, "Plese enter an integer");           
            }  
        }                  
    }




}

