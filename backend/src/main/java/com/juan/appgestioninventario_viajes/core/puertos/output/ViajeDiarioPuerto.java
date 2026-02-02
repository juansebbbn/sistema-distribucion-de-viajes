package com.juan.appgestioninventario_viajes.core.puertos.output;

import com.juan.appgestioninventario_viajes.core.modelos.ViajeDiario;
import java.util.List;

public interface ViajeDiarioPuerto {
    public List<ViajeDiario> obtener_todos_los_viajes();

    void eliminar_todos_los_viajes();
}
