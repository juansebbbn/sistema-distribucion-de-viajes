package com.juan.appgestioninventario_viajes.core.modelos;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
@Data
@Builder
public class Conductor {
    private long id_conductor;
    private String nombre;
    private String apellido;
    private LocalDate fecha_nacimiento;
    private int anios_experiencia;
    private String descripcion;
    private boolean carnet_super_especial;
}
