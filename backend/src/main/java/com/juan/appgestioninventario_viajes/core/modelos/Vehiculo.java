package com.juan.appgestioninventario_viajes.core.modelos;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class Vehiculo {
    private long id_vehiculo;
    private String marca;
    private String modelo;
    private String placa;
    private double capacidadKG;
    private boolean refrigerador;
}
