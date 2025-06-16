package muestra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import opinion.Opinion;
import opinion.TipoOpinion;
import usuario.Usuario;

public class MuestraInicialTest {

    @Mock Muestra muestraMock;
    @Mock Usuario usuarioMock;
    @Mock Opinion opinionMock;

    EstadoMuestra estado;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        estado = new MuestraInicial();
    }

    @Test
    void testPuedeOpinarLanzaExcepcionSiUsuarioYaOpino() {
        when(muestraMock.getCreated_by()).thenReturn(new Usuario());
        when(opinionMock.getUsuario()).thenReturn(usuarioMock);
        when(muestraMock.getOpiniones()).thenReturn(List.of(opinionMock));

        assertThrows(IllegalArgumentException.class, () -> {
            estado.puedeOpinar(muestraMock, usuarioMock);
        });
    }

    @Test
    void testEvaluarTransicionCambiaEstadoSiHayOpinionExperta() {
        when(muestraMock.tieneOpinionDeExperto()).thenReturn(true);

        estado.evaluarTransicion(muestraMock);

        verify(muestraMock).setEstadoMuestra(any(MuestraConOpinionExperta.class));
    }

    @Test
    void testResultadoActualDevuelveTipoMasFrecuente() {
        Opinion op1 = mock(Opinion.class);
        Opinion op2 = mock(Opinion.class);
        when(op1.getTipoOpinion()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
        when(op2.getTipoOpinion()).thenReturn(TipoOpinion.CHINCHEFOLIADA);

        when(muestraMock.getOpiniones()).thenReturn(List.of(op1, op2));

        TipoOpinion resultado = estado.resultadoActual(muestraMock);

        assertEquals(TipoOpinion.CHINCHEFOLIADA, resultado);
    }

    @Test
    void testResultadoActualDevuelveNoDefinidoSiHayEmpate() {
        Opinion op1 = mock(Opinion.class);
        Opinion op2 = mock(Opinion.class);
        when(op1.getTipoOpinion()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
        when(op2.getTipoOpinion()).thenReturn(TipoOpinion.VINCHUCA);

        when(muestraMock.getOpiniones()).thenReturn(List.of(op1, op2));

        TipoOpinion resultado = estado.resultadoActual(muestraMock);

        assertEquals(TipoOpinion.NO_DEFINIDO, resultado);
    }
}
