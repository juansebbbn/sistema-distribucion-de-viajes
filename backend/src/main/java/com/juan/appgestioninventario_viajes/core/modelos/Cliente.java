package com.juan.appgestioninventario_viajes.core.modelos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private long id_cliente;
    private String nombre_cliente;
}
