package zonaDeCobertura;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.Muestra;
import organizacion.Organizacion;
import ubicacion.Ubicacion;
import zonaDeCobertura.ZonaDeCobertura;

class ZonaDeCoberturaTest {
	// SUT
	private ZonaDeCobertura bernal;
	private ZonaDeCobertura quilmes;
	private ZonaDeCobertura laPlata;

	// DOC
	private Organizacion organizacion1;
	private Organizacion organizacion2;

	private Muestra muestra1;
	private Muestra muestra2;

	private Ubicacion ubicacionBernal;
	private Ubicacion ubicacionQuilmes;
	private Ubicacion ubicacionLaPlata;

	@BeforeEach
	void setUp() {
		// Instanciación de Bernal con su stub de Ubicación
		ubicacionBernal = mock(Ubicacion.class);
		when(ubicacionBernal.getLatitud()).thenReturn(-34.7061);
		when(ubicacionBernal.getLongitud()).thenReturn(-58.2816);
		bernal = new ZonaDeCobertura("Bernal", ubicacionBernal, 5);

		// Instanciación de Quilmes con su stub de Ubicación
		ubicacionQuilmes = mock(Ubicacion.class);
		when(ubicacionQuilmes.getLatitud()).thenReturn(-34.7205);
		when(ubicacionQuilmes.getLongitud()).thenReturn(-58.2546);
		quilmes = new ZonaDeCobertura("Quilmes", ubicacionQuilmes, 4);

		// Instanciación de La Plata con su stub de Ubicación
		ubicacionLaPlata = mock(Ubicacion.class);
		when(ubicacionLaPlata.getLatitud()).thenReturn(-34.9214);
		when(ubicacionLaPlata.getLongitud()).thenReturn(-57.9544);
		laPlata = new ZonaDeCobertura("La Plata", ubicacionLaPlata, 3);

		organizacion1 = mock(Organizacion.class);
		organizacion2 = mock(Organizacion.class);

		muestra1 = mock(Muestra.class);
		when(muestra1.getUbicacion()).thenReturn(ubicacionBernal);
		muestra2 = mock(Muestra.class);
		when(muestra2.getUbicacion()).thenReturn(ubicacionLaPlata);
	}

	@Test
	void testRegistrarYRemoverOrganizaciones() {
		bernal.registrarOrganizacion(organizacion1);
		bernal.registrarOrganizacion(organizacion2);

		// Verificar que contiene a ambas organizaciones
		assertTrue(bernal.getOrganizacionesRegistradas().containsAll(List.of(organizacion1, organizacion2)));

		bernal.removerOrganizacion(organizacion1);

		// Verificar que quitó la organiación 1
		assertFalse(bernal.getOrganizacionesRegistradas().contains(organizacion1));
	}

	@Test
	void testNotificarNuevaMuestra() {
		quilmes.registrarOrganizacion(organizacion1);
		quilmes.registrarMuestra(muestra1);
		verify(organizacion1).nuevaMuestra(quilmes, muestra1);
	}
	
	@Test
	void testFallidoNotificarNuevaMuestra() {
		bernal.registrarOrganizacion(organizacion1);
		bernal.registrarMuestra(muestra2);
		verify(organizacion1, never()).nuevaMuestra(bernal, muestra2);
	}

	@Test
	void testNotificarMuestraVerificada() {
		laPlata.registrarOrganizacion(organizacion2);
		laPlata.notificarMuestraValidada(muestra2);
		verify(organizacion2).muestraValidada(laPlata, muestra2);
	}

	@Test
	void testZonasSolapadas() {
		// Armo una lista de zonas
		List<ZonaDeCobertura> zonas = List.of(quilmes, laPlata);

		// Reviso cuáles se solapan con Bernal
		List<ZonaDeCobertura> resultado = bernal.zonasSolapadas(zonas);
		assertTrue(resultado.contains(quilmes), "ERROR: Bernal y Quilmes se solapan.");
		assertFalse(resultado.contains(laPlata), "ERROR: Bernal y La Plata NO se solapan.");
	}

	@Test
	void testGetters() {
		assertEquals("Bernal", bernal.getNombre());
		assertEquals(ubicacionBernal, bernal.getEpicentro());
		assertNotEquals(3, bernal.getRadio());
	}
	
	@Test
	void testContieneCuandoLaMuestraEstaDentroDelRadio() {
	    assertTrue(bernal.contiene(muestra1), "La muestra debería estar contenida en la zona de Bernal");
	}

	@Test
	void testContieneCuandoLaMuestraEstaFueraDelRadio() {
	    assertFalse(bernal.contiene(muestra2), "La muestra no debería estar contenida en la zona de Bernal");
	}
}
