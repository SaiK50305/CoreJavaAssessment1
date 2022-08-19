import java.util.*;
import java.util.stream.Collectors;

public class StreamCollectorsExercise {
    public static void main(String[] args) {
        List<Player> li = new ArrayList();
        li.add(new Player("Ram", 123, 5432, 101, (new Country(1001, "India"))));
        li.add(new Player("Jim", 237, 8575, 91, (new Country(1002, "England"))));
        li.add(new Player("Rick", 189, 4906, 112, (new Country(1003, "South Africa"))));
        li.add(new Player("jil", 162, 3759, 79, (new Country(1004, "France"))));
        li.add(new Player("Jom", 254, 5788, 132, (new Country(1005, "USA"))));
        li.add(new Player("Raj", 184, 5588, 122, (new Country(1001, "India"))));
        li.add(new Player("Rahul", 84, 3588, 122, (new Country(1001, "India"))));
        getPlayersByCountry(li);
        getTotalPlayersByCountry(li);
        getTotalRunsByCountry(li);
        getPlayerNamesByCountry(li);
        getMaxScoreByCountry(li);
        getPlayerNamesStringByCountry(li);
    }
    public static void getPlayersByCountry(List<Player> li){
        Map<Object,Object> countryNameWithPlayerName = li.stream().collect(Collectors.toMap(p1->p1.getPlayerName(),p1->p1.getCountry().getCountryName()));
        System.out.println(countryNameWithPlayerName);}
    public static void getPlayerNamesByCountry(List<Player> li){
        Map<Object, Object> countryNameWithPlayerName1 = li.stream().filter(player -> player.getMatchesPlayed() > 100).collect(Collectors.toMap(p1->p1.getPlayerName(),p1->p1.getCountry()));
        System.out.println(countryNameWithPlayerName1);}
    public static void getTotalPlayersByCountry(List<Player> li) {
        Map<String, Long>  n2 = li.stream().collect(Collectors.groupingBy(k -> k.getCountry().getCountryName()
                ,Collectors.counting()));;
        System.out.println(n2);
    }
    public static void getTotalRunsByCountry(List<Player> li) {
        Map<String, Long>  n3 = li.stream().collect(Collectors.groupingBy(k -> k.getCountry().getCountryName(), Collectors.summingLong(p5->p5.getRuns())));
        System.out.println(n3);
    }
    public static void getMaxScoreByCountry(List<Player> p) {

        Map<Object, Object> highest = p.stream()
                .collect(Collectors.groupingBy(Player::getCountry, Collectors.groupingBy(Player::getRuns, TreeMap::new, Collectors.toList())))
                .entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(),e -> e.getValue().lastEntry().getKey()));
        System.out.println(highest);
    }
    public static void getPlayerNamesStringByCountry(List<Player> p){
        Map<String, String> countryNameWithPlayerName = p.stream().collect(Collectors.toMap(p1->p1.getCountry().getCountryName(),p1->p1.getPlayerName(), (s, a) -> s + ", " + a, LinkedHashMap::new));
        System.out.println(countryNameWithPlayerName);
    }



}