package parking.refactored;

public class MotorbikeTariff implements Tariff {
    public double fee(int hours) { return hours * 2.0; }
}

