package usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.Muestra;
import sistema.Sistema;

public class UsuarioBasicoTest {

    private UsuarioBasico tipoBasico;
    private Muestra muestra1;
    private Muestra muestra2;
    private Muestra muestra3;
    private Sistema sistema;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        tipoBasico = new UsuarioBasico();

        muestra1 = mock(Muestra.class);
        when(muestra1.getFechaDeCreacion()).thenReturn(LocalDate.now().minusDays(5));
        muestra2 = mock(Muestra.class);
        when(muestra2.getFechaDeCreacion()).thenReturn(LocalDate.now().minusDays(10));
        muestra3 = mock(Muestra.class);
        when(muestra3.getFechaDeCreacion()).thenReturn(LocalDate.now().minusDays(40));

        sistema = mock(Sistema.class);
        when(sistema.getMuestras()).thenReturn(List.of(muestra1, muestra2, muestra3));
        
        usuario = mock(Usuario.class);
    }

    @Test
    void testEsExperto() {
        assertFalse(tipoBasico.esExperto());
    }
    
    @Test
    void testCambiarCategoriaNoCumpleRequisitos() {
        when(usuario.cantidadMuestrasEnviadas(sistema)).thenReturn(5);
        tipoBasico.cambiarCategoria(usuario, LocalDate.now(), sistema);
        verify(usuario, never()).setTipo(any(UsuarioExperto.class));
    }

    @Test
    void testCambiarCategoriaCumpleRequisitos() {
        when(usuario.cantidadMuestrasEnviadas(sistema)).thenReturn(40);
        when(usuario.cantidadOpinionesEnviadas(LocalDate.now())).thenReturn(40);
        tipoBasico.cambiarCategoria(usuario, LocalDate.now(), sistema);
        verify(usuario).setTipo(any(UsuarioExperto.class)); 
    }
}

