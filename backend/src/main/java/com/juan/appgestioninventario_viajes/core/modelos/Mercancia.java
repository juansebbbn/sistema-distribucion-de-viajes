package com.juan.appgestioninventario_viajes.core.modelos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Mercancia {
    private long id_mercancia;
    private String nombre_mercancia;
    private String descripcion_mercancia;
    // private List<Producto> productos;
    private double peso_total_mercancia;
}
