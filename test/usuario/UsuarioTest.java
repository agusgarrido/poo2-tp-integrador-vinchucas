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
import sistemaWeb.SistemaWeb;
import ubicacion.Ubicacion;

public class UsuarioTest {

    private Usuario usuario;
    private SistemaWeb sistema;
    private Ubicacion ubicacion;
    private Muestra muestra;
    private Muestra muestra2;
    private Muestra muestra3;
    private TipoOpinion tipoOpinion;
    private TipoUsuario tipoUsuarioExperto;
    private TipoUsuario tipoUsuarioBasico;


    @BeforeEach
    void setUp() {
        sistema = mock(SistemaWeb.class);             
        usuario = new Usuario(sistema);
        ubicacion = mock(Ubicacion.class);
        muestra = mock(Muestra.class);
        when(muestra.getFechaDeCreacion()).thenReturn(LocalDate.now().minusDays(10));
        muestra2 = mock(Muestra.class);
        when(muestra2.getFechaDeCreacion()).thenReturn(LocalDate.now().minusDays(20));
        muestra3 = mock(Muestra.class);
        when(muestra3.getFechaDeCreacion()).thenReturn(LocalDate.now().minusDays(40));
        when(sistema.getMuestras()).thenReturn(List.of(muestra, muestra2, muestra3));
        tipoOpinion = TipoOpinion.CHINCHEFOLIADA;
        tipoUsuarioExperto = mock(TipoUsuario.class);
        when(tipoUsuarioExperto.esExperto()).thenReturn(true);
        tipoUsuarioBasico = mock(TipoUsuario.class);
        when(tipoUsuarioBasico.esExperto()).thenReturn(false);
    }


    @Test
    void testEnviarMuestra() {
        usuario.enviarMuestra("ejemplo.png", ubicacion, tipoOpinion);
        verify(sistema, times(1)).addMuestra(any(Muestra.class));
    }
    
    @Test
    void testDarOpinion() {
        doAnswer(invoc -> null).when(sistema).agregarOpinionAMuestra(eq(muestra), any(Opinion.class));
        usuario.darOpinion(tipoOpinion, muestra);
        verify(sistema, times(1)).agregarOpinionAMuestra(eq(muestra), any(Opinion.class));
        assertEquals(1, usuario.cantidadOpinionesEnviadas(LocalDate.now()));
    }
    
    @Test
    void testCantidadMuestrasEnviadasCuentaDos() {
        assertEquals(2, usuario.cantidadMuestrasEnviadas());
    }

    @Test
    void testCambiarCategoria() {
        usuario.setTipo(tipoUsuarioExperto);
        assertTrue(usuario.esExperto());
        usuario.cambiarCategoria(LocalDate.now());
        verify(tipoUsuarioExperto, times(1)).cambiarCategoria(usuario, LocalDate.now());
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
    
    @Test
    void testCantidadOpinionesEnviadasEnUltimos30Dias() {
        // Opinión dentro de los últimos 30 días
        Opinion opinionReciente = mock(Opinion.class);
        when(opinionReciente.getFecha()).thenReturn(LocalDate.now().minusDays(5));

        // Opinión fuera del rango (más de 30 días atrás)
        Opinion opinionAntigua = mock(Opinion.class);
        when(opinionAntigua.getFecha()).thenReturn(LocalDate.now().minusDays(40));
        
        // Agregamos las opiniones 
        usuario.darOpinion(tipoOpinion, muestra);
        usuario.darOpinion(tipoOpinion, muestra); 

        int cantidad = usuario.cantidadOpinionesEnviadas(LocalDate.now());
        assertEquals(2, cantidad); 
    }
}
