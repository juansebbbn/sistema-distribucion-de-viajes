package com.juan.appgestioninventario_viajes.core.puertos.input;
import com.juan.appgestioninventario_viajes.core.modelos.HojaDeRuta;
import com.juan.appgestioninventario_viajes.core.modelos.Sucursal;
import com.juan.appgestioninventario_viajes.core.modelos.ViajeDiario;
import java.util.HashMap;
import java.util.List;
public interface AsignacionDeViajes {
    public HashMap<Sucursal, List<ViajeDiario>> mapear_sucursales_con_viajes(List<ViajeDiario> viajeDiarios, List<Sucursal> sucursales);
    public List<HojaDeRuta> designar_vehiculo_conductor(HashMap<Sucursal, List<ViajeDiario>> mapeo_sucursales_con_viajes);
}
