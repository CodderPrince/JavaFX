// Encryptable interface with encrypt and decrypt methods
interface Encryptable {
    String encrypt(String data);
    String decrypt(String encryptedData);
}

// AES encryption and decryption implementation
class AES implements Encryptable {

    @Override
    public String encrypt(String data) {
        // Simple AES encryption logic (mocked, not using real AES algorithm for simplicity)
        StringBuilder encrypted = new StringBuilder();
        for (char c : data.toCharArray()) {
            encrypted.append((char) (c + 1)); // Simple shift encryption (for demonstration)
        }
        return encrypted.toString();
    }

    @Override
    public String decrypt(String encryptedData) {
        // Simple AES decryption logic (mocked)
        StringBuilder decrypted = new StringBuilder();
        for (char c : encryptedData.toCharArray()) {
            decrypted.append((char) (c - 1)); // Reverse the shift for decryption
        }
        return decrypted.toString();
    }
}

// RSA encryption and decryption implementation
class RSA implements Encryptable {

    @Override
    public String encrypt(String data) {
        // Simple RSA encryption logic (mocked, not using real RSA algorithm for simplicity)
        StringBuilder encrypted = new StringBuilder();
        for (char c : data.toCharArray()) {
            encrypted.append((char) (c + 2)); // Simple shift encryption (for demonstration)
        }
        return encrypted.toString();
    }

    @Override
    public String decrypt(String encryptedData) {
        // Simple RSA decryption logic (mocked)
        StringBuilder decrypted = new StringBuilder();
        for (char c : encryptedData.toCharArray()) {
            decrypted.append((char) (c - 2)); // Reverse the shift for decryption
        }
        return decrypted.toString();
    }
}

// Main class to test the encryption and decryption
public class EncryptionExample {

    public static void main(String[] args) {
        String originalData = "Hello World!";

        // Using AES for encryption and decryption
        Encryptable aesEncryptor = new AES();
        String aesEncrypted = aesEncryptor.encrypt(originalData);
        String aesDecrypted = aesEncryptor.decrypt(aesEncrypted);

        // Using RSA for encryption and decryption
        Encryptable rsaEncryptor = new RSA();
        String rsaEncrypted = rsaEncryptor.encrypt(originalData);
        String rsaDecrypted = rsaEncryptor.decrypt(rsaEncrypted);

        // Display results
        System.out.println("Original Data: " + originalData);

        System.out.println("\nAES Encryption: " + aesEncrypted);
        System.out.println("AES Decryption: " + aesDecrypted);

        System.out.println("\nRSA Encryption: " + rsaEncrypted);
        System.out.println("RSA Decryption: " + rsaDecrypted);
    }
}
