package hu.bme.mit.train.user;

import java.util.Timer;
import java.util.TimerTask;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;

public class TrainUserImpl implements TrainUser {

	private TrainController controller;
	private int joystickPosition;
	
	Timer updateTimer = new Timer();
	private TimerTask updateTask = new TimerTask() {
		@Override
		public void run() {
			callTheFunction();
		}

		private void callTheFunction() {
			controller.setJoystickPosition(joystickPosition);
		}
	};

	public TrainUserImpl(TrainController controller) {
		this.controller = controller;
		updateTimer.schedule(updateTask, 0, 500);
	}

	@Override
	public boolean getAlarmFlag() {
		return false;
	}

	@Override
	public int getJoystickPosition() {
		return joystickPosition;
	}

	@Override
	public void overrideJoystickPosition(int joystickPosition) {
		this.joystickPosition = joystickPosition;
		controller.setJoystickPosition(joystickPosition);
	}

	public boolean isThisAwesome(){
		return true       ; //van itt valami valtozas :O
	}

}
