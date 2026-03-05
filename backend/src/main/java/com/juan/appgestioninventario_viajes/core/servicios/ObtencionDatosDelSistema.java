package com.juan.appgestioninventario_viajes.core.servicios;
import com.juan.appgestioninventario_viajes.core.modelos.*;
import com.juan.appgestioninventario_viajes.core.puertos.input.ObtenerDatosDelSistema;
import com.juan.appgestioninventario_viajes.core.puertos.output.HojaDeRutaPuerto;
import com.juan.appgestioninventario_viajes.core.puertos.output.SucursalesRepositorioPuerto;
import com.juan.appgestioninventario_viajes.core.puertos.output.VehiculoRepositorioPuerto;
import com.juan.appgestioninventario_viajes.core.puertos.output.ViajeDiarioPuerto;
import java.util.ArrayList;
import java.util.List;
public class ObtencionDatosDelSistema implements ObtenerDatosDelSistema {
    private SucursalesRepositorioPuerto sucursalesRepositorioPuerto;
    private VehiculoRepositorioPuerto vehiculoRepositorioPuerto;
    private ViajeDiarioPuerto viajeDiarioPuerto;
    private HojaDeRutaPuerto hojaDeRutaPuerto;
    @Override
    public ArrayList<HojaDeRuta> obtenerHojasDeRuta() {
        return hojaDeRutaPuerto.obtenerTodasLasHojasDeRuta();
    }
    @Override
    public List<Sucursal> obtenerEstadoSucursales() {
        return sucursalesRepositorioPuerto.obtenterSucursales();
    }
}
