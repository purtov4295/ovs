import java.util.ArrayList;
import java.util.stream.IntStream;

public class DomainsClients {
    public static final Integer amountDomains = 3;
    public static ArrayList<Domain> domains = new ArrayList<Domain>();

    public static boolean[] valid = new boolean[amountDomains];

    public static void AddDomains(Integer amount) {
        IntStream.range(0, amount).forEachOrdered(i -> {
            domains.add(new Domain("localhost", 6000 + i));
        });
    }
}
