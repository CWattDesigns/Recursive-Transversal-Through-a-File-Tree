package Recursive_Transversal;

import java.io.PrintStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path: ");
        String inputPath = scanner.nextLine();
        Path userInputPath = Paths.get(inputPath);
        if (Files.exists(userInputPath, new LinkOption[0])) {
            listDir(userInputPath, 0);
        } else {
            System.out.println("Invalid path. Exiting.");
        }

    }

    public static void listDir(Path path, int depth) throws Exception {
        if (Files.isDirectory(path, new LinkOption[0])) {
            DirectoryStream<Path> paths = Files.newDirectoryStream(path);
            int files = 0;
            long size = 0L;
            Iterator var6 = paths.iterator();

            PrintStream var10000;
            String var10001;
            while(var6.hasNext()) {
                Path tempPath = (Path)var6.next();
                BasicFileAttributes attr = Files.readAttributes(tempPath, BasicFileAttributes.class);
                if (Files.isDirectory(tempPath, new LinkOption[0])) {
                    listDir(tempPath, depth + 1);
                } else {
                    var10000 = System.out;
                    var10001 = spacesForDepth(depth);
                    var10000.println(var10001 + " -- " + String.valueOf(tempPath.getFileName()));
                    ++files;
                    size += attr.size();
                }
            }

            var10000 = System.out;
            var10001 = spacesForDepth(depth);
            var10000.println(var10001 + " >> " + String.valueOf(path.getFileName()) + " === Files: " + files + ", Size: " + size + " Bytes");
        }

    }

    public static String spacesForDepth(int depth) {
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < depth; ++i) {
            builder.append("    ");
        }

        return builder.toString();
    }
}