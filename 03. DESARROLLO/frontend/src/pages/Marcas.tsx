import { useState, useEffect, type FormEvent } from "react";
import { Header } from "../components/Header";
import { api } from "../services/api";

export function Marcas() {
  const [marcas, setMarcas] = useState<any[]>([]);
  const [showModal, setShowModal] = useState(false);
  const [nombre, setNombre] = useState("");

  const cargar = async () => {
    const res = await api.get("/marcas");
    setMarcas(res.data);
  };

  useEffect(() => {
    cargar();
  }, []);

  const crear = async (e: FormEvent) => {
    e.preventDefault();
    await api.post("/marcas", { nombre });
    setNombre("");
    setShowModal(false);
    cargar();
  };

  const eliminar = async (id: number) => {
    if (confirm("¿Eliminar marca?")) {
      await api.delete(`/marcas/${id}`);
      cargar();
    }
  };

  return (
    <div className="bg-[#0b1114] min-h-screen text-slate-200 font-sans flex flex-col">
      <Header />
      <main className="flex-1 p-6 max-w-[1600px] mx-auto w-full flex flex-col gap-6">
        <div className="bg-[#111c18] rounded-[2rem] p-8 border border-[#20362f] flex items-center justify-between">
          <h2 className="text-3xl font-bold text-white">Gestión de Marcas</h2>
          <button
            onClick={() => setShowModal(true)}
            className="bg-[#204239] text-white px-6 py-3 rounded-full text-sm font-semibold cursor-pointer"
          >
            + Nueva Marca
          </button>
        </div>

        {showModal && (
          <div className="fixed inset-0 bg-black/70 flex items-center justify-center p-4 z-50">
            <div className="bg-[#111c18] border border-[#20362f] p-8 rounded-[2rem] w-full max-w-md">
              <h3 className="text-2xl font-bold text-white mb-4">
                Agregar Marca
              </h3>
              <form onSubmit={crear} className="flex flex-col gap-4">
                <input
                  type="text"
                  placeholder="Nombre de marca"
                  value={nombre}
                  onChange={(e) => setNombre(e.target.value)}
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

        <div className="bg-[#111c18] border border-[#20362f] rounded-2xl overflow-hidden">
          <table className="w-full text-left border-collapse">
            <thead>
              <tr className="border-b border-[#20362f] text-slate-200 text-xs font-bold">
                <th className="py-4 px-6">Marca</th>
                <th className="py-4 px-6 text-right">Acciones</th>
              </tr>
            </thead>
            <tbody>
              {marcas.map((m) => (
                <tr
                  key={m.id}
                  className="border-b border-[#20362f] hover:bg-[#162520]"
                >
                  <td className="py-4 px-6 font-bold text-white">{m.nombre}</td>
                  <td className="py-4 px-6 text-right">
                    <button
                      onClick={() => eliminar(m.id)}
                      className="p-1.5 bg-[#162520] border border-[#20362f] text-red-400 rounded-lg cursor-pointer"
                    >
                      🗑️
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </main>
    </div>
  );
}
