package ubicacion;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.Muestra;

import static org.mockito.Mockito.*;

public class CalculadoraDeDistanciaTest {

    private calculadoraDeDistancia calculadora;
    private Ubicacion buenosAires;
    private Ubicacion laPlata;
    private Ubicacion cordoba;

    @BeforeEach
    void setUp() {
        calculadora = new calculadoraDeDistancia();
        buenosAires = new Ubicacion(-34.6037, -58.3816);
        laPlata = new Ubicacion(-34.9205, -57.9536);
        cordoba = new Ubicacion(-31.4201, -64.1888);
    }

    @Test
    void testDistanciaEntreDosUbicaciones() {
        double distancia = calculadora.distanciaEntreDosUbicaciones(buenosAires, laPlata);
        assertTrue(distancia > 40 && distancia < 60); 
    }

    @Test
    void testUbicacionesCercanasA() {
        List<Ubicacion> todas = List.of(laPlata, cordoba);
        List<Ubicacion> cercanas = calculadora.ubicacionesCercanasA(buenosAires, todas, 100); 
        assertEquals(1, cercanas.size());
        assertTrue(cercanas.contains(laPlata));
        assertFalse(cercanas.contains(cordoba));
    }

    @Test
    void testMuestrasCercanasA() {
        Muestra muestraReferencia = mock(Muestra.class);
        Muestra muestra1 = mock(Muestra.class);
        Muestra muestra2 = mock(Muestra.class);

        when(muestraReferencia.getUbicacion()).thenReturn(buenosAires);
        when(muestra1.getUbicacion()).thenReturn(laPlata);
        when(muestra2.getUbicacion()).thenReturn(cordoba);

        List<Muestra> cercanas = calculadora.muestrasCercanasA(muestraReferencia, List.of(muestra1, muestra2), 100);
        assertEquals(1, cercanas.size());
        assertTrue(cercanas.contains(muestra1));
        assertFalse(cercanas.contains(muestra2));
    }
}