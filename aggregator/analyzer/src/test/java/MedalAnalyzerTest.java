import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import entity.Medal;
import enums.Country;
import enums.KindOfSport;
import analyzer.MedalAnalyzer;

import java.util.List;

public class MedalAnalyzerTest {

    @Test
    public void testMedalCreation() {
        Medal medal = new Medal(Country.USA, KindOfSport.ATHLETICS, "Alice", 1);
        assertEquals(Country.USA, medal.getCountry());
        assertEquals(KindOfSport.ATHLETICS, medal.getKindOfSport());
        assertEquals("Alice", medal.getAthlete());
        assertEquals(1, medal.getPlace());
    }

    @Test
    public void testToStringFormat() {
        Medal medal = new Medal(Country.CHN, KindOfSport.SWIMMING, "Li Wei", 2);
        String expected = "CHN;SWIMMING;Li Wei;2";
        assertEquals(expected, medal.toString());
    }

    @Test
    public void testGetGoldMedals_basic() {
        List<Medal> medals = List.of(
                new Medal(Country.USA, KindOfSport.ATHLETICS, "Alice", 1),
                new Medal(Country.USA, KindOfSport.ATHLETICS, "Bob", 2),
                new Medal(Country.CHN, KindOfSport.SWIMMING, "Li Wei", 1)
        );
        MedalAnalyzer analyzer = new MedalAnalyzer();
        List<Medal> golds = analyzer.getGoldMedals(medals);
        assertEquals(2, golds.size());
        assertTrue(golds.stream().allMatch(m -> m.getPlace() == 1));
    }

    @Test
    public void testTopThreeInMedals_basic() {
        List<Medal> medals = List.of(
                new Medal(Country.USA, KindOfSport.ATHLETICS, "Alice", 1),
                new Medal(Country.USA, KindOfSport.BASKETBALL, "Alice", 2),
                new Medal(Country.CHN, KindOfSport.SWIMMING, "Li Wei", 1),
                new Medal(Country.GER, KindOfSport.FENCING, "Ivan", 3)
        );
        MedalAnalyzer analyzer = new MedalAnalyzer();
        List<Country> topThree = analyzer.topThreeInMedals(medals);
        assertEquals(3, topThree.size());
        assertTrue(topThree.contains(Country.USA));
        assertTrue(topThree.contains(Country.CHN));
        assertTrue(topThree.contains(Country.GER));
    }
}
