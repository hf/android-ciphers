package me.stojan.android.ciphers.aes_pbe;

public class PBEWithSHA256AndAES256CBCCipherTest extends AESPBECipherTest {
    public static final String ALGORITHM = "PBEWITHSHA256AND256BITAES-CBC-BC";

    public PBEWithSHA256AndAES256CBCCipherTest() {
        super(ALGORITHM, 2048, "password1".toCharArray(), "salty".getBytes());
    }
}
