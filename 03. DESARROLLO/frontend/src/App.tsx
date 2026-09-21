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
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Navigate to="/login" replace />} />
        <Route path="/login" element={<Login />} />
        <Route path="/registro" element={<Registro />} />
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
