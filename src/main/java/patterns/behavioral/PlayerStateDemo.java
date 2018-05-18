package patterns.behavioral;

interface State {
	abstract void stop();
	abstract void start();
	abstract void pause();
	abstract void rewind();
	default void enterState() {
		// Only some states will need this
	}
}

/**
 * Model a simple media player
 * Doesn't actually play media yet
 * (use existing Java classes for that)
 */
public class PlayerStateDemo implements State {

	public void enterState() {
		currentState.enterState();
	}
	public void stop() {
		currentState.stop();
	}

	public void start() {
		currentState.start();
	}

	@Override
	public void pause() {
		currentState.pause();
	}

	@Override
	public void rewind() {
		currentState.rewind();
	}

	State stoppedState = new State() {
		@Override
		public void enterState() {
			stop();
			// setIcon(Icon.stopped);
		}
		@Override
		public void stop() {
			// Do nothing
		}

		@Override
		public void start() {
			
		}

		@Override
		public void pause() {
			// Do nothing
		}

		@Override
		public void rewind() {
			resetToStart();
		}
	};

	State playingState = new State() {

		public void enterState() {
			start();
			// setIcon(Icon.PLAYING);
		}
		
		@Override
		public void stop() {
			currentState = stoppedState;
			currentState.enterState();
		}

		@Override
		public void start() {
			// Do nothing
		}

		@Override
		public void pause() {
			stop();
		}

		@Override
		public void rewind() {
			stop();
			resetToStart();
			start();
		}
	};

	State pausedState = new State() {

		@Override
		public void stop() {
			currentState = stoppedState;
			currentState.enterState();
		}

		@Override
		public void start() {
			currentState = playingState;
			currentState.enterState();
		}

		@Override
		public void pause() {
			// Do nothing
		}

		@Override
		public void rewind() {
			resetToStart();
		}
	};

	State rewindState = new State() {

		@Override
		public void stop() {
			currentState = stoppedState;
			currentState.enterState();
		}

		@Override
		public void start() {
			// On mechanical transports we have to stop before going into play mode
			stop();
			currentState = playingState;
			currentState.enterState();
		}

		@Override
		public void pause() {
			currentState = pausedState;
			currentState.enterState();
		}

		@Override
		public void rewind() {
			// Do nothing
		}
		
		public void enterState() {
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
