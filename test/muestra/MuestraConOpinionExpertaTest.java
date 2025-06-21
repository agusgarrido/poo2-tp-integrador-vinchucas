package muestra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import opinion.Opinion;
import opinion.TipoOpinion;
import sistemaWeb.SistemaWeb;
import usuario.Usuario;

public class MuestraConOpinionExpertaTest {


    @Mock Muestra muestraMock;
    @Mock Usuario usuarioMock;
    @Mock TipoOpinion tipoOpinionMock;
    @Mock SistemaWeb sistemaWeb;

    EstadoMuestra estado;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        estado = new MuestraConOpinionExperta();
    }

    @Test
    void testPuedeOpinarLanzaExcepcionSiUsuarioNoEsExperto() {
        when(muestraMock.getCreated_by()).thenReturn(new Usuario(sistemaWeb));
        when(muestraMock.getOpiniones()).thenReturn(List.of());
        when(usuarioMock.esExperto()).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> {
            estado.puedeOpinar(muestraMock, usuarioMock);
        });
    }

    @Test
    void testEvaluarTransicionCambiaEstadoSiHayTipoVerificado() {
        when(muestraMock.tipoOpinionMasVotada()).thenReturn(Optional.of(TipoOpinion.VINCHUCA));

        estado.evaluarTransicion(muestraMock);

        verify(muestraMock).setEstadoMuestra(any(MuestraVerificada.class));
    }

    @Test
    void testResultadoActualDevuelveTipoDeUltimaOpinion() {
        Opinion ultimaOpinion = mock(Opinion.class);
        when(ultimaOpinion.getTipoOpinion()).thenReturn(TipoOpinion.VINCHUCA);
        when(muestraMock.getUltimaOpinion()).thenReturn(ultimaOpinion);

        assertEquals(TipoOpinion.VINCHUCA, estado.resultadoActual(muestraMock));
    }
}
