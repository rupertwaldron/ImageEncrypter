package mainWindow.datamodel;

import java.io.IOException;
import java.nio.file.*;

public class InputReader implements Reader {
    private Path path;

    public InputReader(Path path) {
        this.path = path;
    }

    @Override
    public byte[] encrypt(byte[] data) {
        return data;
    }

    @Override
    public byte[] read() {
        byte[] allBytes = null;
        try {
            long start = System.currentTimeMillis();
            allBytes = Files.readAllBytes(path);
            long end = System.currentTimeMillis();
            System.out.println("File read in: " + (end - start) + " ms");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return allBytes;
    }


//    @Override
//    public String read() {
//        StringBuilder sb = new StringBuilder();
//        try (BufferedReader br = Files.newBufferedReader(path)) {
//            String currentLine = null;
//            while ((currentLine = br.readLine()) != null) {
//                sb.append(currentLine);
//            }
//        } catch (IOException e) {
//            System.out.println("Error: " + e.getMessage());
//            e.printStackTrace();
//        }
//        return sb.toString();
//    }
//
//    @Override
//    public void write(String dataToFile) throws IOException {
//        String out = path.toAbsolutePath().toString();
//        out = out.substring(0, out.length() - 3) + "nyp";
//        Path newPath = Paths.get(out);
//        try (BufferedWriter bw = Files.newBufferedWriter(newPath)) {
//            bw.write(dataToFile);
//        } catch (IOException e) {
//            System.out.println(e.getStackTrace());
//            throw new IOException("Error writing to file");
//        }
//    }
}
