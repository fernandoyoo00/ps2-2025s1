import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class GerenciadorNomesArq implements GerenciadorNomes {
    @Override
    public List<String> obterNomes() {
        Path path = Paths.get("nomes.txt");
        try {
            List<String> strings = Files.readAllLines(path);
            return strings;
        } catch(IOException exception) {

        }
        return null;
    }
    @Override
    public void adicionarNome(String nome) {
            Path path = Paths.get("nomes.txt");
            try {
                Files.writeString(path, nome + System.lineSeparator(),
                StandardOpenOption.APPEND);
            } catch (IOException exception) {
        } 
    }
}