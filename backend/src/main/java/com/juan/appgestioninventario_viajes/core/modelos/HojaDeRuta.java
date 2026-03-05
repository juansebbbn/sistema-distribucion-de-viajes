package com.juan.appgestioninventario_viajes.core.modelos;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import java.util.List;
@Data
public class HojaDeRuta {
    private long id_hoja_de_ruta;
    private Conductor conductor;
    private Vehiculo vehiculo;
    private Ruta ruta;
    private List<ViajeDiario> viajes_designados;
    public HojaDeRuta(Conductor conductor, Vehiculo vehiculo, Ruta ruta, List<ViajeDiario> viajes_designados) {
        this.conductor = conductor;
        this.vehiculo = vehiculo;
        this.ruta = ruta;
        this.viajes_designados = viajes_designados;
    }
}
