package com.juan.appgestioninventario_viajes;

import com.juan.appgestioninventario_viajes.core.servicios.Implementacion_algoritmo_asignacion;
import com.juan.appgestioninventario_viajes.core.modelos.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class AlgoritmoAsignacionTest {

    //Algoritmo a testear
    private Implementacion_algoritmo_asignacion algortimo;

    //Estructuras de datos necesarias para el test
    private List<Sucursal> sucursales;
    private List<ViajeDiario> viajesDiarios;
    private HashMap<Sucursal, List<ViajeDiario>> mapeo_sucursales_viajes;


    @Test
    public void testear_mapeo(){
        HashMap<Sucursal, List<ViajeDiario>> mapeo_test_metodo = algortimo.mapear_sucursales_con_viajes(viajesDiarios, sucursales);

        for(Sucursal sucursal: mapeo_test_metodo.keySet()){
            System.out.println("SUCURSAL: " + sucursal.getId_sucursal());

            for (ViajeDiario viaje: mapeo_test_metodo.get(sucursal)){
                System.out.println("Viaje " + viaje.getViaje_diario_id());
            }
        }
    }

    @Test
    public void testear_asignacion(){
        List<HojaDeRuta> lista_hoja_de_ruta_test = new ArrayList<>();

        lista_hoja_de_ruta_test = algortimo.designar_vehiculo_conductor(mapeo_sucursales_viajes);

        for(HojaDeRuta hoja: lista_hoja_de_ruta_test){
            System.out.println("HOJA DE RUTA: " + hoja.getId_hoja_de_ruta());
        }
    }


    @BeforeEach
    public void instanciarObjetosAlgoritmos() {
        this.sucursales = new ArrayList<>();
        this.viajesDiarios = new ArrayList<>();
        this.mapeo_sucursales_viajes = new HashMap<>();

        this.algortimo = new Implementacion_algoritmo_asignacion();

        Vehiculo veh1 = Vehiculo.builder().id_vehiculo(1).capacidadKG(300).build();
        Vehiculo veh2 = Vehiculo.builder().id_vehiculo(2).capacidadKG(800).build();
        Vehiculo veh3 = Vehiculo.builder().id_vehiculo(3).capacidadKG(400).build();
        Vehiculo veh4 = Vehiculo.builder().id_vehiculo(4).capacidadKG(100).build();
        Vehiculo veh5 = Vehiculo.builder().id_vehiculo(5).capacidadKG(500).build();
        Vehiculo veh6 = Vehiculo.builder().id_vehiculo(6).capacidadKG(200).build();
        Vehiculo veh7 = Vehiculo.builder().id_vehiculo(7).capacidadKG(140).build();
        Vehiculo veh8 = Vehiculo.builder().id_vehiculo(8).capacidadKG(200).build();

        Conductor c1 = Conductor.builder().id_conductor(1).anios_experiencia(2).build();
        Conductor c2 = Conductor.builder().id_conductor(2).anios_experiencia(10).carnet_super_especial(true).build();
        Conductor c3 = Conductor.builder().id_conductor(3).anios_experiencia(7).build();
        Conductor c4 = Conductor.builder().id_conductor(4).anios_experiencia(5).build();
        Conductor c5 = Conductor.builder().id_conductor(5).anios_experiencia(0).carnet_super_especial(true).build();
        Conductor c6 = Conductor.builder().id_conductor(6).anios_experiencia(1).build();
        Conductor c7 = Conductor.builder().id_conductor(7).anios_experiencia(3).build();
        Conductor c8 = Conductor.builder().id_conductor(8).anios_experiencia(2).build();

        Sucursal sucursal1 = Sucursal.builder().id_sucursal(1).nombre_sucursal("Sucursal 1").build();
        Sucursal sucursal2 = Sucursal.builder().id_sucursal(2).nombre_sucursal("Sucursal 2").build();
        Sucursal sucursal3 = Sucursal.builder().id_sucursal(3).nombre_sucursal("Sucursal 3").build();
        Sucursal sucursal4 = Sucursal.builder().id_sucursal(4).nombre_sucursal("Sucursal 4").build();

        //linkeando conductores

        sucursal1.add_conductor(c1);
        sucursal1.add_conductor(c2);

        sucursal2.add_conductor(c3);
        sucursal2.add_conductor(c4);

        sucursal3.add_conductor(c5);
        sucursal3.add_conductor(c6);

        sucursal4.add_conductor(c7);
        sucursal4.add_conductor(c8);

        //linkeando vehiculos

        sucursal1.add_vehiculo(veh1);
        sucursal1.add_vehiculo(veh2);

        sucursal2.add_vehiculo(veh3);
        sucursal2.add_vehiculo(veh4);

        sucursal3.add_vehiculo(veh5);
        sucursal3.add_vehiculo(veh6);

        sucursal4.add_vehiculo(veh7);
        sucursal4.add_vehiculo(veh8);

        sucursales.add(sucursal1);
        sucursales.add(sucursal2);
        sucursales.add(sucursal3);
        sucursales.add(sucursal4);

        Ruta ruta1 = Ruta.builder().id_ruta(1).sucursal_origen(sucursal1).sucursal_destino(sucursal2).build();
        Ruta ruta2 = Ruta.builder().id_ruta(2).sucursal_origen(sucursal2).sucursal_destino(sucursal3).build();
        Ruta ruta3 = Ruta.builder().id_ruta(3).sucursal_origen(sucursal3).sucursal_destino(sucursal4).build();
        Ruta ruta4 = Ruta.builder().id_ruta(4).sucursal_origen(sucursal4).sucursal_destino(sucursal1).build();

        Ruta ruta5 = Ruta.builder().id_ruta(5).sucursal_origen(sucursal2).sucursal_destino(sucursal1).build();
        Ruta ruta6 = Ruta.builder().id_ruta(6).sucursal_origen(sucursal3).sucursal_destino(sucursal2).build();
        Ruta ruta7 = Ruta.builder().id_ruta(7).sucursal_origen(sucursal4).sucursal_destino(sucursal3).build();
        Ruta ruta8 = Ruta.builder().id_ruta(8).sucursal_origen(sucursal1).sucursal_destino(sucursal4).build();

        Mercancia mercancia = Mercancia.builder().id_mercancia(1).peso_total_mercancia(400).build();

        ViajeDiario v1 = ViajeDiario.builder().viaje_diario_id(1).ruta(ruta1).mercancia(mercancia).build();
        ViajeDiario v2 = ViajeDiario.builder().viaje_diario_id(2).ruta(ruta2).mercancia(mercancia).build();
        ViajeDiario v3 = ViajeDiario.builder().viaje_diario_id(3).ruta(ruta3).mercancia(mercancia).build();
        ViajeDiario v4 = ViajeDiario.builder().viaje_diario_id(4).ruta(ruta4).mercancia(mercancia).build();

        viajesDiarios.add(v1);
        viajesDiarios.add(v2);
        viajesDiarios.add(v3);
        viajesDiarios.add(v4);

        ArrayList<ViajeDiario> listaViajesDiariosSuc1 = new ArrayList<>();
        listaViajesDiariosSuc1.add(v1);

        ArrayList<ViajeDiario> listaViajesDiariosSuc2 = new ArrayList<>();
        listaViajesDiariosSuc2.add(v2);

        ArrayList<ViajeDiario> listaViajesDiariosSuc3 = new ArrayList<>();
        listaViajesDiariosSuc3.add(v3);

        ArrayList<ViajeDiario> listaViajesDiariosSuc4 = new ArrayList<>();
        listaViajesDiariosSuc4.add(v4);

        mapeo_sucursales_viajes.put(sucursal1, listaViajesDiariosSuc1);
        mapeo_sucursales_viajes.put(sucursal2, listaViajesDiariosSuc2);
        mapeo_sucursales_viajes.put(sucursal3, listaViajesDiariosSuc3);
        mapeo_sucursales_viajes.put(sucursal4, listaViajesDiariosSuc4);
    }
}
