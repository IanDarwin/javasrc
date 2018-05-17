package patterns.behavioral;

/**
 * Model a simple media player
 * Doesn't actually play media yet
 * (use existing Java classes for that)
 */

public class PlayerStateDemo {

	abstract class State {
		abstract void stop();
		abstract void start();
		abstract void pause();
		abstract void rewind();
		void enterState() {
			// Only some states will need this
		}
	}

	State stoppedState = new State() {
		@Override
		void enterState() {
			stop();
			// setIcon(Icon.stopped);
		}
		@Override
		void stop() {
			// Do nothing
		}

		@Override
		void start() {
			
		}

		@Override
		void pause() {
			// Do nothing
		}

		@Override
		void rewind() {
			resetToStart();
		}
	};
	State playingState = new State() {

		void enterState() {
			start();
			// setIcon(Icon.PLAYING);
		}
		
		@Override
		void stop() {
			currentState = stoppedState;
			currentState.enterState();
		}

		@Override
		void start() {
			// Do nothing
		}

		@Override
		void pause() {
			stop();
		}

		@Override
		void rewind() {
			stop();
			resetToStart();
			start();
		}
	};

	State pausedState = new State() {

		@Override
		void stop() {
			currentState = stoppedState;
			currentState.enterState();
		}

		@Override
		void start() {
			currentState = playingState;
			currentState.enterState();
		}

		@Override
		void pause() {
			// Do nothing
		}

		@Override
		void rewind() {
			resetToStart();
		}
	};
	State rewindState = new State() {

		@Override
		void stop() {
			currentState = stoppedState;
			currentState.enterState();
		}

		@Override
		void start() {
			// On mechanical transports we have to stop before going into play mode
			stop();
			currentState = playingState;
			currentState.enterState();
		}

		@Override
		void pause() {
			currentState = pausedState;
			currentState.enterState();
		}

		@Override
		void rewind() {
			// Do nothing
		}
		
		void enterState() {
			rewind();
		}
	};

	State currentState = stoppedState;

	// This section shows the legacy, non-patterns way of implementing
	// one of the four methods.
	enum StateName { STOPPED, PLAYING, PAUSED, REWINDING }
	StateName currentStateName;
	public void unmaintainableStart() {
		if (currentStateName == StateName.STOPPED) {
			currentStateName = StateName.PLAYING;
			startPlay();
		} else if (currentStateName == StateName.PAUSED) {
			currentStateName = StateName.PLAYING;
			resumePlay();
		} else if (currentStateName == StateName.PLAYING) {
			System.out.println("Already playing!");
		} else if (currentStateName == StateName.REWINDING) {
			System.out.println("Wait a while, OK?");
		}
	}

	// Low level, hardware control
	void startPlay() {}
	void resumePlay() {}
	void resetToStart() {}
}
