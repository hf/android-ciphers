package me.stojan.android.ciphers.aes_cbc;

import me.stojan.android.ciphers.CipherTest;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Arrays;

public abstract class AESCBCCipherTest extends CipherTest {

    public static final String ALGORITHM = "AES";
    public static final String MODE = "CBC";
    public static final String PADDING = "PKCS7Padding";

    public static final String TRANSFORMATION = ALGORITHM + "/" + MODE + "/" + PADDING;

    private final int strength;

    private IvParameterSpec iv;

    protected AESCBCCipherTest(int strength) {
        this.strength = strength;
    }

    @Override
    public Key generateKey() throws Exception {
        final KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM, BOUNCY_CASTLE);

        keyGenerator.init(strength);

        final SecureRandom ivRandom = new SecureRandom();

        final byte[] iv = new byte[16];

        ivRandom.nextBytes(iv);

        this.iv = new IvParameterSpec(iv);

        Arrays.fill(iv, (byte) 0);

        return keyGenerator.generateKey();
    }

    @Override
    public Cipher encrypt() throws Exception {
        final Cipher cipher = Cipher.getInstance(TRANSFORMATION, BOUNCY_CASTLE);

        cipher.init(Cipher.ENCRYPT_MODE, obtainKey(), iv);

        return cipher;
    }

    @Override
    public Cipher decrypt() throws Exception {
        final Cipher cipher = Cipher.getInstance(TRANSFORMATION, BOUNCY_CASTLE);

        cipher.init(Cipher.DECRYPT_MODE, obtainKey(), iv);

        return cipher;
    }
}
