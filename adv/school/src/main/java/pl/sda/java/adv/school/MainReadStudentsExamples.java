package pl.sda.java.adv.school;

import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MainReadStudentsExamples {
    public static void main(String[] args) throws IOException {
        Path path1 = Path.of("C:\\Users\\Musk\\Downloads\\students.csv");
        Path path2 = Path.of("C:/Users/Musk/Downloads/students.csv");
        Path path3 = Path.of("C:", "Users", "Musk", "Downloads", "students.csv");
        Path path4 = Path.of("c:", "users", "musk", "downloads", "students.csv");
        Path path5 = Path.of(URI.create("file:///C:/Users/Musk/Downloads/students.csv"));
        Path path6 = Path.of("example/students.csv");


        for (Path path : List.of(path1, path2, path3, path4, path5, path6)) {
            boolean exists = Files.exists(path);
            System.out.printf("%s,%b\n", path, exists);
        }
        System.out.println();

        File file1 = new File("C:\\Users\\Musk\\Downloads\\students.csv");
        File file2 = new File("C:/Users/Musk/Downlo/students.csv");
        File file3 = new File("example/students.csv");

        for (File file : List.of(file1, file2, file3)) {
            boolean exists = file.exists();
            System.out.printf("%s,%b\n", file, exists);
        }

//        printContentsIncorrectWay(file3);

//        printContentsCorrectWayOldSchoolWay(file2);

        printContentsModernWay(file3);


    }

    private static void printContentsIncorrectWay(File file) throws IOException {
        InputStream inputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        while (bufferedReader.ready()) {
            System.out.println(bufferedReader.readLine());
        }
//        bufferedReader.lines().forEach(System.out::println);


    }

    private static void printContentsCorrectWayOldSchoolWay(File file) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while (bufferedReader.ready()) {
                System.out.println(bufferedReader.readLine());
            }
        } catch (IOException e) {
            System.err.println("Message");
            e.printStackTrace();
        }
        finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static void printContentsModernWay(File file) throws IOException{
        try(FileReader fileReader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while (bufferedReader.ready()) {
                System.out.println(bufferedReader.readLine());
            }
        }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
