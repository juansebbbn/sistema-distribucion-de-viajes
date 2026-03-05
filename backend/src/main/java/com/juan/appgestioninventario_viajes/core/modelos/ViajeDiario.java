package com.juan.appgestioninventario_viajes.core.modelos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViajeDiario {
    private long viaje_diario_id;
    private Mercancia mercancia;
    private Ruta ruta;
    private Cliente cliente;
}
