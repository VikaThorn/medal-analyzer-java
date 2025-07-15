import org.junit.jupiter.api.Test;
import analyzer.MedalAnalyzer;
import entity.Medal;
import enums.Country;
import enums.KindOfSport;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MedalAnalyzerMockDataTest {

    @Test
    public void testAthleteWithTheMostMedalsFromMockedList() {
        List<Medal> fakeMedals = List.of(
                new Medal(Country.USA, KindOfSport.SWIMMING, "Alice", 1),
                new Medal(Country.USA, KindOfSport.ATHLETICS, "Bob", 2),
                new Medal(Country.CHN, KindOfSport.ATHLETICS, "Alice", 3)
        );
        MedalAnalyzer analyzer = new MedalAnalyzer();
        List<String> result = analyzer.athleteWithTheMostMedals(fakeMedals);

        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0));
    }
}
