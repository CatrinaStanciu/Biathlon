import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;



public class Main {

    public static void main(String[] args) {

        List<Athlete> athletes = rankAthletes();

        System.out.println("Winner - " + athletes.get(0).getName() + "; " + athletes.get(0).getTotalTime() + "\n" +
                            "Runner-up - " + athletes.get(1).getName() + "; " + athletes.get(1).getTotalTime() + "\n" +
                            "Third place - " + athletes.get(2).getName() + "; " + athletes.get(2).getTotalTime());

    }



    private static List<Athlete> rankAthletes() {

        List<Athlete> athletes = readAthletesFromCSV("biathlon.csv");

        for (Athlete a : athletes) {

            if (a.getSkiTimeResult() != null) {
                String[] values = a.getSkiTimeResult().split(":");

                int shootingTimeResult = a.getShootingTimeResult();

                if (Integer.parseInt(values[1]) + shootingTimeResult >= 60) {
                    a.minutes = Integer.parseInt(values[0]) + (Integer.parseInt(values[1]) + shootingTimeResult) / 60;
                    a.seconds = (Integer.parseInt(values[1]) + shootingTimeResult) % 60;
                } else {
                    a.minutes = Integer.parseInt(values[0]);
                    a.seconds = Integer.parseInt(values[1]) + shootingTimeResult;
                }

                a.totalTime = a.minutes + ":" + a.seconds;
                //System.out.println(totalTime);

            }

        }

        Comparator<Athlete> comp = Comparator.naturalOrder();
        athletes.sort(comp);

        return athletes;
    }



    private static List<Athlete> readAthletesFromCSV(String fileName) {

        List<Athlete> athletes = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);

        // create an instance of BufferedReader /
        // using try with resources

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {

            //read the first line from the text file
            String line = br.readLine();

            // loop until all lines are read
            while (line != null) {
                String[] attributes = line.split(",");
                //System.out.println(Arrays.toString(attributes));

                Athlete athlete = inputAthleteInfo(attributes);

                // adding book into ArrayList
                athletes.add(athlete);

                // read next line before looping
            // if end of file reached, line would be null
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return athletes;
    }


    public static Athlete inputAthleteInfo(String[] metadata) {
        String temp = metadata[0].substring(2);
        Integer number = Integer.parseInt(temp);
        String name = metadata[1];        
        String countryCode = metadata[2];
        String skiTimeResult = metadata[3];
        String firstShootingRange = metadata[4];
        String secondShootingRange = metadata[5];
        String thirdShootingRange = metadata[6];


        // create and return athlete info of this metadata

        return new Athlete(number, name, countryCode, skiTimeResult, firstShootingRange,
                secondShootingRange, thirdShootingRange);
    }

}

