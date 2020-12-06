import java.util.Objects;

public class Athlete implements  Comparable<Athlete>{

    Integer number;
    private String name;
    private String countryCode;
    private String skiTimeResult;
    private String firstShootingRange;
    private String secondShootingRange;
    private String thirdShootingRange;
    public String totalTime;
    public int minutes;
    public int seconds;

    public Athlete(Integer number, String name, String countryCode,
                   String skiTimeResult, String firstShootingRange, String secondShootingRange,
                   String thirdShootingRange) {
        this.number =  number;
        this.name = name;
        this.countryCode = countryCode;
        this.skiTimeResult = skiTimeResult;
        this.firstShootingRange = firstShootingRange;
        this.secondShootingRange = secondShootingRange;
        this.thirdShootingRange = thirdShootingRange;
    }

    public Athlete(int number, String name, String countryCode,
                   String totalTime) {
        this.number =  number;
        this.name = name;
        this.countryCode = countryCode;
        this.totalTime = totalTime;
    }

   public String getName() {
        return name;
   }

    public String getTotalTime() {
        return totalTime;
    }

    public String getSkiTimeResult() {
        return skiTimeResult;
    }


    public int getShootingTimeResult() {
        String finalShootingRange = firstShootingRange + secondShootingRange + thirdShootingRange;
        int a = finalShootingRange.length();
        int count = 0;

        for (int i = 0; i < a; i++) {
            if (finalShootingRange.charAt(i) != 'o') {
                continue;
            }
            count++;
        }
        return count * 10;
    }

    @Override
    public int compareTo(Athlete a) {
        return totalTime.compareTo(a.totalTime);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Athlete athlete = (Athlete) o;
        return totalTime == athlete.totalTime &&
                name == athlete.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, totalTime);
    }


    @Override
    public String toString() {
        return "Athlete [name=" + name + ", country=" + countryCode + ", ski time=" + skiTimeResult
                +  ", shooting results=" + firstShootingRange + secondShootingRange + thirdShootingRange
                + "]"; }


}


