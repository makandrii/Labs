package lab4_2;

import org.junit.jupiter.api.Test;
import org.makarov.lab4_2.Decoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecoderTests {
    @Test
    public void vowelsMethodTest() {
        assertEquals("testing", Decoder.vowelsMethodDecode("t2st3ng"));
    }

    @Test
    public void consonantsMethodTest() {
        assertEquals("testing", Decoder.consonantsMethodDecode("vetviph"));
    }
}
