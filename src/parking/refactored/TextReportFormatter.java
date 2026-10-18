package parking.refactored;

public class TextReportFormatter implements ReportFormatter {
    public String receipt(Vehicle v, int hours, double amount, String methodName) {
        return v.getPlate() + " | " + v.getType() + " | "
                + hours + " h | " + amount + " MAD | " + methodName;
    }
    public String report(List<String> receipts, double total) {
        StringBuilder sb = new StringBuilder("=== Daily report ===\n");
        for (String r : receipts) sb.append(r).append("\n");
        sb.append("Total: ").append(total).append(" MAD");
        return sb.toString();
    }
}
