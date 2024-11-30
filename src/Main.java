import java.io.*;
import java.nio.file.Path;
import java.security.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

class Utils {
    public String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));  // Convert each byte to its hex representation
        }
        return sb.toString();
    }

   // creates a hash of the byte representation of the file

    public String hashEngine(byte[] hex) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashedbytes = digest.digest(hex);
        System.out.println(Arrays.toString(hashedbytes));
        return bytesToHex(hashedbytes);
    }

    //adds a

    public byte[] padding(byte[] imageBytes, String outPath) throws IOException, NoSuchAlgorithmException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(imageBytes);
        int iterations = 0;
        Long startTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        while (true) {
            byteArrayOutputStream.write(0X2FE);
            Path path = Path.of(outPath);
            String hash = hashEngine(Files.readAllBytes(path));
            try (FileOutputStream fileOutputStream = new FileOutputStream(outPath)) {
                fileOutputStream.write(byteArrayOutputStream.toByteArray());
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
                throw e;
            }
            System.out.println("Hash: " + hash);
            iterations =iterations+1;
            if (hash.startsWith("24")) {

                System.out.println("Match found: " + hash);
                System.out.println("found in "+iterations+" iterations");
                Long closeTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
                System.out.println("Time taken "+ (closeTime-startTime));
                return Files.readAllBytes(path);
            } else {
                System.out.println("Searching.");
            }


            //if (byteArrayOutputStream.size() > imageBytes.length + 1000) {
              //  throw new RuntimeException("Padding limit exceeded without finding the match.");
            //}
        }
    }




    public static class Main {
        public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
            Utils utils = new Utils();

            byte[] imageBytes = Files.readAllBytes(Paths.get("/home/mwas/Pictures/ICONS/pdf.jpeg"));
            System.out.println(imageBytes.length);

            System.out.println(utils.hashEngine(imageBytes));
            byte[] paddedImgByteInHex = utils.padding(imageBytes, "/home/mwas/Pictures/ICONS/Modifiedpdf.jpeg");
            System.out.println(paddedImgByteInHex);

            // original image
            System.out.println(utils.bytesToHex(imageBytes));
            //Modified image
            System.out.println(utils.bytesToHex(paddedImgByteInHex));
        }
    }
}