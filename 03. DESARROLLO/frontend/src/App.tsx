import { Header } from "./components/Header";
import { SummaryCards } from "./components/SummaryCards";
import { TopProducts } from "./components/TopProducts";

function App() {
  return (
    <div className="bg-[#0b1114] min-h-screen text-slate-200 font-sans flex flex-col">
      <Header />

      <main className="flex-1 p-6 max-w-[1600px] mx-auto w-full flex flex-col gap-6">
        <div className="mb-8 bg-[#111c18] p-6 rounded-2xl border border-[#20362f]">
          <h2 className="text-3xl font-bold text-white mb-1">Dashboard</h2>
          <p className="text-slate-400">
            Vista general del sistema de inventario
          </p>
        </div>

        <SummaryCards />

        <TopProducts />
      </main>
    </div>
  );
}

export default App;
