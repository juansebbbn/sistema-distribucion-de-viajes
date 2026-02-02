package com.juan.appgestioninventario_viajes.core.modelos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ViajeDiario {
    private long viaje_diario_id;
    private Mercancia mercancia;
    private Ruta ruta;
    private Cliente cliente;
}
