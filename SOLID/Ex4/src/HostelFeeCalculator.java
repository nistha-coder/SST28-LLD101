import java.util.*;

public class HostelFeeCalculator {
    private final List<PricingComponent> components;
    private final BookingRepository repo;

    public HostelFeeCalculator(List<PricingComponent> components,
            BookingRepository repo) {

        this.components = components;
        this.repo = repo;
        
    }
    

    // OCP violation: switch + add-on branching + printing + persistence.
    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000)); // deterministic-ish
        repo.save(bookingId, req, monthly, deposit);
    } 

    private Money calculateMonthly(BookingRequest req) {
        Money monthly = new Money(0.0);

        for (PricingComponent c : components) {
            monthly = monthly.plus(c.monthlyCharge(req));
        }
        return monthly;
    } 
}
