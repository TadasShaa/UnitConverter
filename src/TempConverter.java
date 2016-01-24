/**
 * Created by tds on 10/5/15.
 */
public class TempConverter {

    public double celsiusToFahrenheit(double t) {
        return  ((t * 9 / 5.0) + 32);

    }

    public double fahrenheitToCelsius(double t) {
        return ((t - 32) * (5 / 9.0));
    }
}
