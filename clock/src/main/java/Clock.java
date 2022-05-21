
public class Clock
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;

    /**
     * Constructor, initaites the clock and sets its time to 00:00.
     */
    public Clock()
    {
        this.hours= new NumberDisplay(0,23);
        this.minutes= new NumberDisplay(0,59);
        updateDisplay();
    }

    /**
     * Second constructor, initiates the clock and sets it time to the parameters hour and minute.
     * @param hour Makes a NumberDisplay with this value to represent an hour.
     * @param minute Makes a NumberDisplay with this value to represent a minute.
     */
    public Clock(int hour, int minute)
    {

        this.hours = new NumberDisplay(0, 23);
        this.minutes = new NumberDisplay(0, 59);
        this.setTime(hour, minute);

    }

    /**
     * Returns clock hours as an integer.
     * @return
     */
    public int returnHours()
    {
        return this.hours.getValue();
    }

    /**
     * Returns clock minutes as an integer.
     * @return
     */
    public int returnMinutes()
    {
        return this.minutes.getValue();
    }

    /**
     * Method to incrase the minute by one, if it triggered didWrapAround the hour will also
     * increase by one.
     */
    public void timeTick ()
    {
        this.minutes.increment();

        if(this.minutes.didWrapAround())
        {
            this.hours.increment();
        }
        updateDisplay();
    }

    /**
     * Method to set the clock to a certain time.
     * @param hour
     * @param minute
     */
    public void setTime(int hour, int minute)
    {
        this.minutes.setValue(minute);
        this.hours.setValue(hour);
        this.updateDisplay();

    }

    /**
     * Method to get the current time as a string.
     * @return current clock time as string.
     */
    public String getTime()
    {
        return this.displayString;
    }

    /**
     * Adds the hour DisplayValue with the minute DisplayValue.
     */
    private void updateDisplay()
    {
        this.displayString = this.hours.getDisplayValue()+":"+
                this.minutes.getDisplayValue();

    }
}