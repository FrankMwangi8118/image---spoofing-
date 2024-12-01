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

        //add your desired fileInput path

        String fileInputPath = "/home/mwas/Pictures/ICONS/pdf.tiff";
//        String fileInputPath = "/home/mwas/Pictures/ICONS/blog.svg";

        //add your desired fileOutput path

        String fileOutputPath = "/home/mwas/Pictures/ICONS/Modifiedpdf.tiff";
//        String fileOutputPath = "/home/mwas/Pictures/ICONS/Modifiedblog.svg";
        //add your desired prefix

        String prefix="2fe";

        ImageSpoofingService imageSpoofingService = new ImageSpoofingService();

        byte[] imageBytes = Files.readAllBytes(Paths.get(fileInputPath));
        System.out.println(imageBytes.length);

        System.out.println(imageSpoofingService.hashEngine(imageBytes));
        byte[] paddedImgByteInHex = imageSpoofingService.padding(imageBytes, fileOutputPath);

    }
}
