package me.stojan.android.ciphers.aes_pbe;

import android.util.Log;
import me.stojan.android.ciphers.CipherTest;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.Key;

public abstract class AESPBECipherTest extends CipherTest {

    private final String algorithm;
    private final int iterationCount;
    private final char[] password;
    private final byte[] salt;

    public AESPBECipherTest(String algorithm, int iterationCount, char[] password, byte[] salt) {
        this.algorithm = algorithm;
        this.iterationCount = iterationCount;
        this.password = password;
        this.salt = salt;
    }

    @Override
    public Key generateKey() throws Exception {
        final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm, BOUNCY_CASTLE);

        final Key key = keyFactory.generateSecret(new PBEKeySpec(password, salt, iterationCount));

        Log.d("@@@", key.getAlgorithm());

        key.getEncoded();

        Log.d("@@@", key.getFormat());

        return key;
    }

    @Override
    public Cipher encrypt() throws Exception {
        final Cipher cipher = Cipher.getInstance(algorithm, BOUNCY_CASTLE);

        cipher.init(Cipher.ENCRYPT_MODE, obtainKey());

        return cipher;
    }

    @Override
    public Cipher decrypt() throws Exception {
        final Cipher cipher = Cipher.getInstance(algorithm, BOUNCY_CASTLE);

        cipher.init(Cipher.DECRYPT_MODE, obtainKey());

        return cipher;
    }

}
