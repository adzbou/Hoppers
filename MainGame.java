import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;

public class MainGame
{
    public static void playSound(final String pathname) {
        try {
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(pathname).getAbsoluteFile());
            final Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(5);
        }
        catch (Exception ex) {
            System.out.println("ERROR: COULD NOT PLAY SOUND !!!");
            ex.printStackTrace();
        }
    }
    
    public static void main(final String[] array) {
        playSound("resources/sounds/background1.wav");
        new Board(0);
    }
}
