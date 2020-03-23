package hu.bme.mit.train.sensor;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

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
    public void testSetControllerSpeedLimit() {
        underTest.overrideSpeedLimit(20);
        verify(mockController).setSpeedLimit(20);
    }

    @Test
    public void testSpeedLimitBelowZero() {
        underTest.overrideSpeedLimit(-2);
        verify(mockTrainUser).setAlarmState(true);
    }

    @Test
    public void testSpeedLimitOver500() {
        underTest.overrideSpeedLimit(600);
        verify(mockTrainUser).setAlarmState(true);
    }

    @Test
    public void testSpeedLimitLessThenHalf() {
        when(mockController.getReferenceSpeed()).thenReturn(150);
        underTest.overrideSpeedLimit(50);

        verify(mockTrainUser).setAlarmState(true);
    }

    @Test
    public void ThisIsAnExampleTestStub() {
        Tachograph tachograph = new Tachograph();

        tachograph.addDataElement(1, 1, 1);
        tachograph.addDataElement(2, 1, 15);
        tachograph.addDataElement(1, 4, 16);

        assertEquals(1, tachograph.getElement(1, 1));
        assertEquals(16, tachograph.getElement(1, 4));
    }


}
