import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class GameSound1 extends Thread {
	
	private URL u1;
	private AudioClip s1;

	public GameSound1(String path) {
		try {
			u1 = this.getClass().getResource("/sound/"+path+".wav");
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
        s1 = Applet.newAudioClip(u1);
		  
	}
	public void playSound() {
        s1.play();
    }
    public void playLoop() {
        s1.loop();
    }
    public void stopSound() {
        s1.stop();
    }

}
