import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

public class ImageSpoofingService implements ImageSpoofingInterface {
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

    //adds padding

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
            iterations = iterations + 1;
            if (hash.startsWith("f3")) {

                System.out.println("Match found: " + hash);
                System.out.println("found in " + iterations + " iterations");
                Long closeTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
                System.out.println("Time taken " + (closeTime - startTime));
                return Files.readAllBytes(path);
            } else {
                System.out.println("Searching.");
            }

            //if (byteArrayOutputStream.size() > imageBytes.length + 1000) {
            //  throw new RuntimeException("Padding limit exceeded without finding the match.");
            //}
        }
    }

}
