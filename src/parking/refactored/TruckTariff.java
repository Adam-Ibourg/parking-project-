package parking.refactored;

public class TruckTariff implements Tariff {
    public double fee(int hours) { return 20 + hours * 10.0; }
}
