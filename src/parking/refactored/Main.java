package parking.refactored;

public class Main {
    public static void main(String[] args) {
        ParkingService garage = new ParkingService(
                new TextReportFormatter(), new FileReceiptLog("parking.log"));

        Vehicle car   = new Vehicle("12345-A-6", "CAR", new CarTariff());
        Vehicle bike  = new Vehicle("67890-B-1", "MOTORBIKE", new MotorbikeTariff());
        Vehicle truck = new Vehicle("24680-D-8", "TRUCK", new TruckTariff());

        RefundablePayment card = new CardPayment("4242");
        PaymentMethod cash = new CashPayment();

        garage.checkout(car, 3, card);
        garage.checkout(bike, 2, cash);
        garage.checkout(truck, 4, card);

        garage.cancelAtDesk(bike, 2);       // cash: refunded by a person
        garage.cancel(truck, 4, card);

        System.out.println(garage.dailyReport());
    }
}
