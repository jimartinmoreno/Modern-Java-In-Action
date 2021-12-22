package modernjavainaction.chap03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;

public class ExecuteAround {

    // C:\Proyectos\IntellJ\Java Core\ModernJavaInAction\src\main\resources\modernjavainaction\chap03
    //    private static final String FILE = ExecuteAround.class.getResource("C:\\Proyectos\\IntellJ\\Java Core\\ModernJavaInAction\\target\\classes\\modernjavainaction\\chap03\\data.txt").getFile();
    private static final String FILE = "C:\\Proyectos\\IntellJ\\Java Core\\ModernJavaInAction\\target\\classes\\modernjavainaction\\chap03\\data.txt";

    public static void main(String... args) throws IOException {
        // method we want to refactor to make more flexible
        String result = processFileLimited();
        System.out.println(result);

        System.out.println("---");

        String oneLine = processFile((BufferedReader b) -> b.readLine());
        System.out.println(oneLine);

        System.out.println("---");
        String twoLines = processFile((BufferedReader b) -> b.readLine() + b.readLine());
        System.out.println(twoLines);

        System.out.println("---");
        String twoLinesBis = processFileBis((BufferedReader b) -> {
            String resultString = null;
            try {
                resultString = b.readLine() + b.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                return resultString;
            }

        });
        System.out.println(twoLinesBis);
    }

    public static String processFileLimited() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            return br.readLine();
        }
    }

    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            return p.process(br);
        }
    }

    public static String processFileBis(Function<BufferedReader, String> p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            return p.apply(br);
        }
    }

    @FunctionalInterface
    public interface BufferedReaderProcessor {
        String process(BufferedReader b) throws IOException;
    }
}
