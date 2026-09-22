import { useState, useEffect, type FormEvent } from "react";
import { Header } from "../components/Header";
import { api } from "../services/api";

interface Producto {
  id?: number;
  nombre: string;
  marca: string;
  presentacion: string;
  stock: number;
  lote: string;
  vencimiento: string;
}

export function Inventario() {
  const [productos, setProductos] = useState<Producto[]>([]);
  const [showModal, setShowModal] = useState(false);
  const [productoEditar, setProductoEditar] = useState<Producto | null>(null);

  const [nuevoProducto, setNuevoProducto] = useState({
    nombre: "",
    marca: "",
    presentacion: "Bolsa",
    stock: 0,
    lote: "",
    vencimiento: "",
  });

  // traigo todos los productos del backend
  const cargarProductos = async () => {
    try {
      const response = await api.get("/productos");
      setProductos(response.data);
    } catch (error) {
      console.error("Error al cargar productos:", error);
    }
  };

  useEffect(() => {
    cargarProductos();
  }, []);

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>,
  ) => {
    setNuevoProducto({
      ...nuevoProducto,
      [e.target.name]: e.target.value,
    });
  };

  // creo el producto nuevo y recargo la tabla
  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();
    try {
      await api.post("/productos", {
        ...nuevoProducto,
        stock: Number(nuevoProducto.stock),
      });
      setNuevoProducto({
        nombre: "",
        marca: "",
        presentacion: "Bolsa",
        stock: 0,
        lote: "",
        vencimiento: "",
      });
      setShowModal(false);
      cargarProductos();
    } catch (error) {
      console.error("Error al guardar producto:", error);
    }
  };

  const handleEliminar = async (id?: number) => {
    if (!id) return;
    if (confirm("¿Estás seguro de que deseas eliminar este producto?")) {
      try {
        await api.delete(`/productos/${id}`);
        cargarProductos();
      } catch (error) {
        console.error("Error al eliminar producto:", error);
      }
    }
  };

  const handleGuardarEdicion = async (e: FormEvent) => {
    e.preventDefault();
    if (!productoEditar || !productoEditar.id) return;

    try {
      await api.put(`/productos/${productoEditar.id}`, {
        ...productoEditar,
        stock: Number(productoEditar.stock),
      });
      setProductoEditar(null);
      cargarProductos();
    } catch (error) {
      console.error("Error al actualizar producto:", error);
    }
  };

  return (
    <div className="bg-[#0b1114] min-h-screen text-slate-200 font-sans flex flex-col">
      <Header />

      <main className="flex-1 p-6 max-w-[1600px] mx-auto w-full flex flex-col gap-6">
        {/* encabezado con el botón de agregar */}
        <div className="bg-[#111c18] rounded-[2rem] p-8 border border-[#20362f] flex items-center justify-between flex-wrap gap-4">
          <div className="flex items-center gap-5">
            <div className="bg-[#162520] p-4 rounded-2xl border border-[#20362f]">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                strokeWidth="1.5"
                stroke="currentColor"
                className="w-8 h-8 text-emerald-500"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  d="M21 7.5l-9-5.25L3 7.5m18 0l-9 5.25m9-5.25v9l-9 5.25M3 7.5l9 5.25M3 7.5v9l9 5.25m0-9v9"
                />
              </svg>
            </div>
            <div>
              <h2 className="text-3xl font-bold text-white tracking-wide">
                Inventario
              </h2>
              <p className="text-slate-400 mt-1 text-sm">
                Gestión de productos y existencias
              </p>
            </div>
          </div>

          <button
            onClick={() => setShowModal(true)}
            className="bg-[#204239] hover:bg-[#2a564a] text-white px-6 py-3 rounded-full text-sm font-semibold flex items-center gap-2 transition-colors shadow-lg cursor-pointer"
          >
            <span>+</span> Agregar Inventario
          </button>
        </div>

        {/* modal para crear producto */}
        {showModal && (
          <div className="fixed inset-0 bg-black/70 flex items-center justify-center p-4 z-50">
            <div className="bg-[#111c18] border border-[#20362f] p-8 rounded-[2rem] w-full max-w-lg shadow-2xl">
              <h3 className="text-2xl font-bold text-white mb-6">
                Agregar Nuevo Producto
              </h3>

              <form onSubmit={handleSubmit} className="flex flex-col gap-4">
                <div>
                  <label className="block text-sm font-semibold text-slate-300 mb-1">
                    Nombre del Producto
                  </label>
                  <input
                    type="text"
                    name="nombre"
                    value={nuevoProducto.nombre}
                    onChange={handleChange}
                    required
                    placeholder="Ej: Arroz Diana x 1kg"
                    className="w-full bg-[#162520] border border-[#20362f] text-white text-sm rounded-xl px-4 py-3 outline-none focus:border-emerald-600"
                  />
                </div>

                <div className="grid grid-cols-2 gap-4">
                  <div>
                    <label className="block text-sm font-semibold text-slate-300 mb-1">
                      Marca
                    </label>
                    <input
                      type="text"
                      name="marca"
                      value={nuevoProducto.marca}
                      onChange={handleChange}
                      required
                      placeholder="Ej: Diana"
                      className="w-full bg-[#162520] border border-[#20362f] text-white text-sm rounded-xl px-4 py-3 outline-none focus:border-emerald-600"
                    />
                  </div>
                  <div>
                    <label className="block text-sm font-semibold text-slate-300 mb-1">
                      Presentación
                    </label>
                    <select
                      name="presentacion"
                      value={nuevoProducto.presentacion}
                      onChange={handleChange}
                      className="w-full bg-[#162520] border border-[#20362f] text-white text-sm rounded-xl px-4 py-3 outline-none focus:border-emerald-600 cursor-pointer"
                    >
                      <option value="Bolsa">Bolsa</option>
                      <option value="Caja">Caja</option>
                      <option value="Botella">Botella</option>
                      <option value="Lata">Lata</option>
                    </select>
                  </div>
                </div>

                <div className="grid grid-cols-3 gap-4">
                  <div>
                    <label className="block text-sm font-semibold text-slate-300 mb-1">
                      Stock
                    </label>
                    <input
                      type="number"
                      name="stock"
                      value={nuevoProducto.stock}
                      onChange={handleChange}
                      required
                      min="0"
                      className="w-full bg-[#162520] border border-[#20362f] text-white text-sm rounded-xl px-4 py-3 outline-none focus:border-emerald-600"
                    />
                  </div>
                  <div>
                    <label className="block text-sm font-semibold text-slate-300 mb-1">
                      Lote
                    </label>
                    <input
                      type="text"
                      name="lote"
                      value={nuevoProducto.lote}
                      onChange={handleChange}
                      required
                      placeholder="LOTE-001"
                      className="w-full bg-[#162520] border border-[#20362f] text-white text-sm rounded-xl px-4 py-3 outline-none focus:border-emerald-600"
                    />
                  </div>
                  <div>
                    <label className="block text-sm font-semibold text-slate-300 mb-1">
                      Vencimiento
                    </label>
                    <input
                      type="text"
                      name="vencimiento"
                      value={nuevoProducto.vencimiento}
                      onChange={handleChange}
                      required
                      placeholder="DD/MM/AAAA"
                      className="w-full bg-[#162520] border border-[#20362f] text-white text-sm rounded-xl px-4 py-3 outline-none focus:border-emerald-600"
                    />
                  </div>
                </div>

                <div className="flex justify-end gap-3 mt-6">
                  <button
                    type="button"
                    onClick={() => setShowModal(false)}
                    className="px-5 py-2.5 rounded-xl border border-[#20362f] text-slate-400 hover:text-white transition-colors cursor-pointer"
                  >
                    Cancelar
                  </button>
                  <button
                    type="submit"
                    className="px-6 py-2.5 rounded-xl bg-[#204239] hover:bg-[#2a564a] text-white font-semibold transition-colors shadow-lg cursor-pointer"
                  >
                    Guardar Producto
                  </button>
                </div>
              </form>
            </div>
          </div>
        )}

        {/* modal para editar producto */}
        {productoEditar && (
          <div className="fixed inset-0 bg-black/70 flex items-center justify-center p-4 z-50">
            <div className="bg-[#111c18] border border-[#20362f] p-8 rounded-[2rem] w-full max-w-lg shadow-2xl">
              <h3 className="text-2xl font-bold text-white mb-6">
                Editar Producto
              </h3>

              <form
                onSubmit={handleGuardarEdicion}
                className="flex flex-col gap-4"
              >
                <div>
                  <label className="block text-sm font-semibold text-slate-300 mb-1">
                    Nombre del Producto
                  </label>
                  <input
                    type="text"
                    value={productoEditar.nombre}
                    onChange={(e) =>
                      setProductoEditar({
                        ...productoEditar,
                        nombre: e.target.value,
                      })
                    }
                    required
                    className="w-full bg-[#162520] border border-[#20362f] text-white text-sm rounded-xl px-4 py-3 outline-none focus:border-emerald-600"
                  />
                </div>

                <div className="grid grid-cols-2 gap-4">
                  <div>
                    <label className="block text-sm font-semibold text-slate-300 mb-1">
                      Marca
                    </label>
                    <input
                      type="text"
                      value={productoEditar.marca}
                      onChange={(e) =>
                        setProductoEditar({
                          ...productoEditar,
                          marca: e.target.value,
                        })
                      }
                      required
                      className="w-full bg-[#162520] border border-[#20362f] text-white text-sm rounded-xl px-4 py-3 outline-none focus:border-emerald-600"
                    />
                  </div>
                  <div>
                    <label className="block text-sm font-semibold text-slate-300 mb-1">
                      Presentación
                    </label>
                    <select
                      value={productoEditar.presentacion}
                      onChange={(e) =>
                        setProductoEditar({
                          ...productoEditar,
                          presentacion: e.target.value,
                        })
                      }
                      className="w-full bg-[#162520] border border-[#20362f] text-white text-sm rounded-xl px-4 py-3 outline-none focus:border-emerald-600 cursor-pointer"
                    >
                      <option value="Bolsa">Bolsa</option>
                      <option value="Caja">Caja</option>
                      <option value="Botella">Botella</option>
                      <option value="Lata">Lata</option>
                    </select>
                  </div>
                </div>

                <div className="grid grid-cols-3 gap-4">
                  <div>
                    <label className="block text-sm font-semibold text-slate-300 mb-1">
                      Stock
                    </label>
                    <input
                      type="number"
                      value={productoEditar.stock}
                      onChange={(e) =>
                        setProductoEditar({
                          ...productoEditar,
                          stock: Number(e.target.value),
                        })
                      }
                      required
                      min="0"
                      className="w-full bg-[#162520] border border-[#20362f] text-white text-sm rounded-xl px-4 py-3 outline-none focus:border-emerald-600"
                    />
                  </div>
                  <div>
                    <label className="block text-sm font-semibold text-slate-300 mb-1">
                      Lote
                    </label>
                    <input
                      type="text"
                      value={productoEditar.lote}
                      onChange={(e) =>
                        setProductoEditar({
                          ...productoEditar,
                          lote: e.target.value,
                        })
                      }
                      required
                      className="w-full bg-[#162520] border border-[#20362f] text-white text-sm rounded-xl px-4 py-3 outline-none focus:border-emerald-600"
                    />
                  </div>
                  <div>
                    <label className="block text-sm font-semibold text-slate-300 mb-1">
                      Vencimiento
                    </label>
                    <input
                      type="text"
                      value={productoEditar.vencimiento}
                      onChange={(e) =>
                        setProductoEditar({
                          ...productoEditar,
                          vencimiento: e.target.value,
                        })
                      }
                      required
                      className="w-full bg-[#162520] border border-[#20362f] text-white text-sm rounded-xl px-4 py-3 outline-none focus:border-emerald-600"
                    />
                  </div>
                </div>

                <div className="flex justify-end gap-3 mt-6">
                  <button
                    type="button"
                    onClick={() => setProductoEditar(null)}
                    className="px-5 py-2.5 rounded-xl border border-[#20362f] text-slate-400 hover:text-white transition-colors cursor-pointer"
                  >
                    Cancelar
                  </button>
                  <button
                    type="submit"
                    className="px-6 py-2.5 rounded-xl bg-[#204239] hover:bg-[#2a564a] text-white font-semibold transition-colors shadow-lg cursor-pointer"
                  >
                    Actualizar
                  </button>
                </div>
              </form>
            </div>
          </div>
        )}

        {/* tabla con todo el inventario */}
        <div className="bg-[#111c18] border border-[#20362f] rounded-2xl overflow-hidden overflow-x-auto">
          <table className="w-full text-left border-collapse">
            <thead>
              <tr className="bg-[#111c18] border-b border-[#20362f] text-slate-200 text-xs font-bold">
                <th className="py-4 px-6">Producto</th>
                <th className="py-4 px-6">Marca</th>
                <th className="py-4 px-6">Presentación</th>
                <th className="py-4 px-6">Stock</th>
                <th className="py-4 px-6">Lote</th>
                <th className="py-4 px-6">Vencimiento</th>
                <th className="py-4 px-6 text-right">Acciones</th>
              </tr>
            </thead>
            <tbody className="text-sm">
              {productos.length === 0 ? (
                <tr>
                  <td colSpan={7} className="text-center py-8 text-slate-500">
                    No hay productos registrados en el inventario. ¡Agrega el
                    primero!
                  </td>
                </tr>
              ) : (
                productos.map((prod) => (
                  <tr
                    key={prod.id}
                    className="border-b border-[#20362f] hover:bg-[#162520] transition-colors"
                  >
                    <td className="py-4 px-6">
                      <div className="flex items-center gap-3">
                        <div className="bg-[#162520] border border-[#20362f] p-2 rounded-lg text-slate-400">
                          <svg
                            xmlns="http://www.w3.org/2000/svg"
                            fill="none"
                            viewBox="0 0 24 24"
                            strokeWidth="1.5"
                            stroke="currentColor"
                            className="w-5 h-5"
                          >
                            <path
                              strokeLinecap="round"
                              strokeLinejoin="round"
                              d="M21 7.5l-9-5.25L3 7.5m18 0l-9 5.25m9-5.25v9l-9 5.25M3 7.5l9 5.25M3 7.5v9l9 5.25m0-9v9"
                            />
                          </svg>
                        </div>
                        <span className="font-bold text-white">
                          {prod.nombre}
                        </span>
                      </div>
                    </td>
                    <td className="py-4 px-6 text-slate-200 font-semibold">
                      {prod.marca}
                    </td>
                    <td className="py-4 px-6 text-slate-400">
                      {prod.presentacion}
                    </td>
                    <td className="py-4 px-6">
                      <span className="inline-block border border-emerald-900/50 bg-[#122b22] text-emerald-500 px-3 py-1 rounded-full text-xs font-mono font-bold tracking-wide">
                        {prod.stock}
                      </span>
                    </td>
                    <td className="py-4 px-6 text-slate-400 font-mono text-xs">
                      {prod.lote}
                    </td>
                    <td className="py-4 px-6 text-slate-400 font-mono text-xs">
                      {prod.vencimiento}
                    </td>
                    <td className="py-4 px-6 text-right">
                      <div className="flex justify-end gap-2">
                        <button
                          onClick={() => setProductoEditar(prod)}
                          className="p-1.5 bg-[#162520] border border-[#20362f] hover:border-emerald-500 text-slate-300 hover:text-white rounded-lg transition-colors cursor-pointer"
                          title="Editar"
                        >
                          <svg
                            xmlns="http://www.w3.org/2000/svg"
                            fill="none"
                            viewBox="0 0 24 24"
                            strokeWidth="1.5"
                            stroke="currentColor"
                            className="w-4 h-4"
                          >
                            <path
                              strokeLinecap="round"
                              strokeLinejoin="round"
                              d="M16.862 4.487l1.687-1.688a1.875 1.875 0 112.652 2.652L10.582 16.07a4.5 4.5 0 01-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 011.13-1.897l8.932-8.931zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0115.75 21H5.25A2.25 2.25 0 013 18.75V8.25A2.25 2.25 0 015.25 6H10"
                            />
                          </svg>
                        </button>
                        <button
                          onClick={() => handleEliminar(prod.id)}
                          className="p-1.5 bg-[#162520] border border-[#20362f] hover:border-red-500 text-slate-300 hover:text-red-400 rounded-lg transition-colors cursor-pointer"
                          title="Eliminar"
                        >
                          <svg
                            xmlns="http://www.w3.org/2000/svg"
                            fill="none"
                            viewBox="0 0 24 24"
                            strokeWidth="1.5"
                            stroke="currentColor"
                            className="w-4 h-4"
                          >
                            <path
                              strokeLinecap="round"
                              strokeLinejoin="round"
                              d="M14.74 9l-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 01-2.244 2.077H8.084a2.25 2.25 0 01-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 00-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 013.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 00-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 00-7.5 0"
                            />
                          </svg>
                        </button>
                      </div>
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