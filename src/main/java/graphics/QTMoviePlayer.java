package graphics;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import quicktime.QTException;
import quicktime.QTSession;
import quicktime.app.view.MoviePlayer;
import quicktime.app.view.QTComponent;
import quicktime.app.view.QTFactory;
import quicktime.io.OpenMovieFile;
import quicktime.io.QTFile;
import quicktime.std.movies.Movie;
import quicktime.std.movies.MovieController;

/** Demonstrate simple code to play a movie with Apple'sQuicktime For Java API
 * @author ian
 */
public class QTMoviePlayer extends JFrame {
	
	final static String moviePath = "/home/ian/movies/whale.mov";
	
	/**
	 * @param args
	 * @throws QTException 
	 */
	public static void main(String[] args) throws Exception {
		new QTMoviePlayer(moviePath).setVisible(true);
	}
	
	public QTMoviePlayer(String moviePath) throws QTException, IOException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());

		File f = new File(moviePath);
		if (!f.exists() || !f.canRead()) {
			throw new IOException("Can't read file " + moviePath);
		}
		
		// Set up QuickTime
	    QTSession.open (QTSession.kInitVR);		
		// Construct QT object representing the movie, several step process.
		QTFile qtFile = new QTFile(moviePath);
		OpenMovieFile mf = OpenMovieFile.asRead(qtFile);
		Movie mov = Movie.fromFile(mf);
		
		// Also the Player and Controller objects
		MoviePlayer moviePlayer = new MoviePlayer(mov);
		MovieController movieController = new MovieController(mov);

		// create and add the QT JFC Component
		// XXX Temporarily using AWT component.
		QTComponent controllerComponent = QTFactory.makeQTComponent(movieController);		
		contentPane.add((Component)controllerComponent, BorderLayout.CENTER);
		
		pack();
	}

}
