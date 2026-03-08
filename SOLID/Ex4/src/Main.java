import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");
        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));

         List<PricingComponent> components = List.of(
                new RoomPricing(),
                new AddOnPricing()
        );

        HostelFeeCalculator calc = new HostelFeeCalculator(components,new FakeBookingRepo());
        calc.process(req);
    } 
}
