package com.juan.appgestioninventario_viajes.core.puertos.output;
import com.juan.appgestioninventario_viajes.core.modelos.Vehiculo;
import java.util.List;
public interface VehiculoRepositorioPuerto {
    List<Vehiculo> obtener_vehiculos_disponibles();
    void guardar_asignacion(Vehiculo vehiculo, Long sucursalId);
}
