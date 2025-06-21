package usuario;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class UsuarioEspecialistaTest {
    
    private UsuarioEspecialista tipoEspecialista;
    private Usuario usuario;
    @BeforeEach
    void setUp() {
        tipoEspecialista = new UsuarioEspecialista();
        usuario = mock(Usuario.class);
        mock(UnsupportedOperationException.class);
    }

    @Test
    void testNoPuedeCambiarDeCategoria() {
        UnsupportedOperationException ex = assertThrows(UnsupportedOperationException.class, () -> {
            tipoEspecialista.cambiarCategoria(usuario, LocalDate.now());
        });
        assertTrue(ex.getMessage().contains("Un usuario especialista no puede cambiar de categoría."));
    }
}