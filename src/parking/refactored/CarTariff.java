package parking.refactored;

public class CarTariff implements Tariff {
    public double fee(int hours) { return hours * 5.0; }
}

