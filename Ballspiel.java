package baelle;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Steuerungsklasse für eine Ball-Animation
 * @author Doro
 *
 */
public class Ballspiel {
	private BallFrame ballFrame;
	private List<Ball> ballList = new LinkedList<>();
	private ThreadGroup ballThreadGroup = new ThreadGroup("Ballthreads");
	/**
	 * erstellt die Steuerungsklasse für die angegebene Oberfläche
	 * @param f
	 */
	public Ballspiel(BallFrame f)
	{
		this.ballFrame = f;
	}
	
	/**
	 * startet einen Ball auf der Oberfläche und lässt ihn hüpfen
	 */
	public void ballStarten()
	{

		Random r = new Random();
		int dauer = r.nextInt(500) + 1000; //Zufallszahl zwischen 1000 und 1500
		Ball b = new Ball(ballFrame.getZeichenflaeche(), dauer);
		ballList.add(b);
		Thread bThread = new Thread(ballThreadGroup,b);
		bThread.start();
	}
	
	/**
	 * hält alle Bälle auf der Oberfläche an, sodass sie an ihrer aktuellen Position
	 * stehen bleiben
	 */
	public void baelleStoppen() {
		ballList.forEach(Ball::anhalten);
	}
	/**
	 * lässt alle angehaltenen Bälle wieder weiter hüpfen
	 */
	public void baelleWeiter() {
		ballList.forEach(Ball::weiter);
	}

	/**
	 * löscht alle Bälle von der Oberfläche
	 */
	public void alleLoeschen() {
		ballThreadGroup.interrupt();
		ballList.clear();

	}
}




