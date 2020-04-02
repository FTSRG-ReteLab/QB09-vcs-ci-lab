package hu.bme.mit.train.sensor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

public class TrainSensorTest {

    @Mock
    TrainController mockController;

    @Mock
    TrainUser mockTrainUser;

    TrainSensor underTest;

    @Before
    public void before() {
        initMocks(this);

        underTest = new TrainSensorImpl(mockController, mockTrainUser);
    }

    @Test
    public void testSetAnySpeedlimit() {
        underTest.overrideSpeedLimit(21);
        verify(mockController).setSpeedLimit(21);
    }

    @Test
    public void testNegativeSpeedlimit() {
        underTest.overrideSpeedLimit(-69);
        verify(mockTrainUser).setAlarmState(true);
    }

    @Test
    public void testSetAeroplaneSpeed() {
        underTest.overrideSpeedLimit(690);
        verify(mockTrainUser).setAlarmState(true);
    }

    @Test
    public void testSetAwfullyLowSpeedlimit() {
        when(mockController.getReferenceSpeed()).thenReturn(200);
        underTest.overrideSpeedLimit(99);

        verify(mockTrainUser).setAlarmState(true);
    }
}
