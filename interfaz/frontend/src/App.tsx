import { BrowserRouter, Routes, Route} from "react-router-dom"; // importar ruta para definir los mapeos
import {Home} from "./pages/Home";
import { Sucursales } from "./pages/Sucursales";
import { Conductores } from "./pages/Conductores";
import { Vehiculos } from "./pages/Vehiculos"

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/Sucursales" element={<Sucursales/>}/>
        <Route path="/Conductores" element={<Conductores/>}/>
        <Route path="/Vehiculos" element={<Vehiculos/>}/>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
