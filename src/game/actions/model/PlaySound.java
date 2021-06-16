package game.actions.model;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class PlaySound {

	public static String shoot = "";
	public static String hit = "";
	public static String noBullet = "";
	
	public enum SOUNDS {
		SHOOT,
		HIT, 
		NO_BULLET 
	}
	
	public static void main(String args[]) {
		new PlaySound().playAudio("/home/nathy/eclipse-workspace/duck_simulation_game/src/game/actions/res/music/GunShotSnglShotIn PE1097906.mp3");
	}
	
	boolean playCompleted;
	public void playAudio(String audioFilePath ) {
		File audioFile = new File(audioFilePath);
		 
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
 
            AudioFormat format = audioStream.getFormat();
 
            DataLine.Info info = new DataLine.Info(Clip.class, format);
 
            Clip audioClip = (Clip) AudioSystem.getLine(info);
 
//            audioClip.addLineListener(this);
 
            audioClip.open(audioStream);
             
            audioClip.start();
             
            while (!playCompleted) {
                // wait for the playback completes
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
             
            audioClip.close();
             
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }
	}
	
	
}
//import java.io.File;
//import java.io.IOException;
// 
//import javax.sound.sampled.AudioFormat;
//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.Clip;
//import javax.sound.sampled.DataLine;
//import javax.sound.sampled.LineEvent;
//import javax.sound.sampled.LineListener;
//import javax.sound.sampled.LineUnavailableException;
//import javax.sound.sampled.UnsupportedAudioFileException;
 
/**
 * This is an example program that demonstrates how to play back an audio file
 * using the Clip in Java Sound API.
 * @author www.codejava.net
 *
 */
//public class AudioPlayerExample1 implements LineListener {
//     
//    /**
//     * this flag indicates whether the playback completes or not.
//     */
//    boolean playCompleted;
//     
//    /**
//     * Play a given audio file.
//     * @param audioFilePath Path of the audio file.
//     */
//    void play(String audioFilePath) {
//        File audioFile = new File(audioFilePath);
// 
//        try {
//            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
// 
//            AudioFormat format = audioStream.getFormat();
// 
//            DataLine.Info info = new DataLine.Info(Clip.class, format);
// 
//            Clip audioClip = (Clip) AudioSystem.getLine(info);
// 
//            audioClip.addLineListener(this);
// 
//            audioClip.open(audioStream);
//             
//            audioClip.start();
//             
//            while (!playCompleted) {
//                // wait for the playback completes
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//            }
//             
//            audioClip.close();
//             
//        } catch (UnsupportedAudioFileException ex) {
//            System.out.println("The specified audio file is not supported.");
//            ex.printStackTrace();
//        } catch (LineUnavailableException ex) {
//            System.out.println("Audio line for playing back is unavailable.");
//            ex.printStackTrace();
//        } catch (IOException ex) {
//            System.out.println("Error playing the audio file.");
//            ex.printStackTrace();
//        }
//         
//    }
//     
//    /**
//     * Listens to the START and STOP events of the audio line.
//     */
//    @Override
//    public void update(LineEvent event) {
//        LineEvent.Type type = event.getType();
//         
//        if (type == LineEvent.Type.START) {
//            System.out.println("Playback started.");
//             
//        } else if (type == LineEvent.Type.STOP) {
//            playCompleted = true;
//            System.out.println("Playback completed.");
//        }
// 
//    }
// 
//    public static void main(String[] args) {
//        String audioFilePath = "E:/Test/Audio.wav";
//        AudioPlayerExample1 player = new AudioPlayerExample1();
//        player.play(audioFilePath);
//    }
// 
//}
//
//

/*// Java program to play an Audio
// file using Clip Object
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
  
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
  
public class SimpleAudioPlayer 
{
  
    // to store current position
    Long currentFrame;
    Clip clip;
      
    // current status of clip
    String status;
      
    AudioInputStream audioInputStream;
    static String filePath;
  
    // constructor to initialize streams and clip
    public SimpleAudioPlayer()
        throws UnsupportedAudioFileException,
        IOException, LineUnavailableException 
    {
        // create AudioInputStream object
        audioInputStream = 
                AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
          
        // create clip reference
        clip = AudioSystem.getClip();
          
        // open audioInputStream to the clip
        clip.open(audioInputStream);
          
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
  
    public static void main(String[] args) 
    {
        try
        {
            filePath = "Your path for the file";
            SimpleAudioPlayer audioPlayer = 
                            new SimpleAudioPlayer();
              
            audioPlayer.play();
            Scanner sc = new Scanner(System.in);
              
            while (true)
            {
                System.out.println("1. pause");
                System.out.println("2. resume");
                System.out.println("3. restart");
                System.out.println("4. stop");
                System.out.println("5. Jump to specific time");
                int c = sc.nextInt();
                audioPlayer.gotoChoice(c);
                if (c == 4)
                break;
            }
            sc.close();
        } 
          
        catch (Exception ex) 
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
          
          }
    }
      
    // Work as the user enters his choice
      
    private void gotoChoice(int c)
            throws IOException, LineUnavailableException, UnsupportedAudioFileException 
    {
        switch (c) 
        {
            case 1:
                pause();
                break;
            case 2:
                resumeAudio();
                break;
            case 3:
                restart();
                break;
            case 4:
                stop();
                break;
            case 5:
                System.out.println("Enter time (" + 0 + 
                ", " + clip.getMicrosecondLength() + ")");
                Scanner sc = new Scanner(System.in);
                long c1 = sc.nextLong();
                jump(c1);
                break;
      
        }
      
    }
      
    // Method to play the audio
    public void play() 
    {
        //start the clip
        clip.start();
          
        status = "play";
    }
      
    // Method to pause the audio
    public void pause() 
    {
        if (status.equals("paused")) 
        {
            System.out.println("audio is already paused");
            return;
        }
        this.currentFrame = 
        this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }
      
    // Method to resume the audio
    public void resumeAudio() throws UnsupportedAudioFileException,
                                IOException, LineUnavailableException 
    {
        if (status.equals("play")) 
        {
            System.out.println("Audio is already "+
            "being played");
            return;
        }
        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }
      
    // Method to restart the audio
    public void restart() throws IOException, LineUnavailableException,
                                            UnsupportedAudioFileException 
    {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }
      
    // Method to stop the audio
    public void stop() throws UnsupportedAudioFileException,
    IOException, LineUnavailableException 
    {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }
      
    // Method to jump over a specific part
    public void jump(long c) throws UnsupportedAudioFileException, IOException,
                                                        LineUnavailableException 
    {
        if (c > 0 && c < clip.getMicrosecondLength()) 
        {
            clip.stop();
            clip.close();
            resetAudioStream();
            currentFrame = c;
            clip.setMicrosecondPosition(c);
            this.play();
        }
    }
      
    // Method to reset audio stream
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
                                            LineUnavailableException 
    {
        audioInputStream = AudioSystem.getAudioInputStream(
        new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
  
}*/