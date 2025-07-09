package util;

import entity.Medal;
import enums.Country;
import enums.KindOfSport;

import java.io.*;
import java.util.List;

public class DataHandler {
    private static final int COUNTRY_INDEX = 0;
    private static final int KIND_OF_SPORT_INDEX = 1;
    private static final int ATHLETE_INDEX = 2;
    private static final int PLACE_INDEX = 3;

    public void saveToFile(String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            List<Medal> medals = MedalFactory.generateMedal();
            for (Medal medal : medals) {
                writer.write(String.format("%s;%s;%s;%d%n",
                        medal.getCountry(),
                        medal.getKindOfSport(),
                        medal.getAthlete(),
                        medal.getPlace()));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<Medal> loadFromFile(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            return reader.lines()
                    .map(line -> {
                        String[] parts = line.split(";");
                        Country country = Country.valueOf(parts[COUNTRY_INDEX]);
                        KindOfSport kindOfSport = KindOfSport.valueOf(parts[KIND_OF_SPORT_INDEX]);
                        String athlete = parts[ATHLETE_INDEX];
                        int place = Integer.parseInt(parts[PLACE_INDEX]);
                        return new Medal(country, kindOfSport, athlete, place);
                    }).toList();
        } catch (IOException ex) {
            ex.printStackTrace();
            return List.of();
        }
    }
}
