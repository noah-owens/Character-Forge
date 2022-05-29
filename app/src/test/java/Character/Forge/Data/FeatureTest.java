package Character.Forge.Data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FeatureTest {
    private Feature f;

    @BeforeEach
    void setUp() {
        f = new Feature("Balding", 0, false);
    }

    @Test
    void getName() {
        assert (f.getName().equals("Balding"));
    }

    @Test
    void getLevel() {
        assert (f.getLevel() == 0);
    }

    @Test
    void isTweakNeeded() {
        assert (!f.isTweakNeeded());
    }
}