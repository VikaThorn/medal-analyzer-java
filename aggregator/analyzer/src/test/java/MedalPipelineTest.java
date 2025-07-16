import org.junit.jupiter.api.Test;
import analyzer.MedalAnalyzer;
import util.DataHandler;
import entity.Medal;
import enums.Country;
import enums.KindOfSport;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MedalPipelineTest {

    @Test
    public void testDataHandlingAndAnalysis() {
        DataHandler handlerMock = mock(DataHandler.class);
        MedalAnalyzer analyzer = new MedalAnalyzer();

        List<Medal> mockInput = List.of(
                new Medal(Country.USA, KindOfSport.SWIMMING, "John", 1),
                new Medal(Country.USA, KindOfSport.SWIMMING, "Mike", 2)
        );

        when(handlerMock.loadFromFile(anyString())).thenReturn(mockInput);

        List<Medal> loaded = handlerMock.loadFromFile("fake.csv");
        List<Medal> golds = analyzer.getGoldMedals(loaded);

        assertEquals(1, golds.size());
        assertEquals("John", golds.get(0).getAthlete());

        verify(handlerMock, times(1)).loadFromFile("fake.csv");
    }
}
