package pl.sdacademy.java.adv.dictionary;

import javax.imageio.IIOException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainDictionary {
    public static void main(String[] args) throws IOException {
        final List<String> allWords = Files.readAllLines(Path.of("dict/slowa.txt"));
        final String regex = "^.ada.ie$";

        //single thread
        final long beforeSingleThread = System.currentTimeMillis();
        final List<String> result = findMatchingLines(allWords, "^.ada.ie$");
        final long timeSingleThread = System.currentTimeMillis() - beforeSingleThread;
        System.out.printf("[SINGLE THREAD] Results: %d, Time: %d [ms]\n", result.size(), timeSingleThread);
        result.forEach(System.out::println);

        //multiple threads
        final int threads = 2;
        final int allWordsCount = allWords.size();
        final int chunkSize = allWordsCount / threads;

        System.out.printf("\nAll words: %d, Chunk ranges:\n", allWordsCount);

        final List<List<String>> chunks = new LinkedList<>();
        for (int i = 0; i < threads; i++) {
            final int start = i * chunkSize;
            final int end = (i == threads - 1) ? allWordsCount : ((i + 1) * chunkSize);
            System.out.printf("%d:%d\n", start, end);
            chunks.add(allWords.subList(start, end));
        }

        final ExecutorService executorService = Executors.newFixedThreadPool(threads);

        final List<Future<List<String>>> futures = new LinkedList<>();

        final long beforeMultiThread = System.currentTimeMillis();
        final long timeMultiThread = System.currentTimeMillis() - beforeMultiThread;


        for (List<String> chunk : chunks) {
            final Future<List<String>> chunkResult = executorService.submit(new Callable<List<String>>() {
                @Override
                public List<String> call() throws Exception {
                    return findMatchingLines(chunk, regex);
                }
            });
            futures.add(chunkResult);
        }

        final List<String> multiThreadResult = new LinkedList<>();
        for (Future<List<String>> future : futures) {
            //multiThreadResult.addAll(future.get());
        }

    }


    private static List<String> findMatchingLines(List<String> source, String regex) {
        final List<String> result = new LinkedList<>();
        for (String line : source) {
            if (line.matches(regex)) {
                result.add((line));
            }
        }
        return result;
    }
}
