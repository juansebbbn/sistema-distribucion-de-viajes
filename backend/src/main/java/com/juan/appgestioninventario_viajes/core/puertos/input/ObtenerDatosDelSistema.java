package com.juan.appgestioninventario_viajes.core.puertos.input;

import com.juan.appgestioninventario_viajes.core.modelos.*;

import java.util.ArrayList;
import java.util.List;

public interface ObtenerDatosDelSistema {
    public ArrayList<HojaDeRuta> obtenerHojasDeRuta();
    public List<Sucursal> obtenerEstadoSucursales();
}
