import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface ImageSpoofingInterface {
    String bytesToHex(byte[] bytes);

    String hashEngine(byte[] hex) throws NoSuchAlgorithmException;

    byte[] padding(byte[] imageBytes, String outPath) throws IOException, NoSuchAlgorithmException;

    void createTheOutputFile(String outputFIlePath) throws IOException;
}
