package me.stojan.android.ciphers;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import javax.crypto.Cipher;
import java.security.Key;

@SmallTest
@RunWith(AndroidJUnit4.class)
public abstract class CipherTest {

    protected static final String BOUNCY_CASTLE = "BC";

    protected static final byte[] BYTES = (
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eget est vitae quam sodales pretium. " +
            "Aenean fringilla augue eget lobortis porttitor. Aliquam et justo malesuada, congue ex in, rhoncus " +
            "risus. Nulla facilisi. Pellentesque habitant morbi tristique senectus et netus et malesuada fames " +
            "ac turpis egestas. Vestibulum condimentum, mi quis ultrices bibendum, eros diam efficitur leo, vel" +
            " lacinia est massa non metus. Suspendisse potenti. Maecenas egestas commodo lorem quis ultricies. " +
            "Morbi fringilla sit amet diam sit amet volutpat. Vestibulum eleifend metus a maximus vestibulum. C" +
            "urabitur facilisis lacus magna, nec feugiat dolor fringilla gravida. Maecenas ultricies massa magn" +
            "a, et accumsan ex rutrum ac.\nSed tristique enim vitae volutpat vehicula. Phasellus egestas urna n" +
            "ec mi rhoncus tincidunt. Aenean nec dictum ex, in facilisis turpis. Donec vel lacus vel dui luctus" +
            " viverra. Nullam in libero orci. Mauris tempus mi in quam fringilla dictum. Nulla facilisi. Sed po" +
            "rttitor eleifend porttitor. Integer lacinia cursus nulla, nec feugiat velit aliquet nec. Vestibulu" +
            "m nisi tellus, scelerisque quis ultrices molestie, gravida sit amet lectus. Integer condimentum ma" +
            "gna dui, non mollis lectus commodo sed. Integer eu porttitor ipsum. Pellentesque habitant morbi tr" +
            "istique senectus et netus et malesuada fames ac turpis egestas cras amet.").getBytes();

    private Key key;

    @Before
    public void setUp() throws Exception {
        key = generateKey();
    }

    public abstract Key generateKey() throws Exception;

    public Key obtainKey() {
        return key;
    }

    @Test
    public final void encryptDecryptCycle() throws Exception {
        final Cipher encrypt = encrypt();
        final Cipher decrypt = decrypt();

        final byte[] encrypted = encrypt.doFinal(BYTES);
        final byte[] decrypted = decrypt.doFinal(encrypted);

        assertArrayEquals(BYTES, decrypted);
    }

    public abstract Cipher encrypt() throws Exception;

    public abstract Cipher decrypt() throws Exception;

}
