package com.juan.appgestioninventario_viajes.core.servicios;

import com.juan.appgestioninventario_viajes.core.modelos.HojaDeRuta;
import com.juan.appgestioninventario_viajes.core.modelos.Sucursal;
import com.juan.appgestioninventario_viajes.core.modelos.ViajeDiario;
import com.juan.appgestioninventario_viajes.core.puertos.output.HojaDeRutaPuerto;
import com.juan.appgestioninventario_viajes.core.puertos.output.SucursalesRepositorioPuerto;
import com.juan.appgestioninventario_viajes.core.puertos.output.VehiculoRepositorioPuerto;
import com.juan.appgestioninventario_viajes.core.puertos.output.ViajeDiarioPuerto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrquestacionAlgortimo {

    //manipulacion de data
    private SucursalesRepositorioPuerto sucursalesRepositorioPuerto;
    private VehiculoRepositorioPuerto vehiculoRepositorioPuerto;
    private ViajeDiarioPuerto viajeDiarioPuerto;
    private HojaDeRutaPuerto hojaDeRutaPuerto;

    //algoritmo a orquestar
    private Implementacion_algoritmo_asignacion implementacion_algoritmo_asignacion;

    public void orquestarAlgortimo() {
        List<Sucursal> sucursales = new ArrayList<>();
        sucursales = sucursalesRepositorioPuerto.obtenterSucursales();
        List<ViajeDiario> viajes = new ArrayList<>();
        viajes = viajeDiarioPuerto.obtener_todos_los_viajes();

        HashMap<Sucursal, List<ViajeDiario>> mapeo = implementacion_algoritmo_asignacion.mapear_sucursales_con_viajes(viajes, sucursales);

        List<HojaDeRuta> hojas_de_ruta = new ArrayList<>();
        hojas_de_ruta = implementacion_algoritmo_asignacion.designar_vehiculo_conductor(mapeo);

        hojaDeRutaPuerto.guardarHojasDeRuta(hojas_de_ruta);
        viajeDiarioPuerto.eliminar_todos_los_viajes();
        sucursalesRepositorioPuerto.persistir_estado_sucursales(implementacion_algoritmo_asignacion.obtenerSucursalesPersistidas());
    }


}
