package muestra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import opinion.Opinion;
import opinion.TipoOpinion;
import ubicacion.Ubicacion;
import usuario.Usuario;


public class MuestraTest {

    @Mock Usuario usuarioMock;
    @Mock Ubicacion ubicacionMock;
    @Mock Opinion opinionMock;
    @Mock Opinion opinionExpertoMock1;
    @Mock Opinion opinionExpertoMock2;
    @Mock TipoOpinion tipoOpinionMock;
    @Mock EstadoMuestra estadoMock;

    Muestra muestra;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(opinionMock.getUsuario()).thenReturn(usuarioMock);
        when(opinionMock.esDeExperto()).thenReturn(false);
        muestra = new Muestra(usuarioMock, "foto1", ubicacionMock, opinionMock);
    }


    @Test
    public void testConstructor() {
        assertEquals(usuarioMock, muestra.getCreated_by());
        assertEquals("foto1", muestra.getFotoMuestra());
        assertEquals(ubicacionMock, muestra.getUbicacion());
        assertEquals(1, muestra.getOpiniones().size());
        assertEquals(opinionMock, muestra.getOpiniones().get(0));
    }
    
    @Test
    public void testGetDateCreatedDevuelveFechaActual() {
        assertEquals(LocalDate.now(), muestra.getDateCreated());
    }
    
    @Test
    public void testGetEstadoMuestraDevuelveInstanciaDeMuestraInicial() {
        assertTrue(muestra.getEstadoMuestra() instanceof MuestraInicial);
    }



    @Test
    public void testAddOpinionAgregaOpinionYLlamaEstado() {
        muestra.setEstadoMuestra(estadoMock);

        Opinion nuevaOpinion = mock(Opinion.class);
        when(nuevaOpinion.getUsuario()).thenReturn(usuarioMock);

        muestra.addOpinion(nuevaOpinion);

        assertEquals(2, muestra.getOpiniones().size());
        verify(estadoMock).puedeOpinar(muestra, usuarioMock);
        verify(estadoMock).evaluarTransicion(muestra);
    }

    @Test
    public void testGetUltimaOpinionDevuelveLaUltima() {
        Usuario otroUsuario = mock(Usuario.class);
        Opinion opinion2 = mock(Opinion.class);
        when(opinion2.getUsuario()).thenReturn(otroUsuario); // diferente usuario
        muestra.addOpinion(opinion2);
        assertEquals(opinion2, muestra.getUltimaOpinion());
    }

    @Test
    public void testTieneOpinionDeExpertoCuandoHay() {
        when(opinionMock.esDeExperto()).thenReturn(true);
        assertTrue(muestra.tieneOpinionDeExperto());
    }

    @Test
    public void testTipoOpinionVerificadaDevuelveTipoCuandoHayDosExpertosIguales() {
        Usuario experto1 = mock(Usuario.class);
        Usuario experto2 = mock(Usuario.class);

        Opinion opinionExperto1 = mock(Opinion.class);
        when(opinionExperto1.getUsuario()).thenReturn(experto1);
        when(opinionExperto1.esDeExperto()).thenReturn(true);
        when(opinionExperto1.getTipoOpinion()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
        when(experto1.esExperto()).thenReturn(true);
        when(experto2.esExperto()).thenReturn(true);


        Opinion opinionExperto2 = mock(Opinion.class);
        when(opinionExperto2.getUsuario()).thenReturn(experto2);
        when(opinionExperto2.esDeExperto()).thenReturn(true);
        when(opinionExperto2.getTipoOpinion()).thenReturn(TipoOpinion.CHINCHEFOLIADA);

        muestra.addOpinion(opinionExperto1);
        muestra.addOpinion(opinionExperto2);

        Optional<TipoOpinion> resultado = muestra.tipoOpinionVerificada();
        assertTrue(resultado.isPresent());
        assertEquals(TipoOpinion.CHINCHEFOLIADA, resultado.get());
    }


    @Test
    public void testResultadoActualDelegadoAlEstado() {
        muestra.setEstadoMuestra(estadoMock);
        when(estadoMock.resultadoActual(muestra)).thenReturn(TipoOpinion.VINCHUCA);
        assertEquals(TipoOpinion.VINCHUCA, muestra.resultadoActual());
    }
}
