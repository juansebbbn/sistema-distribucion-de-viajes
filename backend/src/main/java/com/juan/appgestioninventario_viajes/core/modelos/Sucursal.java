package com.juan.appgestioninventario_viajes.core.modelos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sucursal {
    private long id_sucursal;
    private String nombre_sucursal;
    private String descripcion_sucursal;
    private List<Vehiculo> vehiculos_en_sucursal;
    private List<Conductor> conductores_en_sucursal;
    public void add_vehiculo(Vehiculo vehiculo) {
        if (vehiculos_en_sucursal == null) {
            vehiculos_en_sucursal = new ArrayList<>();
        }
        vehiculos_en_sucursal.add(vehiculo);
    }
    public void add_conductor(Conductor conductor) {
        if (conductores_en_sucursal == null) {
            conductores_en_sucursal = new ArrayList<>();
        }
        conductores_en_sucursal.add(conductor);
    }
    public void remove_vehiculo(Vehiculo vehiculo) {
        vehiculos_en_sucursal.remove(vehiculo);
    }
    public void remove_conductor(Conductor conductor) {
        conductores_en_sucursal.remove(conductor);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sucursal sucursal = (Sucursal) o;
        return Objects.equals(id_sucursal, sucursal.id_sucursal);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id_sucursal);
    }
}
