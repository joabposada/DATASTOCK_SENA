import { useState, useEffect, type FormEvent } from "react";
import { Header } from "../components/Header";
import { api } from "../services/api";

interface Usuario {
  id?: number;
  nombre: string;
  correo: string;
  rol: string;
  password?: string;
}

export function Usuarios() {
  const [usuarios, setUsuarios] = useState<Usuario[]>([]);
  const [showModal, setShowModal] = useState(false);
  const [usuarioEditar, setUsuarioEditar] = useState<Usuario | null>(null);

  const [nuevoUsuario, setNuevoUsuario] = useState({
    nombre: "",
    correo: "",
    rol: "empresario",
    password: "",
  });

  // busco la lista de usuarios en el backend
  const cargarUsuarios = async () => {
    try {
      const response = await api.get("/usuarios");
      setUsuarios(response.data);
    } catch (error) {
      console.error("Error al cargar usuarios:", error);
    }
  };

  useEffect(() => {
    cargarUsuarios();
  }, []);

  const handleCrear = async (e: FormEvent) => {
    e.preventDefault();
    try {
      await api.post("/usuarios", nuevoUsuario);
      setNuevoUsuario({
        nombre: "",
        correo: "",
        rol: "empresario",
        password: "",
      });
      setShowModal(false);
      cargarUsuarios();
    } catch (error) {
      console.error("Error al crear usuario:", error);
    }
  };

  const handleEliminar = async (id?: number) => {
    if (!id) return;
    if (confirm("¿Deseas eliminar este usuario?")) {
      try {
        await api.delete(`/usuarios/${id}`);
        cargarUsuarios();
      } catch (error) {
        console.error("Error al eliminar usuario:", error);
      }
    }
  };

  const handleEditar = async (e: FormEvent) => {
    e.preventDefault();
    if (!usuarioEditar || !usuarioEditar.id) return;
    try {
      await api.put(`/usuarios/${usuarioEditar.id}`, usuarioEditar);
      setUsuarioEditar(null);
      cargarUsuarios();
    } catch (error) {
      console.error("Error al actualizar usuario:", error);
    }
  };

  return (
    <div className="bg-[#0b1114] min-h-screen text-slate-200 font-sans flex flex-col">
      <Header />
      <main className="flex-1 p-6 max-w-[1600px] mx-auto w-full flex flex-col gap-6">
        <div className="bg-[#111c18] rounded-[2rem] p-8 border border-[#20362f] flex items-center justify-between flex-wrap gap-4">
          <div>
            <h2 className="text-3xl font-bold text-white tracking-wide">
              Gestión de Usuarios
            </h2>
            <p className="text-slate-400 mt-1 text-sm">
              Administración de cuentas y permisos
            </p>
          </div>
          <button
            onClick={() => setShowModal(true)}
            className="bg-[#204239] hover:bg-[#2a564a] text-white px-6 py-3 rounded-full text-sm font-semibold cursor-pointer"
          >
            + Nuevo Usuario
          </button>
        </div>

        {/* modal para crear usuario */}
        {showModal && (
          <div className="fixed inset-0 bg-black/70 flex items-center justify-center p-4 z-50">
            <div className="bg-[#111c18] border border-[#20362f] p-8 rounded-[2rem] w-full max-w-md shadow-2xl">
              <h3 className="text-2xl font-bold text-white mb-6">
                Crear Usuario
              </h3>
              <form onSubmit={handleCrear} className="flex flex-col gap-4">
                <input
                  type="text"
                  placeholder="Nombre completo"
                  value={nuevoUsuario.nombre}
                  onChange={(e) =>
                    setNuevoUsuario({ ...nuevoUsuario, nombre: e.target.value })
                  }
                  required
                  className="w-full bg-[#162520] border border-[#20362f] text-white p-3 rounded-xl outline-none"
                />
                <input
                  type="email"
                  placeholder="Correo electrónico"
                  value={nuevoUsuario.correo}
                  onChange={(e) =>
                    setNuevoUsuario({ ...nuevoUsuario, correo: e.target.value })
                  }
                  required
                  className="w-full bg-[#162520] border border-[#20362f] text-white p-3 rounded-xl outline-none"
                />
                <select
                  value={nuevoUsuario.rol}
                  onChange={(e) =>
                    setNuevoUsuario({ ...nuevoUsuario, rol: e.target.value })
                  }
                  className="w-full bg-[#162520] border border-[#20362f] text-white p-3 rounded-xl outline-none"
                >
                  <option value="empresario">Empresario</option>
                  <option value="admin_inventario">Admin de Inventario</option>
                  <option value="empleado">Empleado</option>
                </select>
                <input
                  type="password"
                  placeholder="Contraseña"
                  value={nuevoUsuario.password}
                  onChange={(e) =>
                    setNuevoUsuario({
                      ...nuevoUsuario,
                      password: e.target.value,
                    })
                  }
                  required
                  className="w-full bg-[#162520] border border-[#20362f] text-white p-3 rounded-xl outline-none"
                />
                <div className="flex justify-end gap-3 mt-4">
                  <button
                    type="button"
                    onClick={() => setShowModal(false)}
                    className="px-4 py-2 text-slate-400 cursor-pointer"
                  >
                    Cancelar
                  </button>
                  <button
                    type="submit"
                    className="px-5 py-2 bg-[#204239] text-white rounded-xl cursor-pointer"
                  >
                    Guardar
                  </button>
                </div>
              </form>
            </div>
          </div>
        )}

        {/* modal para editar usuario */}
        {usuarioEditar && (
          <div className="fixed inset-0 bg-black/70 flex items-center justify-center p-4 z-50">
            <div className="bg-[#111c18] border border-[#20362f] p-8 rounded-[2rem] w-full max-w-md shadow-2xl">
              <h3 className="text-2xl font-bold text-white mb-6">
                Editar Usuario
              </h3>
              <form onSubmit={handleEditar} className="flex flex-col gap-4">
                <input
                  type="text"
                  value={usuarioEditar.nombre}
                  onChange={(e) =>
                    setUsuarioEditar({
                      ...usuarioEditar,
                      nombre: e.target.value,
                    })
                  }
                  required
                  className="w-full bg-[#162520] border border-[#20362f] text-white p-3 rounded-xl outline-none"
                />
                <input
                  type="email"
                  value={usuarioEditar.correo}
                  onChange={(e) =>
                    setUsuarioEditar({
                      ...usuarioEditar,
                      correo: e.target.value,
                    })
                  }
                  required
                  className="w-full bg-[#162520] border border-[#20362f] text-white p-3 rounded-xl outline-none"
                />
                <select
                  value={usuarioEditar.rol}
                  onChange={(e) =>
                    setUsuarioEditar({ ...usuarioEditar, rol: e.target.value })
                  }
                  className="w-full bg-[#162520] border border-[#20362f] text-white p-3 rounded-xl outline-none"
                >
                  <option value="empresario">Empresario</option>
                  <option value="admin_inventario">Admin de Inventario</option>
                  <option value="empleado">Empleado</option>
                </select>
                <div className="flex justify-end gap-3 mt-4">
                  <button
                    type="button"
                    onClick={() => setUsuarioEditar(null)}
                    className="px-4 py-2 text-slate-400 cursor-pointer"
                  >
                    Cancelar
                  </button>
                  <button
                    type="submit"
                    className="px-5 py-2 bg-[#204239] text-white rounded-xl cursor-pointer"
                  >
                    Actualizar
                  </button>
                </div>
              </form>
            </div>
          </div>
        )}

        {/* tabla de usuarios */}
        <div className="bg-[#111c18] border border-[#20362f] rounded-2xl overflow-hidden">
          <table className="w-full text-left border-collapse">
            <thead>
              <tr className="border-b border-[#20362f] text-slate-200 text-xs font-bold">
                <th className="py-4 px-6">Nombre</th>
                <th className="py-4 px-6">Correo</th>
                <th className="py-4 px-6">Rol</th>
                <th className="py-4 px-6 text-right">Acciones</th>
              </tr>
            </thead>
            <tbody className="text-sm">
              {usuarios.length === 0 ? (
                <tr>
                  <td colSpan={4} className="text-center py-8 text-slate-500">
                    No hay usuarios registrados.
                  </td>
                </tr>
              ) : (
                usuarios.map((u) => (
                  <tr
                    key={u.id}
                    className="border-b border-[#20362f] hover:bg-[#162520]"
                  >
                    <td className="py-4 px-6 font-bold text-white">
                      {u.nombre}
                    </td>
                    <td className="py-4 px-6 text-slate-300">{u.correo}</td>
                    <td className="py-4 px-6">
                      <span className="bg-[#122b22] text-emerald-400 px-3 py-1 rounded-full text-xs uppercase">
                        {u.rol}
                      </span>
                    </td>
                    <td className="py-4 px-6 text-right">
                      <button
                        onClick={() => setUsuarioEditar(u)}
                        className="p-1.5 bg-[#162520] border border-[#20362f] text-slate-300 rounded-lg mr-2 cursor-pointer"
                      >
                        ✏️
                      </button>
                      <button
                        onClick={() => handleEliminar(u.id)}
                        className="p-1.5 bg-[#162520] border border-[#20362f] text-red-400 rounded-lg cursor-pointer"
                      >
                        🗑️
                      </button>
                    </td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </div>
      </main>
    </div>
  );
}