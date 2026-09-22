import CryptoJS from "crypto-js";
import { useState, type FormEvent } from "react";
import { Link, useNavigate } from "react-router-dom";
import { api } from "../services/api";

export function Login() {
  const navigate = useNavigate();
  const [correo, setCorreo] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleLogin = async (e: FormEvent) => {
    e.preventDefault();
    setError("");

    if (!correo || !password) {
      setError("Por favor ingresa tu correo y contraseña");
      return;
    }

    try {
      const response = await api.get("/usuarios");
      const usuarios = response.data;

      // Encriptamos la contraseña ingresada para compararla con la base de datos
      const passwordHasheada = CryptoJS.SHA256(password).toString();

      const usuarioEncontrado = usuarios.find(
        (u: any) => u.correo === correo && u.password === passwordHasheada,
      );

      if (usuarioEncontrado) {
        // SEGURIDAD: Extraemos la contraseña para NUNCA guardarla en localStorage
        const { password: _, ...datosSeguros } = usuarioEncontrado;

        localStorage.setItem("usuarioLogueado", JSON.stringify(datosSeguros));
        navigate("/dashboard");
      } else {
        setError("Correo o contraseña incorrectos");
      }
    } catch (err) {
      console.error(err);
      setError("Error al conectar con el servidor");
    }
  };

  return (
    <div className="bg-[#0b1114] min-h-screen flex items-center justify-center font-sans p-4">
      <div className="bg-[#111c18] p-10 rounded-[2rem] shadow-2xl w-full max-w-md text-center border border-[#20362f]">
        {/* logo y titulo */}
        <div className="mb-10 flex flex-col items-center">
          <img
            src="/Logo.png"
            alt="Logo DataStock"
            className="w-24 h-24 mb-4 rounded-full shadow-lg object-cover border border-[#20362f]"
          />
          <h1 className="text-[#4b7c68] text-3xl font-bold tracking-wide">
            DataStock
          </h1>
          <p className="text-slate-400 text-sm mt-2">
            Sistema de Gestión Profesional
          </p>
        </div>

        {/* si algo falla muestro el mensaje de error aquí */}
        {error && (
          <div className="mb-6 p-3 bg-red-900/50 border border-red-700 text-red-200 text-sm rounded-xl text-center">
            {error}
          </div>
        )}

        <form onSubmit={handleLogin}>
          <div className="mb-5 text-left">
            <label
              htmlFor="correo"
              className="block text-sm font-semibold text-slate-200 mb-2"
            >
              Correo Electrónico
            </label>
            <input
              type="email"
              id="correo"
              value={correo}
              onChange={(e) => setCorreo(e.target.value)}
              placeholder="correo@ejemplo.com"
              className="w-full bg-[#162520] border border-[#20362f] text-white text-sm rounded-xl px-4 py-3 outline-none focus:border-emerald-600 transition-all placeholder-slate-500"
            />
          </div>

          <div className="mb-8 text-left">
            <label
              htmlFor="password"
              className="block text-sm font-semibold text-slate-200 mb-2"
            >
              Contraseña
            </label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="••••••••"
              className="w-full bg-[#162520] border border-[#20362f] text-white text-sm rounded-xl px-4 py-3 outline-none focus:border-emerald-600 transition-all placeholder-slate-500 tracking-widest"
            />
          </div>

          <button
            type="submit"
            className="w-full bg-[#204239] hover:bg-[#2a564a] text-slate-200 text-center font-semibold py-3 px-4 rounded-xl transition-colors shadow-lg cursor-pointer"
          >
            Iniciar Sesión
          </button>
        </form>

        <div className="mt-8 text-sm text-slate-400">
          ¿No tienes cuenta?
          <Link
            to="/registro"
            className="text-[#4b7c68] font-semibold hover:text-emerald-500 transition-colors ml-1"
          >
            Regístrate
          </Link>
        </div>
      </div>
    </div>
  );
}