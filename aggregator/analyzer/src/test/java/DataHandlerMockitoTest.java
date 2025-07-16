import analyzer.MedalAnalyzer;
import org.junit.jupiter.api.Test;
import util.DataHandler;
import entity.Medal;
import enums.Country;
import enums.KindOfSport;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DataHandlerMockitoTest {

    @Test
    public void testLoadFromFileWithMock() {
        DataHandler mockHandler = mock(DataHandler.class);

        List<Medal> mockedResult = List.of(
                new Medal(Country.USA, KindOfSport.SWIMMING, "John Doe", 1)
        );

        when(mockHandler.loadFromFile("fake.csv")).thenReturn(mockedResult);

        List<Medal> result = mockHandler.loadFromFile("fake.csv");

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getAthlete());

        verify(mockHandler, times(1)).loadFromFile("fake.csv");
    }

    @Test
    public void testMedalApplicationFlow() {
        DataHandler dataHandlerMock = mock(DataHandler.class);
        MedalAnalyzer analyzerMock = mock(MedalAnalyzer.class);

        List<Medal> fakeMedals = List.of(
                new Medal(Country.USA, KindOfSport.FENCING, "Alice", 1)
        );

        when(dataHandlerMock.loadFromFile("test.csv")).thenReturn(fakeMedals);
        when(analyzerMock.getGoldMedals(fakeMedals)).thenReturn(fakeMedals);

        MedalApplication app = new MedalApplication(dataHandlerMock, analyzerMock);
        List<Medal> result = app.run("test.csv");

        assertEquals(1, result.size());
        verify(dataHandlerMock).loadFromFile("test.csv");
        verify(analyzerMock).getGoldMedals(fakeMedals);
    }

}
