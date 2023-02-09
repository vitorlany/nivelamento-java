import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

class ComparatorSkills implements Comparator<Candidates> {

    @Override
    public int compare(Candidates o1, Candidates o2) {
        Integer o1Value = o1.getNotes().get("Java");
        Integer o2Value = o2.getNotes().get("Java");
        return o1Value.compareTo(o2Value);
    }
}

public class Main {

    public static void main(String[] args) {
        List<Candidates> candidates = loadCandidatesData("candidates.txt");
        candidates.sort(new ComparatorSkills().reversed());
        System.out.println(candidates);
    }

    public static List<Candidates> loadCandidatesData(String fileName) {
        List<Candidates> candidates = new ArrayList<>();
        Path path = Paths.get("./src/files/" + fileName);

        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(line -> {
                String[] splitData = line.split(";");
                String name = splitData[0];

                String[] arrayStringToInt = Arrays.copyOfRange(splitData, 1, splitData.length);
                Integer[] notesArray = Arrays.stream(arrayStringToInt)
                        .map(Integer::valueOf)
                        .toList()
                        .toArray(new Integer[0]);

                Candidates candidate = new Candidates(name, notesArray);
                candidates.add(candidate);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return candidates;
    }

}