import { Link, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";

export function Header() {
  const navigate = useNavigate();
  const [nombreUsuario, setNombreUsuario] = useState("Usuario");
  const [rolUsuario, setRolUsuario] = useState("Empresario");

  useEffect(() => {
    const usuarioGuardado = localStorage.getItem("usuarioLogueado");
    if (usuarioGuardado) {
      const data = JSON.parse(usuarioGuardado);
      setNombreUsuario(data.nombre || "Usuario");
      setRolUsuario(data.rol || "Empresario");
    }
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("usuarioLogueado");
    navigate("/login");
  };

  const iniciales = nombreUsuario
    .split(" ")
    .map((n) => n[0])
    .join("")
    .substring(0, 2)
    .toUpperCase();

  return (
    <header className="bg-[#111c18] border-b border-[#20362f] px-6 py-4 flex items-center justify-between flex-wrap gap-4">
      <div className="flex items-center gap-3">
        <img
          src="/Logo.png"
          alt="Logo"
          className="w-10 h-10 rounded-full object-cover border border-[#20362f]"
        />
        <div>
          <h1 className="text-white font-bold text-lg leading-none">
            DataStock
          </h1>
          <p className="text-slate-400 text-xs mt-1">
            SISTEMA DE GESTIÓN INTELIGENTE
          </p>
        </div>
      </div>

      <nav className="flex items-center gap-2 bg-[#162520] border border-[#20362f] p-1.5 rounded-full overflow-x-auto">
        <Link
          to="/dashboard"
          className="px-4 py-1.5 rounded-full text-xs font-semibold text-slate-300 hover:text-white transition-all"
        >
          Dashboard
        </Link>
        <Link
          to="/inventario"
          className="px-4 py-1.5 rounded-full text-xs font-semibold text-slate-300 hover:text-white transition-all"
        >
          Inventario
        </Link>
        <Link
          to="/usuarios"
          className="px-4 py-1.5 rounded-full text-xs font-semibold text-slate-300 hover:text-white transition-all"
        >
          Usuarios
        </Link>
        <Link
          to="/tiendas"
          className="px-4 py-1.5 rounded-full text-xs font-semibold text-slate-300 hover:text-white transition-all"
        >
          Tiendas
        </Link>
        <Link
          to="/categorias"
          className="px-4 py-1.5 rounded-full text-xs font-semibold text-slate-300 hover:text-white transition-all"
        >
          Categorías
        </Link>
        <Link
          to="/marcas"
          className="px-4 py-1.5 rounded-full text-xs font-semibold text-slate-300 hover:text-white transition-all"
        >
          Marcas
        </Link>
      </nav>

      <div className="flex items-center gap-4">
        <div className="flex items-center gap-3 bg-[#162520] border border-[#20362f] px-3 py-1.5 rounded-2xl">
          <div className="w-9 h-9 rounded-xl bg-[#204239] text-emerald-300 font-bold flex items-center justify-center text-xs border border-emerald-900/50">
            {iniciales}
          </div>
          <div className="text-left">
            <p className="text-xs font-bold text-white leading-tight">
              {nombreUsuario}
            </p>
            <p className="text-[10px] uppercase font-mono text-emerald-500 tracking-wider">
              {rolUsuario}
            </p>
          </div>
        </div>

        <button
          onClick={handleLogout}
          className="text-xs font-semibold text-slate-400 hover:text-red-400 border border-[#20362f] hover:border-red-900/50 bg-[#162520] px-4 py-2.5 rounded-xl transition-colors cursor-pointer"
        >
          Salir
        </button>
      </div>
    </header>
  );
}
