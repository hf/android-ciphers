package me.stojan.android.ciphers;

import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import java.util.Random;

public abstract class AECipherTest extends CipherTest {

    @Test(expected = BadPaddingException.class)
    public final void dataIntegrity() throws Exception {
        final Cipher encrypt = encrypt();
        final Cipher decrypt = decrypt();

        final byte[] encrypted = encrypt.doFinal(BYTES);

        final Random random = new Random();

        encrypted[random.nextInt(encrypted.length)] += 1;

        final byte[] decrypted = decrypt.doFinal(encrypted);
    }
}
