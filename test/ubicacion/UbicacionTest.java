package ubicacion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UbicacionTest {

    private Ubicacion ubicacion;

    @BeforeEach
    void setUp() {
        ubicacion = new Ubicacion(-34.6037, -58.3816);
    }

    @Test
    void testGetLatitud() {
        assertEquals(-34.6037, ubicacion.getLatitud(), 0.0001);
    }

    @Test
    void testGetLongitud() {
        assertEquals(-58.3816, ubicacion.getLongitud(), 0.0001);
    }

    @Test
    void testValoresDistintos() {
        Ubicacion otraUbicacion = new Ubicacion(40.7128, -74.0060);
        assertNotEquals(otraUbicacion.getLatitud(), ubicacion.getLatitud());
        assertNotEquals(otraUbicacion.getLongitud(), ubicacion.getLongitud());
    }
}