import { useState, type FormEvent } from "react";
import { Link, useNavigate } from "react-router-dom";
import { api } from "../services/api";

export function Registro() {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    primer_nombre: "",
    segundo_nombre: "",
    primer_apellido: "",
    segundo_apellido: "",
    correo: "",
    rol: "empresario",
    password_reg: "",
    confirm_password: "",
  });

  const [error, setError] = useState("");

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>,
  ) => {
    setFormData({
      ...formData,
      [e.target.id]: e.target.value,
    });
  };

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();
    setError("");

    if (
      !formData.primer_nombre ||
      !formData.primer_apellido ||
      !formData.correo ||
      !formData.password_reg
    ) {
      setError("Por favor completa todos los campos obligatorios (*)");
      return;
    }

    if (formData.password_reg !== formData.confirm_password) {
      setError("Las contraseñas no coinciden");
      return;
    }

    try {
      // Guardamos en la base de datos simulada
      await api.post("/usuarios", {
        nombre: `${formData.primer_nombre} ${formData.primer_apellido}`,
        correo: formData.correo,
        rol: formData.rol,
        password: formData.password_reg,
      });

      navigate("/login");
    } catch (err) {
      console.error(err);
      setError("Hubo un error al conectar con el servidor");
    }
  };

  return (
    <div className="bg-[#0b1114] min-h-screen flex items-center justify-center font-sans p-4">
      <div className="bg-[#111c18] p-8 md:p-10 rounded-[2rem] shadow-2xl w-full max-w-2xl border border-[#20362f]">
        <div className="mb-8 flex flex-col items-center text-center">
          <img
            src="/Logo.png"
            alt="DataStock Logo"
            className="w-16 h-16 mb-4 rounded-full shadow-lg object-cover border border-[#20362f]"
          />
          <h1 className="text-[#4b7c68] text-3xl font-bold tracking-wide mb-1">
            Registro en DataStock
          </h1>
          <p className="text-slate-400 text-sm">Crea tu cuenta en el sistema</p>
        </div>

        {error && (
          <div className="mb-6 p-3 bg-red-900/50 border border-red-700 text-red-200 text-sm rounded-xl text-center">
            {error}
          </div>
        )}

        <form onSubmit={handleSubmit}>
          <div className="grid grid-cols-1 md:grid-cols-2 gap-x-5 gap-y-6">
            <div>
              <label
                htmlFor="primer_nombre"
                className="block text-sm font-semibold text-slate-200 mb-2"
              >
                Primer Nombre *
              </label>
              <input
                type="text"
                id="primer_nombre"
                value={formData.primer_nombre}
                onChange={handleChange}
                placeholder="Juan"
                className="w-full bg-[#162520] border border-[#20362f] text-white text-sm rounded-xl px-4 py-3 outline-none focus:border-emerald-600 transition-all placeholder-slate-500"
              />
            </div>

            <div>
              <label
                htmlFor="segundo_nombre"
                className="block text-sm font-semibold text-slate-200 mb-2"
              >
                Segundo Nombre
              </label>
              <input
                type="text"
                id="segundo_nombre"
                value={formData.segundo_nombre}
                onChange={handleChange}
                placeholder="Carlos"
                className="w-full bg-[#162520] border border-[#20362f] text-white text-sm rounded-xl px-4 py-3 outline-none focus:border-emerald-600 transition-all placeholder-slate-500"
              />
            </div>

            <div>
              <label
                htmlFor="primer_apellido"
                className="block text-sm font-semibold text-slate-200 mb-2"
              >
                Primer Apellido *
              </label>
              <input
                type="text"
                id="primer_apellido"
                value={formData.primer_apellido}
                onChange={handleChange}
                placeholder="Pérez"
                className="w-full bg-[#162520] border border-[#20362f] text-white text-sm rounded-xl px-4 py-3 outline-none focus:border-emerald-600 transition-all placeholder-slate-500"
              />
            </div>

            <div>
              <label
                htmlFor="segundo_apellido"
                className="block text-sm font-semibold text-slate-200 mb-2"
              >
                Segundo Apellido
              </label>
              <input
                type="text"
                id="segundo_apellido"
                value={formData.segundo_apellido}
                onChange={handleChange}
                placeholder="González"
                className="w-full bg-[#162520] border border-[#20362f] text-white text-sm rounded-xl px-4 py-3 outline-none focus:border-emerald-600 transition-all placeholder-slate-500"
              />
            </div>

            <div className="md:col-span-2">
              <label
                htmlFor="correo"
                className="block text-sm font-semibold text-slate-200 mb-2"
              >
                Correo Electrónico *
              </label>
              <input
                type="email"
                id="correo"
                value={formData.correo}
                onChange={handleChange}
                placeholder="correo@ejemplo.com"
                className="w-full bg-[#162520] border border-[#20362f] text-white text-sm rounded-xl px-4 py-3 outline-none focus:border-emerald-600 transition-all placeholder-slate-500"
              />
            </div>

            <div className="md:col-span-2 relative">
              <label
                htmlFor="rol"
                className="block text-sm font-semibold text-slate-200 mb-2"
              >
                Rol Inicial *
              </label>
              <select
                id="rol"
                value={formData.rol}
                onChange={handleChange}
                className="w-full bg-[#162520] border border-[#20362f] text-white text-sm rounded-xl px-4 py-3 outline-none focus:border-emerald-600 transition-all cursor-pointer"
              >
                <option value="empresario">Empresario</option>
                <option value="admin_inventario">Admin de Inventario</option>
                <option value="empleado">Empleado</option>
              </select>
            </div>

            <div>
              <label
                htmlFor="password_reg"
                className="block text-sm font-semibold text-slate-200 mb-2"
              >
                Contraseña *
              </label>
              <input
                type="password"
                id="password_reg"
                value={formData.password_reg}
                onChange={handleChange}
                placeholder="••••••••"
                className="w-full bg-[#162520] border border-[#20362f] text-white text-sm rounded-xl px-4 py-3 outline-none focus:border-emerald-600 transition-all placeholder-slate-500 tracking-widest"
              />
            </div>

            <div>
              <label
                htmlFor="confirm_password"
                className="block text-sm font-semibold text-slate-200 mb-2"
              >
                Confirmar Contraseña *
              </label>
              <input
                type="password"
                id="confirm_password"
                value={formData.confirm_password}
                onChange={handleChange}
                placeholder="••••••••"
                className="w-full bg-[#162520] border border-[#20362f] text-white text-sm rounded-xl px-4 py-3 outline-none focus:border-emerald-600 transition-all placeholder-slate-500 tracking-widest"
              />
            </div>
          </div>

          <button
            type="submit"
            className="w-full mt-8 bg-[#204239] hover:bg-[#2a564a] text-slate-200 font-semibold py-3 px-4 rounded-xl transition-colors shadow-lg cursor-pointer"
          >
            Registrarse
          </button>
        </form>

        <div className="mt-6 text-center text-sm text-slate-400">
          ¿Ya tienes cuenta?
          <Link
            to="/login"
            className="text-[#4b7c68] font-semibold hover:text-emerald-500 transition-colors ml-1"
          >
            Inicia sesión aquí
          </Link>
        </div>
      </div>
    </div>
  );
}
