public interface ImageSpoofingInterface {
    String bytesToHex(byte[] bytes);

    String hashEngine(byte[] hex);

    String padding(byte[] imageBytes, String outPath);

}
