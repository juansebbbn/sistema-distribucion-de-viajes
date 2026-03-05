package com.juan.appgestioninventario_viajes.core.modelos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ruta {
    private long id_ruta;
    private String numero_ruta;
    private String descripcion;
    private Sucursal sucursal_origen;
    private Sucursal sucursal_destino;
    private double distanciaKM;
    private int cantidad_peajes;
    private int anios_experiencia;
    private boolean carnet_super_especial;
}
