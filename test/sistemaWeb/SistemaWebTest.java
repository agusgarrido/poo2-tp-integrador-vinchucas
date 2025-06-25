package sistemaWeb;

import muestra.Muestra;
import opinion.Opinion;
import busqueda.BusquedaMuestra;
import usuario.Usuario;
import zonaDeCobertura.ZonaDeCobertura;
import organizacion.Organizacion;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class SistemaWebTest {

    private SistemaWeb sistema;
    private Muestra muestra;
    private ZonaDeCobertura zona;
    private Usuario usuario;
    private Opinion opinion;
    private Organizacion organizacion;

    @BeforeEach
    void setUp() {
        sistema = new SistemaWeb();
        muestra = mock(Muestra.class);
        zona = mock(ZonaDeCobertura.class);
        usuario = mock(Usuario.class);
        opinion = mock(Opinion.class);
        organizacion = mock(Organizacion.class);
    }

    @Test
    void testAddMuestraAgregaYNotifica() {
        sistema.getZonas().add(zona);
        when(zona.contiene(muestra)).thenReturn(true);

        sistema.addMuestra(muestra);

        assertTrue(sistema.getMuestras().contains(muestra));
        verify(zona, times(2)).notificarNuevaMuestra(muestra); // Se notifica 2 veces si zona.contiene() es true
    }

    @Test
    void testAgregarOpinionAMuestraValidaCuandoVerificada() {
        muestra = mock(Muestra.class, RETURNS_DEEP_STUBS);
        muestra.addOpinion(opinion);

        when(muestra.getEstadoMuestra()).thenReturn(mock(muestra.MuestraVerificada.class));

        sistema.getZonas().add(zona);
        when(zona.contiene(muestra)).thenReturn(true);

        sistema.agregarOpinionAMuestra(muestra, opinion);

        verify(zona, times(1)).notificarMuestraValidada(muestra);
    }

    @Test
    void testBuscarMuestrasUsaBusqueda() {
        BusquedaMuestra busqueda = mock(BusquedaMuestra.class);
        List<Muestra> resultadoEsperado = List.of(muestra);

        when(busqueda.buscarMuestras(sistema.getMuestras())).thenReturn(resultadoEsperado);

        List<Muestra> resultado = sistema.buscarMuestras(busqueda);

        assertEquals(resultadoEsperado, resultado);
        verify(busqueda).buscarMuestras(sistema.getMuestras());
    }

    @Test
    void testActualizarUsuariosCambiaCategoria() {
        sistema.getUsuarios().add(usuario);

        sistema.actualizarUsuarios();

        verify(usuario).cambiarCategoria(any());
    }

    @Test
    void testGeters() {
        assertNotNull(sistema.getMuestras());
        assertNotNull(sistema.getUsuarios());
        assertNotNull(sistema.getZonas());
        assertNotNull(sistema.getOrganizaciones());
    }
}