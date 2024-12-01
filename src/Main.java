import java.io.*;
import java.nio.file.Path;
import java.security.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        //add your file input path

        String fileInPath = "/home/mwas/Pictures/ICONS/pdf.jpeg";

        //add your file output path

        String fileOutPath = "/home/mwas/Pictures/ICONS/Modifiedpdf.jpeg";

        ImageSpoofingService imageSpoofingService = new ImageSpoofingService();

        byte[] imageBytes = Files.readAllBytes(Paths.get(fileInPath));
        System.out.println(imageBytes.length);

        System.out.println(imageSpoofingService.hashEngine(imageBytes));
        byte[] paddedImgByteInHex = imageSpoofingService.padding(imageBytes, fileOutPath);

    }
}
