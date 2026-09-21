import { Link } from "react-router-dom";

export function Header() {
  return (
    <header className="bg-[#0b1114] border-b border-[#20362f] px-6 py-4 flex justify-between items-center">
      <div className="flex items-center gap-3">
        <img
          src="/Logo.png"
          alt="Logo"
          className="w-10 h-10 rounded-full object-cover border border-[#20362f]"
        />
        <div>
          <h1 className="text-white font-bold text-xl leading-none tracking-wide">
            DataStock
          </h1>
          <p className="text-slate-400 text-[10px] uppercase tracking-widest mt-1">
            Sistema de Gestión Inteligente
          </p>
        </div>
      </div>

      <nav className="hidden md:flex items-center gap-1 bg-[#111c18] p-1 rounded-2xl border border-[#20362f]">
        <Link
          to="/dashboard"
          className="flex items-center gap-1.5 px-3 py-1.5 rounded-xl bg-[#204239] text-white shadow-md transition-all text-xs font-bold"
        >
          Dashboard
        </Link>
        <Link
          to="/inventario"
          className="flex items-center gap-1.5 px-3 py-1.5 rounded-xl text-slate-400 hover:text-white hover:bg-[#162520] transition-all text-xs font-semibold"
        >
          Inventario
        </Link>
        <div className="flex items-center gap-1.5 px-3 py-1.5 rounded-xl text-slate-400 text-xs font-semibold opacity-50 cursor-not-allowed">
          Usuarios
        </div>
        <div className="flex items-center gap-1.5 px-3 py-1.5 rounded-xl text-slate-400 text-xs font-semibold opacity-50 cursor-not-allowed">
          Tiendas
        </div>
      </nav>

      <div className="flex items-center gap-4">
        <div className="flex items-center gap-3 border border-[#20362f] rounded-full pl-2 pr-4 py-1.5 bg-[#111c18]">
          <div className="bg-[#162520] text-slate-300 w-8 h-8 rounded-full flex items-center justify-center text-xs font-bold">
            CR
          </div>
          <div className="text-right">
            <p className="text-white text-sm font-bold leading-tight">
              Carlos Rodríguez
            </p>
            <p className="text-slate-400 text-[10px] uppercase tracking-wider leading-tight">
              Empresario
            </p>
          </div>
        </div>
        {/* Botón salir nos devuelve al Login */}
        <Link
          to="/login"
          className="flex items-center gap-2 text-slate-400 hover:text-red-400 text-sm font-semibold transition-colors"
        >
          Salir
        </Link>
      </div>
    </header>
  );
}
