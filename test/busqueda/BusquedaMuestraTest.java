package busqueda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import muestra.Muestra;
import muestra.TipoEstadoMuestra;
import opinion.TipoOpinion;

public class BusquedaMuestraTest {
    private Muestra muestra;
    private LocalDate fechaEsperada;
    private LocalDate fechaDistinta;
    private Muestra muestraExtra1;
    private Muestra muestraExtra2;

    @BeforeEach
    void setUp() {
        muestra = mock(Muestra.class);
        muestraExtra1 = mock(Muestra.class);
        muestraExtra2 = mock(Muestra.class);
        fechaEsperada = LocalDate.of(2024, 6, 10);
        fechaDistinta = LocalDate.of(2024, 6, 1);
        when(muestra.getFechaDeCreacion()).thenReturn(fechaEsperada);
        when(muestra.getFechaUltimaVotacion()).thenReturn(fechaEsperada);
        when(muestra.nivelDeValidacion()).thenReturn(TipoEstadoMuestra.VERIFICADA);
        when(muestra.resultadoActual()).thenReturn(TipoOpinion.VINCHUCA);

        when(muestraExtra1.getFechaDeCreacion()).thenReturn(fechaDistinta);
        when(muestraExtra2.getFechaDeCreacion()).thenReturn(fechaEsperada);
    }
    
    @Test
    void testBuscarMuestrasFiltraCorrectamentePorFechaDeCreacion() {
        List<Muestra> lista = List.of(muestra, muestraExtra1, muestraExtra2);
        BusquedaMuestra filtro = new BusquedaFechaCreacionMuestra(fechaEsperada);
        List<Muestra> resultado = filtro.buscarMuestras(lista);
        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(muestra));
        assertTrue(resultado.contains(muestraExtra2));
        assertFalse(resultado.contains(muestraExtra1));
    }
    
    @Test
    void testBusquedaFechaCreacionCumple() {
        BusquedaMuestra filtro = new BusquedaFechaCreacionMuestra(fechaEsperada);
        assertTrue(filtro.aplicaBusqueda(muestra));
    }

    @Test
    void testBusquedaFechaCreacionNoCumple() {
        BusquedaMuestra filtro = new BusquedaFechaCreacionMuestra(fechaDistinta);
        assertFalse(filtro.aplicaBusqueda(muestra));
    }

    @Test
    void testBusquedaFechaUltimaVotacionCumple() {
        BusquedaMuestra filtro = new BusquedaFechaUltimaVotacionMuestra(fechaEsperada);
        assertTrue(filtro.aplicaBusqueda(muestra));
    }

    @Test
    void testBusquedaFechaUltimaVotacionNoCumple() {
        BusquedaMuestra filtro = new BusquedaFechaUltimaVotacionMuestra(fechaDistinta);
        assertFalse(filtro.aplicaBusqueda(muestra));
    }

    @Test
    void testBusquedaNivelVerificacionCumple() {
        BusquedaMuestra filtro = new BusquedaNivelVerificacionMuestra(TipoEstadoMuestra.VERIFICADA);
        assertTrue(filtro.aplicaBusqueda(muestra));
    }

    @Test
    void testBusquedaNivelVerificacionNoCumple() {
        BusquedaMuestra filtro = new BusquedaNivelVerificacionMuestra(TipoEstadoMuestra.VOTADA);
        assertFalse(filtro.aplicaBusqueda(muestra));
    }

    @Test
    void testBusquedaTipoInsectoCumple() {
        BusquedaMuestra filtro = new BusquedaTipoInsectoMuestra(TipoOpinion.VINCHUCA);
        assertTrue(filtro.aplicaBusqueda(muestra));
    }
    
    @Test
    void testBusquedaTipoInsectoNoCumple() {
        BusquedaMuestra filtro = new BusquedaTipoInsectoMuestra(TipoOpinion.CHINCHEFOLIADA);
        assertFalse(filtro.aplicaBusqueda(muestra));
    }
 
}