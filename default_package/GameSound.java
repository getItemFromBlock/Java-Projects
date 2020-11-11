import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class GameSound extends Thread {
	
	private URL u1;
	private AudioClip s1;

	
	/*
	 * Constructeur de la classe, permet de d�clarer et de charger un sons � partir
	 * de l'emplacement pr�cis� par le programme.
	 * Si aucun sons n'est trouv� � l'emplacement indiqu�, le programme s'arr�te et
	 * une erreur est affich�e.
	 * Cette fonction doit �tre appel�e avant de pouvoir jouer le son.
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
    // Arr�te de jouer le son
    public void stopSound() {
        s1.stop();
    }

}
