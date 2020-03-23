package hu.bme.mit.train.user;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;
import java.util.Timer;
import java.util.TimerTask;

public class TrainUserImpl implements TrainUser {

	private TrainController controller;
	private int joystickPosition;

	private boolean alarmState = false;

	Timer updateTimer = new Timer();
	private TimerTask updateTask = new TimerTask() {
		@Override
		public void run() {
			sendStickPositionToController();
		}
	};

	public TrainUserImpl(TrainController controller) {
		this.controller = controller;
		updateTimer.schedule(updateTask, 0, 1000);
	}

	private void sendStickPositionToController() {
		controller.setJoystickPosition(joystickPosition);
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

	@Override
	public boolean getAlarmState() {
		return alarmState;
	}

	@Override
	public void setAlarmState(boolean alarmState) {
		this.alarmState = alarmState;
	}

	public boolean isThisAwesome(){
		return true       ; //van itt valami valtozas :O
	}

}
