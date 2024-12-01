                  
# image-hash-spoofing Image Spoofing Service
This Java application modifies an image file by adding padding bytes to its content and generates a SHA-1, SHA-256 hash,SHA-512 & SHA-2 of the file's byte representation. The process continues until the hash meets a 
specific condition (e.g., starting with `f3`). The program also logs the number of iterations and the time taken to achieve the desired hash. ---
## Features
- Converts byte arrays to their hexadecimal string representation. - Computes SHA-256 hash of a file's byte representation. - Adds padding to the image and repeatedly hashes it until a condition is satisfied. - Logs 
process details, including iterations and time taken. ---
## Prerequisites
Before running the program, ensure you have the following: - **JDK 8 or higher** installed. - **Basic knowledge of file paths** in your operating system. ---
## File Structure
```plaintext ├── ImageSpoofingInterface.java // Interface for the spoofing service ├── ImageSpoofingService.java // Implements the spoofing logic ├── Main.java // Entry point of the program ---
## Key Functions
1. bytesToHex(byte[] bytes) 
      -Converts a byte array into a hexadecimal String.
2. hashEngine(byte[] hex) 
      -Computes the SHA-256 & SHA-512 of the file byte array. -It uses the MessageDigest library from Java Security.
      -It uses the out of the box messageDigest library from Java Security  
3.padding(byte[] imageBytes),String fileOutputPath)
      -Repeatedly appends a padding byte to the image array till a condition is met.
      -Saves/Writes the byte to a file ie modifiedFile.JPEG
      -Computes the hash of the byte array of the modifiedFile after each modification ie after each padding since we wrap it in a while loop.
      -The while loop breaks when the Hash meets the condition ...in these case when it starta with the defined prefix. "fe........"
         . outputs the hash where the match was found.
         . Number of iterations.
         . Time taken to fin the match
      
