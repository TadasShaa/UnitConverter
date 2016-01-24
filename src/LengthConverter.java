/**
 * Created by tds on 9/23/15.
 */
public class LengthConverter {

    private double INC_TO_METER = 0.0254;
    private double FOOT_TO_METER = 0.3048;
    private double MILE_TO_METER = 1609.344;
    private double MM_TO_METER = 0.001;
    private double CM_TO_METER = 0.01;
    private double M_TO_METER = 1;
    private double KM_TO_METER = 1000;
    private double YD_TO_METER = 0.9144;

    private double G_TO_KG = 0.001;
    private double KG_TO_KG = 1;
    private double ST_TO_KG = 6.35029;
    private double P_TO_KG = 0.453592;

    private double factor;

    public LengthConverter(String unit) {

        if (unit.equals("inch")) {
            factor = INC_TO_METER;
        } else if (unit.equals("foot")) {
            factor = FOOT_TO_METER;
        } else if (unit.equals("mile")) {
            factor = MILE_TO_METER;
        } else if (unit.equals("mm")) {
            factor = MM_TO_METER;
        } else if (unit.equals("cm")) {
            factor = CM_TO_METER;
        } else if (unit.equals("m")) {
            factor = M_TO_METER;
        } else if (unit.equals("km")) {
            factor = KM_TO_METER;
        } else if (unit.equals("yard")) {
            factor = YD_TO_METER;
        } else if (unit.equals("g")) {
            factor = G_TO_KG;
        } else if (unit.equals("kg")) {
            factor = KG_TO_KG;
        } else if (unit.equals("stones")) {
            factor = ST_TO_KG;
        } else if (unit.equals("pounds")) {
            factor = P_TO_KG;
        }
    }

    public double toMeters(double measurement) {

        return (measurement * factor);
    }

    public double fromMeters(double measurement) {

        return  (measurement / factor);

    }
}
