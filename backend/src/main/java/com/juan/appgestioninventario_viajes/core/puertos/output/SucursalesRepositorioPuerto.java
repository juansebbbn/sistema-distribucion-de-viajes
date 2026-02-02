package com.juan.appgestioninventario_viajes.core.puertos.output;

import com.juan.appgestioninventario_viajes.core.modelos.Sucursal;

import java.util.List;

public interface SucursalesRepositorioPuerto {

    List<Sucursal> obtenterSucursales();
    void guardar_estado_sucursales();
    void persistir_estado_sucursales(List<Sucursal> sucursals);
}
