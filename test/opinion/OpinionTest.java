package opinion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import usuario.TipoUsuario;
import usuario.Usuario;

public class OpinionTest {

    private TipoUsuario tipoUsuarioMock;
    private Usuario usuarioMock;
    private Opinion opinion;

    @BeforeEach
    void setUp() {
        tipoUsuarioMock = mock(TipoUsuario.class);
        usuarioMock = mock(Usuario.class);
        opinion = new Opinion(TipoOpinion.VINCHUCA, tipoUsuarioMock, usuarioMock);
    }

    @Test
    void testGetTipoOpinion() {
        assertEquals(TipoOpinion.VINCHUCA, opinion.getTipoOpinion());
    }

    @Test
    void testGetTipoUsuario() {
        assertEquals(tipoUsuarioMock, opinion.getTipoUsuario());
    }

    @Test
    void testGetFecha() {
        assertEquals(LocalDate.now(), opinion.getFecha());
    }

    @Test
    void testGetUsuario() {
        assertEquals(usuarioMock, opinion.getUsuario());
    }

    @Test
    void testEsDeExpertoCuandoEsExperto() {
        when(tipoUsuarioMock.esExperto()).thenReturn(true);
        assertTrue(opinion.esDeExperto());
    }

    @Test
    void testEsDeExpertoCuandoNoEsExperto() {
        when(tipoUsuarioMock.esExperto()).thenReturn(false);
        assertFalse(opinion.esDeExperto());
    }
}