package com.juan.appgestioninventario_viajes.core.puertos.output;

import com.juan.appgestioninventario_viajes.core.modelos.HojaDeRuta;

import java.util.ArrayList;
import java.util.List;

public interface HojaDeRutaPuerto {
    public void guardarHojasDeRuta(List<HojaDeRuta> hojas_de_ruta);
    ArrayList<HojaDeRuta> obtenerTodasLasHojasDeRuta();
}
