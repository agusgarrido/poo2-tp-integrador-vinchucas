package muestra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import opinion.Opinion;
import opinion.TipoOpinion;
import usuario.Usuario;

public class EstadoMuestraTest {

    EstadoMuestra estado;

    Muestra muestraMock;
    Usuario usuarioMock;
    Usuario otroUsuarioMock;
    Opinion opinionMock;

    @BeforeEach
    void setUp() {
        estado = new EstadoMuestra() {
            @Override
            public void evaluarTransicion(Muestra muestra) {
                // No hace nada
            }

            @Override
            public TipoOpinion resultadoActual(Muestra muestra) {
                return TipoOpinion.NO_DEFINIDO;
            }
        };

        muestraMock = Mockito.mock(Muestra.class);
        usuarioMock = Mockito.mock(Usuario.class);
        otroUsuarioMock = Mockito.mock(Usuario.class);
        opinionMock = Mockito.mock(Opinion.class);
    }

    @Test
    void testNivelDeValidacionDevuelveVotadaPorDefecto() {
        assertEquals(TipoEstadoMuestra.VOTADA, estado.nivelDeValidacion());
    }

    @Test
    void testPuedeOpinarLanzaExcepcionSiUsuarioEsElCreador() {
        Mockito.when(muestraMock.getCreated_by()).thenReturn(usuarioMock);

        assertThrows(IllegalArgumentException.class, () -> {
            estado.puedeOpinar(muestraMock, usuarioMock);
        });
    }

    @Test
    void testPuedeOpinarLanzaExcepcionSiUsuarioYaOpino() {
        Mockito.when(muestraMock.getCreated_by()).thenReturn(otroUsuarioMock);
        Mockito.when(opinionMock.getUsuario()).thenReturn(usuarioMock);
        Mockito.when(muestraMock.getOpiniones()).thenReturn(List.of(opinionMock));

        assertThrows(IllegalArgumentException.class, () -> {
            estado.puedeOpinar(muestraMock, usuarioMock);
        });
    }

    @Test
    void testPuedeOpinarPermiteSiUsuarioNoEsCreadorNiOpinoAntes() {
        Mockito.when(muestraMock.getCreated_by()).thenReturn(otroUsuarioMock); 
        Mockito.when(opinionMock.getUsuario()).thenReturn(otroUsuarioMock); 
        Mockito.when(muestraMock.getOpiniones()).thenReturn(List.of(opinionMock));
        estado.puedeOpinar(muestraMock, usuarioMock);
    }
}
