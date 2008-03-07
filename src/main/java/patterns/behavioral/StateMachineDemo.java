package patterns.behavioral;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** A simple demo of using a State machine implemented as Java classes.
 * Not intended to be a full RPG game, sorry. 
 * This is NOT the only way to implement state machines;
 * they have been done for years in many languages as
 * e.g., transition tables of integers.
 */
public class StateMachineDemo {

	enum Command { LOOK, ENTER, EXIT, QUIT }

	abstract class State {
		public abstract void lookAround();
		public abstract void goInside();
		public abstract void goOutside();
		public void quitGame() {
			// In this trivial game it makes sense to allow exit
			// from any state, so allow that here.
			// In a real game, should not quit without e.g., 
			// prompting the user if they're holding any valuables.
			System.exit(0);
		}
	}

	public State inHallwayState = new State() {
		public void lookAround() {
			display("There is a door here");
		}
		public void goInside() {
			display("You are in a room");
			state = inRoomState;
		}
		public void goOutside() {
			display("You are already in the hallway");
		}
	};

	public State inRoomState = new State() {
		public void lookAround() {
			display("The room is full of gold");
		}
		public void goInside() {
			display("You are already in the room");
		}
		public void goOutside() {
			display("You are in a hallway");
			state = inHallwayState;
		}
	};

	public void play(String line) {
		Command c = Command.valueOf(line.toUpperCase());
		switch(c) {
		case LOOK: state.lookAround(); break;
		case ENTER: state.goInside(); break;
		case EXIT: state.goOutside(); break;
		case QUIT: state.quitGame(); break;
		}
	}

	public void display(String mesg) {
		System.out.println(mesg);
	}

	private State state = inHallwayState;

	public void play() throws IOException {
		BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
		String line;

		while ((line = is.readLine()) != null) {
			play(line);
		}
	}

	public static void main(String[] args) throws IOException {
		new StateMachineDemo().play();
	}

}
