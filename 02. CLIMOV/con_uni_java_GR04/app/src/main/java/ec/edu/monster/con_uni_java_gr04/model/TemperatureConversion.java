package ec.edu.monster.con_uni_java_gr04.model;

public class TemperatureConversion {
    private double value;
    private String fromUnit;
    private String toUnit;

    public TemperatureConversion(double value, String fromUnit, String toUnit) {
        this.value = value;
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
    }

    // Getters y setters
    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
    public String getFromUnit() { return fromUnit; }
    public void setFromUnit(String fromUnit) { this.fromUnit = fromUnit; }
    public String getToUnit() { return toUnit; }
    public void setToUnit(String toUnit) { this.toUnit = toUnit; }
}