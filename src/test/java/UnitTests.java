import org.example.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnitTests {

    @Test
    void testFireGrass() {
        Main testClass = new Main();
        assertEquals("2x", testClass.getEffectiveness("fire -> grass"));
    }

    @Test
    void testFightingIceRock() {
        Main testClass = new Main();
        assertEquals("4x", testClass.getEffectiveness("fighting -> ice rock"));
    }

    @Test
    void testPsychicPoisonDark() {
        Main testClass = new Main();
        assertEquals("0x", testClass.getEffectiveness("psychic -> poison dark"));
    }

    @Test
    void testWaterNormal() {
        Main testClass = new Main();
        assertEquals("1x", testClass.getEffectiveness("water -> normal"));
    }

    @Test
    void testFireRock() {
        Main testClass = new Main();
        assertEquals("0.5x", testClass.getEffectiveness("fire -> rock"));
    }
}