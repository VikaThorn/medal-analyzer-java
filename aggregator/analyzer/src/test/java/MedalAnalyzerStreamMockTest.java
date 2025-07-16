import org.junit.jupiter.api.Test;
import analyzer.MedalAnalyzer;
import entity.Medal;
import enums.Country;
import enums.KindOfSport;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MedalAnalyzerStreamMockTest {

    @Test
    public void testGoldMedalsWithMockedList() {
        List<Medal> medalsMock = mock(List.class);

        when(medalsMock.stream()).thenReturn(List.of(
                new Medal(Country.USA, KindOfSport.ATHLETICS, "Alice", 1),
                new Medal(Country.USA, KindOfSport.ATHLETICS, "Bob", 3)
        ).stream());

        MedalAnalyzer analyzer = new MedalAnalyzer();
        List<Medal> golds = analyzer.getGoldMedals(medalsMock);

        assertEquals(1, golds.size());
        assertEquals("Alice", golds.get(0).getAthlete());

        verify(medalsMock, times(1)).stream();
    }
}
