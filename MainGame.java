import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;

public class MainGame{
    private JFrame inputLevel;
    private static LevelConfig levels = new LevelConfig();
    private static JFrame winMessage;

    public static void playSound(final String pathname) {
        try {
            final AudioInputStream audioInputStream = AudioSystem
                    .getAudioInputStream(new File(pathname).getAbsoluteFile());
            final Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(-1);

        } catch (Exception ex) {
            System.out.println("ERROR: COULD NOT PLAY SOUND !!!");
            ex.printStackTrace();
        }
    }

    public static int inputBox() {
        JFrame inputLevel = new JFrame();
        String level = JOptionPane.showInputDialog(inputLevel, "Enter Level");
        int a = Integer.parseInt(level);
        return a;
    }

    public static void main(final String[] array) {
        
        boolean done = false;
        
        while (!done) {
            try {
                JFrame inputLevel = new JFrame();         
                String level = JOptionPane.showInputDialog(inputLevel, "Enter Level Number \nStart From Level 1 To Enable 'Time Mode'");    
                int a = Integer.parseInt(level);
                int b = levels.levelLength() - 1;

            if (a > b || a <= 0){           
                winMessage = new JFrame();
                JOptionPane.showMessageDialog(winMessage, "Invalid Level");        
            }

            // else if(level == null){
            //     System.exit(0);
            //     JOptionPane.CANCEL
            //  }

            else {
                done = true;
                new Board(a);
                playSound("resources/sounds/background2.wav");
            }
                
            } catch (Exception e) {
                System.out.println("Invalid input error :(");
                      
            }  
        }                 
    }
}

