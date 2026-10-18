package parking.refactored;
import java.util.List;

public interface ReportFormatter {
    String receipt(Vehicle v, int hours, double amount, String methodName);
    String report(List<String> receipts, double total);
}
