package busqueda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import muestra.Muestra;
import java.util.List;
class BusquedaCompuestaMuestraTest {

	// SUT
	BusquedaMuestra busqueda1;
	BusquedaMuestra busqueda2;
	
	// DOC
    Muestra muestra1;
    Muestra muestra2;
    Muestra muestra3;
    List<Muestra> todas;

    @BeforeEach
    void setUp() {
        muestra1 = mock(Muestra.class);
        muestra2 = mock(Muestra.class);
        muestra3 = mock(Muestra.class);

        busqueda1 = mock(BusquedaMuestra.class);
        busqueda2 = mock(BusquedaMuestra.class);

        todas = List.of(muestra1, muestra2, muestra3);
    }

    @Test
    void testBusquedaCompuestaOrUneResultadosSinRepetir() {
        when(busqueda1.buscarMuestras(todas)).thenReturn(List.of(muestra1, muestra2));
        when(busqueda2.buscarMuestras(todas)).thenReturn(List.of(muestra2, muestra3));

        BusquedaCompuestaMuestra or = new BusquedaCompuestaOrMuestra();
        or.addBusqueda(busqueda1);
        or.addBusqueda(busqueda2);

        List<Muestra> resultado = or.buscarMuestras(todas);

        assertTrue(resultado.containsAll(List.of(muestra1, muestra2, muestra3)));
        assertEquals(3, resultado.size());
    }

    @Test
    void testBusquedaCompuestaAndDevuelveInterseccion() {
        when(busqueda1.buscarMuestras(todas)).thenReturn(List.of(muestra1, muestra2));
        when(busqueda2.buscarMuestras(todas)).thenReturn(List.of(muestra2, muestra3));

        BusquedaCompuestaMuestra and = new BusquedaCompuestaAndMuestra();
        and.addBusqueda(busqueda1);
        and.addBusqueda(busqueda2);

        List<Muestra> resultado = and.buscarMuestras(todas);

        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(muestra2));
    }

    @Test
    void testAddYRemoveBusquedaEnCualquierCombinada() {
        BusquedaCompuestaMuestra and = new BusquedaCompuestaAndMuestra();

        and.addBusqueda(busqueda1);
        assertEquals(1, and.listaBusquedas.size());

        and.removeBusquea(busqueda1);
        assertEquals(0, and.listaBusquedas.size());
    }

    @Test
    void testInvocacionDeBuscarMuestrasDesdeCompuesta() {
        when(busqueda1.buscarMuestras(todas)).thenReturn(List.of(muestra1));
        BusquedaCompuestaMuestra or = new BusquedaCompuestaOrMuestra();
        or.addBusqueda(busqueda1);

        or.buscarMuestras(todas);

        verify(busqueda1).buscarMuestras(todas);
    }
    
    @Test
    void testAplicaBusquedaSiempreDevuelveTrue() {
        BusquedaCompuestaMuestra busqueda = new BusquedaCompuestaOrMuestra();
        Muestra muestraMock = mock(Muestra.class);

        assertTrue(busqueda.aplicaBusqueda(muestraMock));
    }

}