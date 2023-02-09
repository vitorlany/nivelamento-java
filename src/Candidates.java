import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Candidates {
    String name;
    private static List<String> skills = new ArrayList<>();
    Map<String, Integer> notes;

    static {
        Path path = Paths.get("./src/files/skills.txt");
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(skills::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Candidates(String name, Integer[] notesInt) {
        this.name = name;
        setNotes(notesInt);
    }

    public Map<String, Integer> getNotes() {
        return notes;
    }

    public void setNotes(Integer[] notesInt) {
        Map<String, Integer> notes = new HashMap<>();
        try {
            for (int i = 0; i < notesInt.length; i++) {
                notes.put(skills.get(i), notesInt[i]);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.notes = notes;
    }

    public static List<String> getSkills() {
        return skills;
    }

    @Override
    public String toString() {
        return "Candidates{" +
                "name='" + name + '\'' +
                ", notes=" + notes +
                '}';
    }
}
