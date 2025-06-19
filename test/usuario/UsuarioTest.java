package usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

import muestra.Muestra;
import opinion.Opinion;
import opinion.TipoOpinion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.Sistema;
import ubicacion.Ubicacion;

public class UsuarioTest {

    private Usuario usuario;
    private Sistema sistema;
    private Ubicacion ubicacion;
    private Muestra muestra;
    private Muestra muestra2;
    private Muestra muestra3;
    private TipoOpinion tipoOpinion;
    private TipoUsuario tipoUsuarioExperto;
    private TipoUsuario tipoUsuarioBasico;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        ubicacion = mock(Ubicacion.class);
        muestra = mock(Muestra.class);
        when(muestra.getFechaDeCreacion()).thenReturn(LocalDate.now().minusDays(10));
        muestra2 = mock(Muestra.class);
        when(muestra2.getFechaDeCreacion()).thenReturn(LocalDate.now().minusDays(20));
        sistema = mock(Sistema.class);
        muestra3 = mock(Muestra.class);
        when(muestra3.getFechaDeCreacion()).thenReturn(LocalDate.now().minusDays(40));
        sistema = mock(Sistema.class);
        when(sistema.getMuestras()).thenReturn(List.of(muestra, muestra2, muestra3));
        tipoOpinion = TipoOpinion.CHINCHEFOLIADA;
        tipoUsuarioExperto = mock(TipoUsuario.class);
        when(tipoUsuarioExperto.esExperto()).thenReturn(true);
        tipoUsuarioBasico = mock(TipoUsuario.class);
        when(tipoUsuarioBasico.esExperto()).thenReturn(false);
    }

    @Test
    void testEnviarMuestra() {
        usuario.enviarMuestra("ejemplo.png", ubicacion, tipoOpinion, sistema);
        verify(sistema, times(1)).addMuestra(any(Muestra.class));
    }
    
    @Test
    void testDarOpinion() {
        usuario.darOpinion(tipoOpinion, muestra);
        verify(muestra, times(1)).addOpinion(any(Opinion.class));
        assertEquals(1, usuario.cantidadOpinionesEnviadas(LocalDate.now()));
    }
    
    @Test
    void testCantidadMuestrasEnviadasCuentaDos() {
        assertEquals(2, usuario.cantidadMuestrasEnviadas(sistema));
    }

    @Test
    void testCambiarCategoria() {
        usuario.setTipo(tipoUsuarioExperto);
        assertTrue(usuario.esExperto());
        usuario.cambiarCategoria(LocalDate.now(), sistema);
        verify(tipoUsuarioExperto, times(1)).cambiarCategoria(usuario, LocalDate.now(), sistema);
    }
    
    @Test
    void testEsExperto() {
    	// Setear y consultar como experto
        usuario.setTipo(tipoUsuarioExperto);
        assertTrue(usuario.esExperto());
        verify(tipoUsuarioExperto).esExperto();
     // Setear como básico
        usuario.setTipo(tipoUsuarioBasico);
        assertFalse(usuario.esExperto());
        verify(tipoUsuarioBasico).esExperto();
    }
}
