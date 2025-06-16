package muestra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import opinion.TipoOpinion;
import usuario.Usuario;

public class MuestraVerificadaTest {

    EstadoMuestra estado;

    @Mock Muestra muestraMock;
    @Mock Usuario usuarioMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        estado = new MuestraVerificada(TipoOpinion.CHINCHEFOLIADA);
    }

    @Test
    void testPuedeOpinarLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            estado.puedeOpinar(muestraMock, usuarioMock);
        });
    }

    @Test
    void testEvaluarTransicionNoHaceNada() {
        estado.evaluarTransicion(muestraMock);
        // No hay verificación porque no hace nada.
    }

    @Test
    void testResultadoActualDevuelveElTipoFinal() {
        assertEquals(TipoOpinion.CHINCHEFOLIADA, estado.resultadoActual(muestraMock));
    }
}
