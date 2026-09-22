import { useState, useEffect, type FormEvent } from "react";
import { Header } from "../components/Header";
import { api } from "../services/api";

interface Sitio {
  id?: number;
  nombre: string;
}

export function Tiendas() {
  const [sitios, setSitios] = useState<Sitio[]>([]);
  const [showModal, setShowModal] = useState(false);
  const [sitioEditar, setSitioEditar] = useState<Sitio | null>(null);
  const [nombreSitio, setNombreSitio] = useState("");

  const cargarSitios = async () => {
    try {
      const response = await api.get("/sitios");
      setSitios(response.data);
    } catch (error) {
      console.error("Error al cargar sitios:", error);
    }
  };

  useEffect(() => {
    cargarSitios();
  }, []);

  const handleCrear = async (e: FormEvent) => {
    e.preventDefault();
    try {
      await api.post("/sitios", { nombre: nombreSitio });
      setNombreSitio("");
      setShowModal(false);
      cargarSitios();
    } catch (error) {
      console.error(error);
    }
  };

  const handleEliminar = async (id?: number) => {
    if (!id) return;
    if (confirm("¿Eliminar esta tienda/sitio?")) {
      await api.delete(`/sitios/${id}`);
      cargarSitios();
    }
  };

  const handleEditar = async (e: FormEvent) => {
    e.preventDefault();
    if (!sitioEditar || !sitioEditar.id) return;
    await api.put(`/sitios/${sitioEditar.id}`, sitioEditar);
    setSitioEditar(null);
    cargarSitios();
  };

  return (
    <div className="bg-[#0b1114] min-h-screen text-slate-200 font-sans flex flex-col">
      <Header />
      <main className="flex-1 p-6 max-w-[1600px] mx-auto w-full flex flex-col gap-6">
        <div className="bg-[#111c18] rounded-[2rem] p-8 border border-[#20362f] flex items-center justify-between">
          <div>
            <h2 className="text-3xl font-bold text-white">
              Gestión de Tiendas y Sitios
            </h2>
            <p className="text-slate-400 mt-1 text-sm">
              Puntos de almacenamiento y sucursales
            </p>
          </div>
          <button
            onClick={() => setShowModal(true)}
            className="bg-[#204239] hover:bg-[#2a564a] text-white px-6 py-3 rounded-full text-sm font-semibold cursor-pointer"
          >
            + Nueva Tienda
          </button>
        </div>

        {/* modal para crear tienda */}
        {showModal && (
          <div className="fixed inset-0 bg-black/70 flex items-center justify-center p-4 z-50">
            <div className="bg-[#111c18] border border-[#20362f] p-8 rounded-[2rem] w-full max-w-md">
              <h3 className="text-2xl font-bold text-white mb-4">
                Agregar Tienda
              </h3>
              <form onSubmit={handleCrear} className="flex flex-col gap-4">
                <input
                  type="text"
                  placeholder="Nombre de la bodega/tienda"
                  value={nombreSitio}
                  onChange={(e) => setNombreSitio(e.target.value)}
                  required
                  className="bg-[#162520] border border-[#20362f] text-white p-3 rounded-xl outline-none"
                />
                <div className="flex justify-end gap-2">
                  <button
                    type="button"
                    onClick={() => setShowModal(false)}
                    className="text-slate-400 px-4 py-2"
                  >
                    Cancelar
                  </button>
                  <button
                    type="submit"
                    className="bg-[#204239] text-white px-5 py-2 rounded-xl"
                  >
                    Guardar
                  </button>
                </div>
              </form>
            </div>
          </div>
        )}

        {/* modal para editar tienda */}
        {sitioEditar && (
          <div className="fixed inset-0 bg-black/70 flex items-center justify-center p-4 z-50">
            <div className="bg-[#111c18] border border-[#20362f] p-8 rounded-[2rem] w-full max-w-md">
              <h3 className="text-2xl font-bold text-white mb-4">
                Editar Tienda
              </h3>
              <form onSubmit={handleEditar} className="flex flex-col gap-4">
                <input
                  type="text"
                  value={sitioEditar.nombre}
                  onChange={(e) =>
                    setSitioEditar({ ...sitioEditar, nombre: e.target.value })
                  }
                  required
                  className="bg-[#162520] border border-[#20362f] text-white p-3 rounded-xl outline-none"
                />
                <div className="flex justify-end gap-2">
                  <button
                    type="button"
                    onClick={() => setSitioEditar(null)}
                    className="text-slate-400 px-4 py-2"
                  >
                    Cancelar
                  </button>
                  <button
                    type="submit"
                    className="bg-[#204239] text-white px-5 py-2 rounded-xl"
                  >
                    Actualizar
                  </button>
                </div>
              </form>
            </div>
          </div>
        )}

        {/* tabla de tiendas */}
        <div className="bg-[#111c18] border border-[#20362f] rounded-2xl overflow-hidden">
          <table className="w-full text-left border-collapse">
            <thead>
              <tr className="border-b border-[#20362f] text-slate-200 text-xs font-bold">
                <th className="py-4 px-6">Nombre de la Tienda / Bodega</th>
                <th className="py-4 px-6 text-right">Acciones</th>
              </tr>
            </thead>
            <tbody>
              {sitios.length === 0 ? (
                <tr>
                  <td colSpan={2} className="text-center py-8 text-slate-500">
                    No hay tiendas registradas.
                  </td>
                </tr>
              ) : (
                sitios.map((s) => (
                  <tr
                    key={s.id}
                    className="border-b border-[#20362f] hover:bg-[#162520]"
                  >
                    <td className="py-4 px-6 font-bold text-white">
                      {s.nombre}
                    </td>
                    <td className="py-4 px-6 text-right">
                      <button
                        onClick={() => setSitioEditar(s)}
                        className="p-1.5 bg-[#162520] border border-[#20362f] text-slate-300 rounded-lg mr-2 cursor-pointer"
                      >
                        ✏️
                      </button>
                      <button
                        onClick={() => handleEliminar(s.id)}
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