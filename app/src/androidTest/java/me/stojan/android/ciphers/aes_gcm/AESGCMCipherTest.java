package me.stojan.android.ciphers.aes_gcm;

import me.stojan.android.ciphers.AECipherTest;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Arrays;

public abstract class AESGCMCipherTest extends AECipherTest {

    public static final String ALGORITHM = "AES";
    public static final String MODE = "GCM";
    public static final String PADDING = "NoPadding";

    public static final String TRANSFORMATION = ALGORITHM + "/" + MODE + "/" + PADDING;

    private final int strength;

    private IvParameterSpec iv;

    protected AESGCMCipherTest(int strength) {
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
