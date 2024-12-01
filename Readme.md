                  
# image-hash-spoofing Image Spoofing Service
This Java application modifies an image file by adding padding bytes to its content and generates a SHA-1, SHA-256 hash,SHA-512 & SHA-2 of the file's byte representation. The process continues until the hash meets a 
specific condition (e.g., starting with `f3`). The program also logs the number of iterations and the time taken to achieve the desired hash. ---
## Features
- Converts byte arrays to their hexadecimal string representation. - Computes SHA-256 hash of a file's byte representation. - Adds padding to the image and repeatedly hashes it until a condition is satisfied. - Logs 
process details, including iterations and time taken. ---
## Prerequisites
Before running the program, ensure you have the following: - **JDK 8 or higher** installed. - **Basic knowledge of file paths** in your operating system. ---
## File Structure
- -ImageSpoofingInterface.java // Interface for the spoofing service 
  -mageSpoofingService.java // Implements the spoofing logic 
  -Main.java // Entry point of the program 
## Key Functions
### `bytesToHex(byte[] bytes)`
- Converts a byte array into a hexadecimal string.
### `hashEngine(byte[] hex)`
- Computes the SHA-256 hash of the file's byte array. - Uses Java's `MessageDigest` library for 
cryptographic hashing.
### `padding(byte[] imageBytes, String fileOutputPath)`
- Repeatedly appends a padding byte (e.g., 0x2FE) to the image byte array until a condition is met. - 
Saves the modified byte array to a file (e.g., `ModifiedFile.jpeg`). - Computes the hash of the 
modified byte array after each iteration. - Logs:
  - The hash when the condition is met. 
  - The number of iterations taken. 
  - The time required to find 
   the match.
