package parking.refactored;

import java.util.*;

public class ParkingService {
    private final ReportFormatter formatter;
    private final ReceiptLog log;
    private final List<String> receipts = new ArrayList<>();
    private double total;

    public ParkingService(ReportFormatter formatter, ReceiptLog log) {
        this.formatter = formatter;
        this.log = log;
    }

    public double fee(Vehicle vehicle, int hours) {
        return vehicle.getTariff().fee(hours);
    }

    public void checkout(Vehicle vehicle, int hours, PaymentMethod method) {
        double amount = fee(vehicle, hours);
        method.pay(amount);
        total += amount;
        String receipt = formatter.receipt(vehicle, hours, amount, method.name());
        receipts.add(receipt);
        log.log(receipt);
    }

    public void cancel(Vehicle vehicle, int hours, RefundablePayment method) {
        double amount = fee(vehicle, hours);
        method.refund(amount);
        total -= amount;
    }

    public void cancelAtDesk(Vehicle vehicle, int hours) {   // cash refunds
        double amount = fee(vehicle, hours);
        System.out.println("Refund " + amount + " MAD at the desk for " + vehicle.getPlate());
        total -= amount;
    }

    public String dailyReport() { return formatter.report(receipts, total); }
}
