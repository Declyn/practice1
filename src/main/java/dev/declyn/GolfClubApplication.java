package dev.declyn;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class GolfClubApplication {

    public static void main(String[] args) {
        try {
            var file = new File(GolfClubApplication.class.getClassLoader().getResource("data.txt").toURI());
            var scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                var line = scanner.nextLine();

                if (line.startsWith("#") || line.isEmpty()) {
                    continue;
                }

                var data = line.split(",");

                if (data.length == 0) {
                    continue;
                }

                var start = System.currentTimeMillis();

                var course = data[0];
                int totalRounds = data.length - 1;

                System.out.println("Evaluating course " + course + " with data sample size of " + totalRounds);

                int score = 0;

                for (int i = 1; i < data.length; i++) {
                    int at = Integer.parseInt(data[i]);

                    score += at;

                    System.out.println("Reading score: " + at);
                }

                double avg = (double) score / (double) totalRounds;

                System.out.println("Course " + course + " is " + getMessage(avg) + " based on an average score of " + avg);

                System.out.println("Time taken to evaluate " + course + ' ' + (System.currentTimeMillis() - start) + "ms");

            }

            scanner.close();
        } catch (URISyntaxException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String getMessage(double averageScore) {
        if (averageScore < 65) {
            return "easy";
        }

        if (averageScore <= 79) {
            return "experienced";
        }

        return "expert";
    }

}