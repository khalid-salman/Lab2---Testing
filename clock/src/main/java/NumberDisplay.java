/**
 Class for a 2 digit number display.
 */
public class NumberDisplay
{
    private int MinLimit;
    private int MaxLimit;
    private int Value;

    /**
     * Constructor, initiates the number display.
     * @param minLimit the lowest number in the display.
     * @param maxLimit the highest number in the display.
     * @throws IllegalArgumentException if minLimit is higher than maxLimit.
     */
    public NumberDisplay(int minLimit, int maxLimit) throws IllegalArgumentException
    {
        this.Value=minLimit;
        this.MinLimit=minLimit;
        this.MaxLimit=maxLimit;

        if(maxLimit<minLimit)
        {
            throw new IllegalArgumentException("maxLimit is lower than minLimit");
        }

    }

    /**
     * Method to get the current display value.
     * @return display value.
     */
    public int getValue()
    {
        return this.Value;
    }

    /**
     * Method to set the number display to a certain value.
     * @param newValue
     * @throws IllegalArgumentException if the new value is outside of the allowed limit.
     */
    public void setValue( int newValue) throws IllegalArgumentException
    {

        if(newValue<this.MinLimit || newValue>this.MaxLimit)
        {
            throw new IllegalArgumentException("Time is outside of limits!");
        }

        this.Value=newValue;


    }

    /**
     * Method to get the display value as a string.
     * @return display value as a string.
     */
    public String getDisplayValue()
    {
        String numberZero = "0";
        if(this.Value<10)
        {
            return numberZero+Integer.toString(this.Value);
        }

        return Integer.toString(this.Value);
    }

    /**
     * Method to inrease the current value by one, should the value reach above the highest value
     * it will be set to the lowest allowed value.
     */
    public void increment()
    {
        this.Value++;

        if(this.Value==this.MaxLimit+1)
        {
            this.Value=this.MinLimit;
        }
    }

    /**
     * Method to see if increment set the current value to minLimit.
     * @return
     */
    public boolean didWrapAround()
    {
        if(this.Value==this.MinLimit)
        {
            return true;
        }

        return false;
    }



}
