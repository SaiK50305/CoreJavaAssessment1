import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class StreamOperationExercise {
    public static void main(String[] args) {
        List<Player> player = new ArrayList();
        player.add(new Player("Ram", 123, 5432, 101, (new Country(1001, "India"))));
        player.add(new Player("Jim", 237, 8575, 91, (new Country(1002, "England"))));
        player.add(new Player("Rick", 189, 4906, 112, (new Country(1003, "South Africa"))));
        player.add(new Player("jil", 162, 3759, 79, (new Country(1004, "France"))));
        player.add(new Player("Jom", 254, 5788, 132, (new Country(1005, "USA"))));
        player.add(new Player("Raj", 184, 5588, 122, (new Country(1001, "India"))));
        displayPlayers(player);
        displayPlayerForCountry(player, "India");
        getPlayerNames(player);
        getAverageRunsByCountry(player, "India");
        getPlayerNamesSorted(player);
        getPlayerCountry(player);
        getMaxRunsPlayer(player);
        findPlayer(player, "Jim");
        checkHighScorerByCountry(player, "India");
    }

    public static void displayPlayers(List<Player> li) {
        List<String> playerName = li.stream()
                .map(x -> x.getPlayerName())
                .collect(Collectors.toList());
        System.out.println(playerName);
    }

    static void displayPlayerForCountry(List<Player> li, String country) {
        List<String> playerNameByCountry = li.stream()
                .filter(player -> player.getCountry().getCountryName().equals(country) && player.getHighestScore() > 100
                ).map(x -> x.getPlayerName()).collect(Collectors.toList());
        System.out.println(playerNameByCountry);
    }

    static void getPlayerNames(List<Player> li) {
        LinkedList<String> li1 = li.stream().filter(player -> player.getRuns() > 5000)
                .sorted((p1, p2) -> p2.getPlayerName().compareTo(p1.getPlayerName()))
                .map(player -> player.getPlayerName()
                ).collect(Collectors.toCollection(LinkedList::new));
        System.out.println(li1);
    }

    public static void getAverageRunsByCountry(List<Player> p, String country) {
        List<Integer> getRuns = p.stream().filter(player -> player.getCountry().getCountryName().equals(country)).map(p1 -> p1.getRuns())
                .collect(Collectors.toList());
        OptionalDouble average = getRuns.stream().mapToInt(x1 -> x1.intValue()).average();
        System.out.println("AverageRuns " + (average.getAsDouble()));
    }

    public static void getPlayerNamesSorted(List<Player> li) {
        List<String> li3 = li.stream()
                .sorted(Comparator.comparing(p2 -> p2.getCountry().getCountryName()))
                .map(p1 -> p1.getPlayerName()).collect(Collectors.toList());
        System.out.println("Sorted by country  " + li3);
        List<String> li4 = li.stream().sorted(Comparator.comparingInt(Player::getMatchesPlayed))
                .map(p1 -> p1.getPlayerName()).collect(Collectors.toList());
        System.out.println("Sorted by match played  " + li4);
    }

    public static void getPlayerCountry(List<Player> p) {
        Map<Object, Object> countryNameWithPlayerName = p.stream().filter(player -> player.getMatchesPlayed() > 200)
                .collect(Collectors.toMap(p1 -> p1.getPlayerName(), p1 -> p1.getCountry().getCountryName()));
        System.out.println(countryNameWithPlayerName);
    }

    public static void getMaxRunsPlayer(List<Player> p) {
        Player product1 = p.stream().max((p1, p2) -> p1.getRuns() > p2.getRuns() ? 1 : -1).get();
        System.out.println(product1);
    }

    public static void findPlayer(List<Player> p, String name) {
        String s = p.stream()
                .filter(customer -> name.equals(customer.getPlayerName()))
                .map(p1 -> p1.getCountry().getCountryName())
                .findAny()
                .orElse(null);
        System.out.println(s);
    }

    public static void checkHighScorerByCountry(List<Player> p, String name) {
        boolean match = p.stream().anyMatch(s -> s.getRuns() > 10000 && s.getCountry().getCountryName().equals(name));
        System.out.println(match);

    }
}
