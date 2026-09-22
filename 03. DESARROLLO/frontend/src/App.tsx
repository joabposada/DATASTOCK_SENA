// traigo el router y las páginas que voy a usar
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import { Login } from "./pages/Login";
import { Registro } from "./pages/Registro";
import { Dashboard } from "./pages/Dashboard";
import { Inventario } from "./pages/Inventario";
import { Usuarios } from "./pages/Usuarios";
import { Tiendas } from "./pages/Tiendas";
import { Categorias } from "./pages/Categorias";
import { Marcas } from "./pages/Marcas";

function App() {
  return (
    // meto todo dentro del router para poder navegar entre páginas
    <BrowserRouter>
      <Routes>
        {/* si entran a la raíz los mando directo al login */}
        <Route path="/" element={<Navigate to="/login" replace />} />
        <Route path="/login" element={<Login />} />
        <Route path="/registro" element={<Registro />} />
        {/* de aquí para abajo van las páginas del sistema */}
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/inventario" element={<Inventario />} />
        <Route path="/usuarios" element={<Usuarios />} />
        <Route path="/tiendas" element={<Tiendas />} />
        <Route path="/categorias" element={<Categorias />} />
        <Route path="/marcas" element={<Marcas />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;