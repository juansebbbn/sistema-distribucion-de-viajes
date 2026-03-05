package com.juan.appgestioninventario_viajes.core.servicios;
import com.juan.appgestioninventario_viajes.core.modelos.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class Unit_test_algorithim_asign {
    private Implementacion_algoritmo_asignacion implementacion;
    @BeforeEach
    void setUp() {
        implementacion = new Implementacion_algoritmo_asignacion();
    }
    @Test
    void testMapearSucursalesConViajes_CasoBasico() {
        Sucursal sucursal1 = crearSucursal(1L, "Sucursal A");
        Sucursal sucursal2 = crearSucursal(2L, "Sucursal B");
        ViajeDiario viaje1 = crearViajeDiario(1L, sucursal1, sucursal2, 100.0);
        ViajeDiario viaje2 = crearViajeDiario(2L, sucursal1, sucursal2, 150.0);
        ViajeDiario viaje3 = crearViajeDiario(3L, sucursal2, sucursal1, 200.0);
        List<ViajeDiario> viajes = Arrays.asList(viaje1, viaje2, viaje3);
        List<Sucursal> sucursales = Arrays.asList(sucursal1, sucursal2);
        Map<Sucursal, List<ViajeDiario>> resultado = implementacion.mapear_sucursales_con_viajes(viajes, sucursales);
        assertEquals(2, resultado.size());
        assertTrue(resultado.containsKey(sucursal1));
        assertTrue(resultado.containsKey(sucursal2));
        assertEquals(2, resultado.get(sucursal1).size());
        assertEquals(1, resultado.get(sucursal2).size());
        assertTrue(resultado.get(sucursal1).contains(viaje1));
        assertTrue(resultado.get(sucursal1).contains(viaje2));
        assertTrue(resultado.get(sucursal2).contains(viaje3));
    }
    @Test
    void testMapearSucursalesConViajes_ViajesConOrigenNoExistente() {
        Sucursal sucursal1 = crearSucursal(1L, "Sucursal A");
        Sucursal sucursalOrigenNoExistente = crearSucursal(99L, "Sucursal No Existente");
        ViajeDiario viaje1 = crearViajeDiario(1L, sucursal1, sucursalOrigenNoExistente, 100.0);
        ViajeDiario viaje2 = crearViajeDiario(2L, sucursalOrigenNoExistente, sucursal1, 150.0);
        List<ViajeDiario> viajes = Arrays.asList(viaje1, viaje2);
        List<Sucursal> sucursales = Arrays.asList(sucursal1);
        Map<Sucursal, List<ViajeDiario>> resultado = implementacion.mapear_sucursales_con_viajes(viajes, sucursales);
        assertEquals(1, resultado.size());
        assertTrue(resultado.containsKey(sucursal1));
        assertEquals(1, resultado.get(sucursal1).size()); 
        assertTrue(resultado.get(sucursal1).contains(viaje1));
    }
    @Test
    void testMapearSucursalesConViajes_ListasVacias() {
        List<ViajeDiario> viajes = new ArrayList<>();
        List<Sucursal> sucursales = new ArrayList<>();
        Map<Sucursal, List<ViajeDiario>> resultado = implementacion.mapear_sucursales_con_viajes(viajes, sucursales);
        assertTrue(resultado.isEmpty());
    }
    @Test
    void testDesignarVehiculoConductor_CasoSimple() {
        Sucursal sucursalOrigen = crearSucursalConRecursos(1L, "Sucursal A", 1000.0, 5);
        Sucursal sucursalDestino = crearSucursal(2L, "Sucursal B");
        ViajeDiario viaje1 = crearViajeDiario(1L, sucursalOrigen, sucursalDestino, 300.0);
        ViajeDiario viaje2 = crearViajeDiario(2L, sucursalOrigen, sucursalDestino, 200.0);
        HashMap<Sucursal, List<ViajeDiario>> mapeo = new HashMap<>();
        mapeo.put(sucursalOrigen, Arrays.asList(viaje1, viaje2));
        List<HojaDeRuta> resultado = implementacion.designar_vehiculo_conductor(mapeo);
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        HojaDeRuta hojaDeRuta = resultado.get(0);
        assertNotNull(hojaDeRuta.getConductor());
        assertNotNull(hojaDeRuta.getVehiculo());
        assertEquals(2, hojaDeRuta.getViajes_designados().size());
        assertFalse(sucursalOrigen.getConductores_en_sucursal().contains(hojaDeRuta.getConductor()));
        assertTrue(sucursalDestino.getConductores_en_sucursal().contains(hojaDeRuta.getConductor()));
    }
    @Test
    void testDesignarVehiculoConductor_MultiplesVehiculosPorPeso() {
        Sucursal sucursalOrigen = crearSucursalConMultiplesVehiculos(1L);
        Sucursal sucursalDestino = crearSucursal(2L, "Sucursal B");
        ViajeDiario viaje1 = crearViajeDiario(1L, sucursalOrigen, sucursalDestino, 800.0); 
        ViajeDiario viaje2 = crearViajeDiario(2L, sucursalOrigen, sucursalDestino, 300.0);
        HashMap<Sucursal, List<ViajeDiario>> mapeo = new HashMap<>();
        mapeo.put(sucursalOrigen, Arrays.asList(viaje1, viaje2));
        List<HojaDeRuta> resultado = implementacion.designar_vehiculo_conductor(mapeo);
        assertTrue(resultado.size() >= 1);
    }
    @Test
    void testDesignarVehiculoConductor_RequiereCarnetEspecial() {
        Sucursal sucursalOrigen = crearSucursalConConductorEspecial(1L, "Sucursal A", 1000.0, 5, true);
        Sucursal sucursalDestino = crearSucursal(2L, "Sucursal B");
        ViajeDiario viaje = crearViajeDiarioConRequisitosEspeciales(1L, sucursalOrigen, sucursalDestino, 300.0, 3, true);
        HashMap<Sucursal, List<ViajeDiario>> mapeo = new HashMap<>();
        mapeo.put(sucursalOrigen, Arrays.asList(viaje));
        List<HojaDeRuta> resultado = implementacion.designar_vehiculo_conductor(mapeo);
        assertFalse(resultado.isEmpty());
        HojaDeRuta hojaDeRuta = resultado.get(0);
        assertNotNull(hojaDeRuta.getConductor());
        assertTrue(hojaDeRuta.getConductor().isCarnet_super_especial());
    }
    @Test
    void testDesignarVehiculoConductor_ExperienciaMinima() {
        Sucursal sucursalOrigen = crearSucursalConRecursos(1L, "Sucursal A", 1000.0, 3);
        Sucursal sucursalDestino = crearSucursal(2L, "Sucursal B");
        ViajeDiario viaje = crearViajeDiarioConRequisitosEspeciales(1L, sucursalOrigen, sucursalDestino, 300.0, 3, false);
        HashMap<Sucursal, List<ViajeDiario>> mapeo = new HashMap<>();
        mapeo.put(sucursalOrigen, Arrays.asList(viaje));
        List<HojaDeRuta> resultado = implementacion.designar_vehiculo_conductor(mapeo);
        assertFalse(resultado.isEmpty());
        HojaDeRuta hojaDeRuta = resultado.get(0);
        assertNotNull(hojaDeRuta.getConductor());
        assertEquals(3, hojaDeRuta.getConductor().getAnios_experiencia());
    }
    @Test
    void testObtenerSucursalesPersistidas() {
        Sucursal sucursal1 = crearSucursalConRecursos(1L, "Sucursal A", 1000.0, 5);
        Sucursal sucursal2 = crearSucursal(2L, "Sucursal B");
        ViajeDiario viaje1 = crearViajeDiario(1L, sucursal1, sucursal2, 100.0);
        HashMap<Sucursal, List<ViajeDiario>> mapeo = new HashMap<>();
        mapeo.put(sucursal1, Arrays.asList(viaje1));
        implementacion.designar_vehiculo_conductor(mapeo);
        List<Sucursal> persistidas = implementacion.obtenerSucursalesPersistidas();
        assertEquals(1, persistidas.size());
        assertTrue(persistidas.contains(sucursal1));
    }
    private Sucursal crearSucursal(Long id, String nombre) {
        return Sucursal.builder()
                .id_sucursal(id)
                .nombre_sucursal(nombre)
                .descripcion_sucursal("Descripción " + nombre)
                .vehiculos_en_sucursal(new ArrayList<>())
                .conductores_en_sucursal(new ArrayList<>())
                .build();
    }
    private Sucursal crearSucursalConRecursos(Long id, String nombre, double capacidadVehiculo, int aniosExperiencia) {
        Vehiculo vehiculo = Vehiculo.builder()
                .id_vehiculo(id)
                .marca("Toyota")
                .modelo("Hilux")
                .placa("ABC123")
                .capacidadKG(capacidadVehiculo)
                .refrigerador(false)
                .build();
        Conductor conductor = Conductor.builder()
                .id_conductor(id)
                .nombre("Juan")
                .apellido("Perez")
                .fecha_nacimiento(LocalDate.of(1980, 1, 1))
                .anios_experiencia(aniosExperiencia)
                .descripcion("Conductor experimentado")
                .carnet_super_especial(false)
                .build();
        return Sucursal.builder()
                .id_sucursal(id)
                .nombre_sucursal(nombre)
                .descripcion_sucursal("Descripción " + nombre)
                .vehiculos_en_sucursal(new ArrayList<>(Arrays.asList(vehiculo)))
                .conductores_en_sucursal(new ArrayList<>(Arrays.asList(conductor)))
                .build();
    }
    private Sucursal crearSucursalConMultiplesVehiculos(Long id) {
        Vehiculo vehiculo1 = Vehiculo.builder()
                .id_vehiculo(1L)
                .marca("Toyota")
                .modelo("Hilux")
                .placa("ABC123")
                .capacidadKG(500.0)
                .refrigerador(false)
                .build();
        Vehiculo vehiculo2 = Vehiculo.builder()
                .id_vehiculo(2L)
                .marca("Ford")
                .modelo("F150")
                .placa("DEF456")
                .capacidadKG(1000.0)
                .refrigerador(false)
                .build();
        Conductor conductor = Conductor.builder()
                .id_conductor(1L)
                .nombre("Juan")
                .apellido("Perez")
                .fecha_nacimiento(LocalDate.of(1980, 1, 1))
                .anios_experiencia(5)
                .descripcion("Conductor experimentado")
                .carnet_super_especial(false)
                .build();
        return Sucursal.builder()
                .id_sucursal(id)
                .nombre_sucursal("Sucursal Múltiple")
                .descripcion_sucursal("Sucursal con múltiples vehículos")
                .vehiculos_en_sucursal(new ArrayList<>(Arrays.asList(vehiculo1, vehiculo2)))
                .conductores_en_sucursal(new ArrayList<>(Arrays.asList(conductor)))
                .build();
    }
    private Sucursal crearSucursalConConductorEspecial(Long id, String nombre, double capacidadVehiculo, int aniosExperiencia, boolean carnetEspecial) {
        Vehiculo vehiculo = Vehiculo.builder()
                .id_vehiculo(id)
                .marca("Toyota")
                .modelo("Hilux")
                .placa("ABC123")
                .capacidadKG(capacidadVehiculo)
                .refrigerador(false)
                .build();
        Conductor conductor = Conductor.builder()
                .id_conductor(id)
                .nombre("Juan")
                .apellido("Perez")
                .fecha_nacimiento(LocalDate.of(1980, 1, 1))
                .anios_experiencia(aniosExperiencia)
                .descripcion("Conductor especial")
                .carnet_super_especial(carnetEspecial)
                .build();
        return Sucursal.builder()
                .id_sucursal(id)
                .nombre_sucursal(nombre)
                .descripcion_sucursal("Descripción " + nombre)
                .vehiculos_en_sucursal(new ArrayList<>(Arrays.asList(vehiculo)))
                .conductores_en_sucursal(new ArrayList<>(Arrays.asList(conductor)))
                .build();
    }
    private ViajeDiario crearViajeDiario(Long id, Sucursal origen, Sucursal destino, double peso) {
        Mercancia mercancia = Mercancia.builder()
                .id_mercancia(id)
                .nombre_mercancia("Mercancia " + id)
                .descripcion_mercancia("Descripción mercancía")
                .peso_total_mercancia(peso)
                .build();
        Ruta ruta = Ruta.builder()
                .id_ruta(id)
                .numero_ruta("R" + id)
                .descripcion("Ruta " + id)
                .sucursal_origen(origen)
                .sucursal_destino(destino)
                .distanciaKM(100.0)
                .cantidad_peajes(2)
                .anios_experiencia(2)
                .carnet_super_especial(false)
                .build();
        Cliente cliente = Cliente.builder()
                .id_cliente(id)
                .nombre_cliente("Cliente " + id)
                .build();
        return ViajeDiario.builder()
                .viaje_diario_id(id)
                .mercancia(mercancia)
                .ruta(ruta)
                .cliente(cliente)
                .build();
    }
    private ViajeDiario crearViajeDiarioConRequisitosEspeciales(Long id, Sucursal origen, Sucursal destino, double peso, int aniosExperienciaRequeridos, boolean carnetEspecialRequerido) {
        Mercancia mercancia = Mercancia.builder()
                .id_mercancia(id)
                .nombre_mercancia("Mercancia especial " + id)
                .descripcion_mercancia("Descripción mercancía especial")
                .peso_total_mercancia(peso)
                .build();
        Ruta ruta = Ruta.builder()
                .id_ruta(id)
                .numero_ruta("R" + id)
                .descripcion("Ruta especial " + id)
                .sucursal_origen(origen)
                .sucursal_destino(destino)
                .distanciaKM(150.0)
                .cantidad_peajes(3)
                .anios_experiencia(aniosExperienciaRequeridos)
                .carnet_super_especial(carnetEspecialRequerido)
                .build();
        Cliente cliente = Cliente.builder()
                .id_cliente(id)
                .nombre_cliente("Cliente especial " + id)
                .build();
        return ViajeDiario.builder()
                .viaje_diario_id(id)
                .mercancia(mercancia)
                .ruta(ruta)
                .cliente(cliente)
                .build();
    }
}
