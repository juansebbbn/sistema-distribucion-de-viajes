package com.juan.appgestioninventario_viajes.core.servicios;

import com.juan.appgestioninventario_viajes.core.modelos.*;
import com.juan.appgestioninventario_viajes.core.puertos.input.AsignacionDeViajes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Implementacion_algoritmo_asignacion implements AsignacionDeViajes {
    private List<HojaDeRuta> hojas_de_ruta = new ArrayList<>();
    private List<Sucursal> sucursalesPersistidas = new ArrayList<>();

    @Override
    public HashMap<Sucursal, List<ViajeDiario>> mapear_sucursales_con_viajes(List<ViajeDiario> viajeDiarios, List<Sucursal> sucursales){
        HashMap<Sucursal, List<ViajeDiario>> sucursales_viajes = new HashMap<>();

        for (Sucursal sucursal : sucursales){
            List<ViajeDiario> lista_viajes = new ArrayList<>();
            sucursales_viajes.put(sucursal, lista_viajes);
        }

        for(ViajeDiario viajeDiario : viajeDiarios){
            Sucursal origen = viajeDiario.getRuta().getSucursal_origen();

            if (sucursales_viajes.containsKey(origen)) {
                sucursales_viajes.get(origen).add(viajeDiario);
            }
        }

        return sucursales_viajes;
    }

    @Override
    public List<HojaDeRuta> designar_vehiculo_conductor(HashMap<Sucursal, List<ViajeDiario>> mapeo_sucursales_con_viajes) {

        // para todas las sucursales que tengo en mi hash map debo asignar vehiculos y conductores para sus viajes
        for(Sucursal sucursal : mapeo_sucursales_con_viajes.keySet()){ // por cada sucursal
            System.out.println("DESIGNANDO VEHICULO Y CONDUCTOR PARA LOS VIAJES DE LA SUCURSAL: " + sucursal.getId_sucursal());

            //variable para llevar el conteo de cuantos kg llevamos por el total de los viajes sumados
            double suma_peso_total_vehiculo = 0;

            //obtenemos el vehiculo que mas peso puede llevar
            Vehiculo vehiculo = obtener_vehiculo_con_mas_capacidad(sucursal);

            //lista para ir agregando los viajes que pueden viajar juntos en un mismo vehiculo
            ArrayList<ViajeDiario> viajes_designados = new ArrayList<>();

            Iterator<ViajeDiario> lista_viajes = mapeo_sucursales_con_viajes.get(sucursal).iterator();

            while(lista_viajes.hasNext()){
                ViajeDiario viajeDiario = lista_viajes.next();

                suma_peso_total_vehiculo += viajeDiario.getMercancia().getPeso_total_mercancia();

                if(suma_peso_total_vehiculo <= vehiculo.getCapacidadKG()){
                    viajes_designados.add(viajeDiario);

                    //este checkeo sirve para que si la condicion de que el viaje actual
                    //no rebalsa el peso total del vehiculo se cree la designacion
                    if(!lista_viajes.hasNext()){
                        Conductor conductor = buscar_conductor_adecuado(vehiculo, sucursal, viajeDiario);

                        HojaDeRuta hr = crear_hoja_de_ruta(conductor, vehiculo, viajeDiario.getRuta(), viajes_designados);

                        print_hoja_de_ruta(hr);

                        actualizar_estado_sucursales(conductor, vehiculo, sucursal, viajeDiario.getRuta().getSucursal_destino(), viajes_designados);
                    }
                }else{
                    suma_peso_total_vehiculo = 0;

                    Conductor conductor = buscar_conductor_adecuado(vehiculo, sucursal, viajeDiario);

                    HojaDeRuta hr = crear_hoja_de_ruta(conductor, vehiculo, viajeDiario.getRuta(), viajes_designados);

                    actualizar_estado_sucursales(conductor, vehiculo, sucursal, viajeDiario.getRuta().getSucursal_destino(), viajes_designados);

                    print_hoja_de_ruta(hr);

                    /*if(obtener_vehiculo_con_mas_capacidad(sucursal) == null || buscar_conductor_adecuado(vehiculo, sucursal, viajeDiario) == null){
                        // no quedan mas vehiculos o conductores, la mercaderia tendra que enviarse en la proxima iteracion
                        return;
                    }*/

                    viajes_designados.clear();

                    viajes_designados.add(viajeDiario);

                    suma_peso_total_vehiculo += viajeDiario.getMercancia().getPeso_total_mercancia();

                    vehiculo = obtener_vehiculo_con_mas_capacidad(sucursal);
                }

            }

        }

        this.sucursalesPersistidas.addAll(mapeo_sucursales_con_viajes.keySet());

        return this.hojas_de_ruta;
    }

    public List<Sucursal> obtenerSucursalesPersistidas(){
        return this.sucursalesPersistidas;
    }

    private void print_hoja_de_ruta(HojaDeRuta hr){
        System.out.println("ID HR:  " + hr.getId_hoja_de_ruta());
        System.out.println("ID CONDUCTOR: " + hr.getConductor().getId_conductor());
        System.out.println("ID VEHICULO: " + hr.getVehiculo().getId_vehiculo());
        System.out.println("ID RUTA: " + hr.getRuta());
        System.out.println("CANTIDAD DE VIAJES DESIGNADOS: " + hr.getViajes_designados().size());
    }


    private Vehiculo obtener_vehiculo_con_mas_capacidad(Sucursal sucursal) {
        double capacidad_actual = 0;
        Vehiculo vehiculo = null;

        for(Vehiculo veh: sucursal.getVehiculos_en_sucursal()){
            if(veh.getCapacidadKG() > capacidad_actual){
                capacidad_actual = veh.getCapacidadKG();
                vehiculo = veh;
            }
        }

        return vehiculo;
    }

    private Conductor buscar_conductor_adecuado(Vehiculo vehiculo, Sucursal sucursal, ViajeDiario viaje_diario) {
        Conductor condu = null;

        for (Conductor conductor : sucursal.getConductores_en_sucursal()){
            if(conductor.getAnios_experiencia() >= viaje_diario.getRuta().getAnios_experiencia()){
                if(viaje_diario.getRuta().isCarnet_super_especial()){
                    if(conductor.isCarnet_super_especial()){
                        condu = conductor;
                    }
                }else{
                    condu = conductor;
                }
            }
        }

        if(condu == null){
            // no se encontraron conductores
        }

        return condu;
    }

    private void actualizar_estado_sucursales(Conductor conductor, Vehiculo vehiculo, Sucursal sucursalOrigen, Sucursal sucursalDestino, ArrayList<ViajeDiario> viajes_designados) {
        sucursalDestino.add_conductor(conductor);
        sucursalDestino.add_vehiculo(vehiculo);

        sucursalOrigen.remove_conductor(conductor);
        sucursalOrigen.add_vehiculo(vehiculo);
    }


    private HojaDeRuta crear_hoja_de_ruta(Conductor conductor, Vehiculo vehiculo, Ruta ruta, ArrayList<ViajeDiario> viajes_designados) {
        HojaDeRuta hojaDeRuta = new HojaDeRuta(conductor, vehiculo, ruta, viajes_designados);
        hojas_de_ruta.add(hojaDeRuta);
        return hojaDeRuta;
    }

}
