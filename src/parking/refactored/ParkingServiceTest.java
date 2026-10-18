package parking.refactored;

import java.util.*;

public class ParkingServiceTest {
    public static void main(String[] args) {
        List<String> logged = new ArrayList<>();
        ReceiptLog fakeLog = logged::add;      // in-memory test double, no file
        ParkingService s = new ParkingService(new TextReportFormatter(), fakeLog);

        Vehicle car   = new Vehicle("12345-A-6", "CAR", new CarTariff());
        Vehicle bike  = new Vehicle("67890-B-1", "MOTORBIKE", new MotorbikeTariff());
        Vehicle truck = new Vehicle("24680-D-8", "TRUCK", new TruckTariff());
        RefundablePayment card = new CardPayment("4242");

        check("car fee", 15.0, s.fee(car, 3));
        check("bike fee", 4.0, s.fee(bike, 2));
        check("truck fee", 60.0, s.fee(truck, 4));

        s.checkout(car, 3, card);
        s.checkout(bike, 2, new CashPayment());
        s.checkout(truck, 4, card);
        s.cancelAtDesk(bike, 2);
        s.cancel(truck, 4, card);

        check("receipts logged", 3, logged.size());
        check("report total", true, s.dailyReport().endsWith("Total: 15.0 MAD"));
    }

    private static void check(String label, Object expected, Object actual) {
        System.out.println((expected.equals(actual) ? "OK   " : "FAIL ")
                + label + " -> " + actual);
    }
}
