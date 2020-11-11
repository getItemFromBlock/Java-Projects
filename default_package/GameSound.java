import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class GameSound extends Thread {
	
	private URL u1;
	private AudioClip s1;

	
	/*
	 * Constructeur de la classe, permet de déclarer et de charger un sons à partir
	 * de l'emplacement précisé par le programme.
	 * Si aucun sons n'est trouvé à l'emplacement indiqué, le programme s'arrête et
	 * une erreur est affichée.
	 * Cette fonction doit être appelée avant de pouvoir jouer le son.
	 */
	public GameSound(String path) {
		try {
			u1 = this.getClass().getResource("/sound/"+path+".wav");
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
        s1 = Applet.newAudioClip(u1);
		  
	}
	// Joue une fois le son
	public void playSound() {
        s1.play();
    }
	// Joue en boucle le son
    public void playLoop() {
        s1.loop();
    }
    // Arrête de jouer le son
    public void stopSound() {
        s1.stop();
    }

}
