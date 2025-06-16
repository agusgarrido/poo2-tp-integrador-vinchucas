package organizacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.Muestra;
import ubicacion.Ubicacion;
import zonaDeCobertura.ZonaDeCobertura;

class OrganizacionTest {

	// SUT
	private Organizacion organizacion1;
	
	// DOC
	private ZonaDeCobertura zona1;
	private FuncionalidadExterna funcNuevaMuestra;
	private FuncionalidadExterna funcMuestraValidada;
	private Muestra muestra1;
	private Ubicacion ubicacion1;

	@BeforeEach
	void setUp() {
		funcNuevaMuestra = mock(FuncionalidadExterna.class);
		funcMuestraValidada = mock(FuncionalidadExterna.class);
		zona1 = mock(ZonaDeCobertura.class);
		muestra1 = mock(Muestra.class);
		ubicacion1 = mock(Ubicacion.class);

		organizacion1 = new Organizacion(ubicacion1, "Educativa", 50);
	}

	@Test
	void testNuevaMuestraLlamaAFuncionalidadExterna() {
		organizacion1.setFuncionalidadNuevaMuestra(funcNuevaMuestra);
		organizacion1.nuevaMuestra(zona1, muestra1);

		verify(funcNuevaMuestra).nuevoEvento(organizacion1, zona1, muestra1);
	}

	@Test
	void testMuestraValidadaLlamaAFuncionalidadExterna() {
		organizacion1.setFuncionalidadMuestraValidada(funcMuestraValidada);
		organizacion1.muestraValidada(zona1, muestra1);

		verify(funcMuestraValidada).nuevoEvento(organizacion1, zona1, muestra1);
	}

	@Test
	void testRegistrarseEnZona() {
		organizacion1.registrarseEnZona(zona1);

		verify(zona1).registrarOrganizacion(organizacion1);
	}

	@Test
	void testDarseDeBajaEnZona() {
		organizacion1.darseDeBajaEnZona(zona1);

		verify(zona1).removerOrganizacion(organizacion1);
	}

	@Test
	void testGetters() {
		assertEquals(ubicacion1, organizacion1.getUbicacion());
		assertEquals("Educativa", organizacion1.getTipoOrganizacion());
		assertEquals(50, organizacion1.getCantidadEmpleados());
	}
}