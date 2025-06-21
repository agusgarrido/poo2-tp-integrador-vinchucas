package usuario;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class UsuarioExpertoTest {
    private UsuarioExperto tipoExperto;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
    	tipoExperto = new UsuarioExperto();
        usuario = mock(Usuario.class);
    }

    @Test
    void testEsExperto() {
        assertTrue(tipoExperto.esExperto());
    }
    
    @Test
    void testCambiarCategoriaNoCumpleRequisitos() {
        when(usuario.cantidadMuestrasEnviadas()).thenReturn(5);
        tipoExperto.cambiarCategoria(usuario, LocalDate.now());
        verify(usuario).setTipo(any(UsuarioBasico.class));
    }

    @Test
    void testCambiarCategoriaCumpleRequisitos() {
        when(usuario.cantidadMuestrasEnviadas()).thenReturn(40);
        when(usuario.cantidadOpinionesEnviadas(LocalDate.now())).thenReturn(40);
        tipoExperto.cambiarCategoria(usuario, LocalDate.now());
        verify(usuario, never()).setTipo(any(UsuarioBasico.class)); 
    }
}
