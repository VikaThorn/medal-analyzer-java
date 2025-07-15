import analyzer.MedalAnalyzer;
import entity.Medal;
import util.DataHandler;

import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

//В файл записана олимпийская медальная статистика для различных стран
//в следующем формате:
//страна;вид спорта;спортсмен;место
//Необходимо вывести следующую информацию:
//        - топ-3 страны медального зачета
// - для каждого вида спорта вывести список спортсменов, занявших места
// - вывести спортмена, который завоевал больше всего медалей
//В задаче должны использоваться элементы функционального программирования

public class MedalApplication {
    private static final String PATH = "medals.txt";
    private static Logger logger = Logger.getLogger(MedalApplication.class.getName());
    private final DataHandler dataHandler;
    private final MedalAnalyzer analyzer;

    public MedalApplication(DataHandler dataHandler, MedalAnalyzer analyzer) {
        this.dataHandler = dataHandler;
        this.analyzer = analyzer;
    }

    public List<Medal> run(String path) {
        List<Medal> medals = dataHandler.loadFromFile(path);
        return analyzer.getGoldMedals(medals);
    }

    static {
        try {
            FileHandler fileHandler = new FileHandler("medals_app.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Could not initialize file handler for logger", e);
        }
    }

    public static void main(String[] args) {
        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(Level.INFO);
        for (var handler : rootLogger.getHandlers()) {
            handler.setLevel(Level.INFO);
        }

        logger.log(Level.INFO, "Starting MedalApplication.");
        DataHandler dataHandler = new DataHandler();
        dataHandler.saveToFile(PATH);
        List<Medal> medals = dataHandler.loadFromFile(PATH);
        if (medals.isEmpty()) {
            logger.log(Level.SEVERE, "No medals were loaded.");
            return;
        }
        MedalAnalyzer medalAnalyzer = new MedalAnalyzer();

        logger.log(Level.INFO, "Analyzing medals...");
        System.out.println("\nTop 3 countries in the medal standings:");
        System.out.println(medalAnalyzer.topThreeInMedals(medals));
        System.out.println("\nMedalists in sports:");
        System.out.println(medalAnalyzer.athletesWhoTookPlaces(medals));
        System.out.println("\nBest Athlete:");
        List<String> topAthletes = medalAnalyzer.athleteWithTheMostMedals(medals);
        if (topAthletes.size() == 1) {
            System.out.println(topAthletes.get(0));
        } else if (!topAthletes.isEmpty()){
            System.out.println("There is no outstanding athlete. Several athletes have the same number of medals.\n");
        } else {
            System.out.println("Nobody has medals.\n");
        }
        System.out.println("\nGold medalists:");
        List<Medal> goldMedals = medalAnalyzer.getGoldMedals(medals);
        goldMedals.forEach(System.out::println);
        logger.log(Level.INFO, "Application finished.");
    }
}