import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClockTest
{
    private Clock clockTestObject;
    private int hour;
    private int minute;


    @Test
    public void createClockTest() throws Exception
    {
        clockTestObject = new Clock();
    }

    @Test
    public void clockWithTimeTest() throws Exception
    {
        clockTestObject = new Clock(5, 30);
        clockTestObject = new Clock(0,0);
        clockTestObject = new Clock(0,59);
    }

    @Test(expected = IllegalArgumentException.class)
    public void clockCreateFails() throws Exception
    {
        clockTestObject = new Clock(25,19);

    }

    @Test
    public void getTimeTest()
    {
        clockTestObject = new Clock(10, 40);
        assertEquals("10:40", clockTestObject.getTime());
    }

    @Test
    public void getHoursAndMinutesTest()
    {
        hour = 5;
        minute = 9;

        clockTestObject = new Clock(hour, minute);
        assertEquals(clockTestObject.returnHours(), hour);
        assertEquals(clockTestObject.returnMinutes(), minute);

    }

    @Test
    public void setTimeTest()
    {
        hour = 15;
        minute = 39;

        clockTestObject = new Clock();
        clockTestObject.setTime(hour, minute);
        assertEquals(clockTestObject.returnHours(), hour);
        assertEquals(clockTestObject.returnMinutes(), minute);
    }

    @Test
    public void timeTickTest()
    {
        hour = 1;
        minute = 58;
        clockTestObject = new Clock(hour,minute);
        clockTestObject.timeTick();

        assertEquals(clockTestObject.returnMinutes(),minute+1);

        clockTestObject.timeTick();

        assertEquals(clockTestObject.returnHours(), hour+1);
    }

}