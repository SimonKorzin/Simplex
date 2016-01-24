package logic;

/**
 * Created by user on 15-Dec-15.
 */
public class DoubleWithValue {
    public double value;

    public DoubleWithValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
