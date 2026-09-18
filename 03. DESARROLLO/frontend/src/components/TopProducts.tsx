export function TopProducts() {
  return (
    <div className="bg-[#111c18] p-8 rounded-2xl border border-[#20362f]">
      <div className="flex items-center gap-3 mb-2">
        <div className="w-8 h-8 bg-[#204239] rounded-full flex items-center justify-center">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            strokeWidth="2"
            stroke="white"
            className="w-4 h-4"
          >
            <path
              strokeLinecap="round"
              strokeLinejoin="round"
              d="M2.25 18L9 11.25l4.306 4.307a11.95 11.95 0 015.814-5.519l2.74-1.22m0 0l-5.94-2.28m5.94 2.28l-2.28 5.941"
            />
          </svg>
        </div>
        <h3 className="text-2xl font-semibold text-white">
          Top 10 productos más surtidos
        </h3>
      </div>
      <p className="text-slate-400 text-sm mb-12 ml-11">
        Productos ordenados por cantidad de ventas
      </p>

      <div className="relative h-72 w-full overflow-x-auto scrollbar-hide pb-20">
        <div className="absolute inset-0 flex flex-col justify-between text-xs text-slate-500 pb-24 z-0">
          <div className="border-b border-[#20362f] flex items-end h-0">
            <span className="absolute -translate-y-2 -translate-x-8">320</span>
          </div>
          <div className="border-b border-[#20362f] flex items-end h-0">
            <span className="absolute -translate-y-2 -translate-x-8">240</span>
          </div>
          <div className="border-b border-[#20362f] flex items-end h-0">
            <span className="absolute -translate-y-2 -translate-x-8">160</span>
          </div>
          <div className="border-b border-[#20362f] flex items-end h-0">
            <span className="absolute -translate-y-2 -translate-x-6">80</span>
          </div>
          <div className="border-b border-[#20362f] flex items-end h-0">
            <span className="absolute -translate-y-2 -translate-x-4">0</span>
          </div>
        </div>

        <div className="relative z-10 flex items-end justify-between h-48 pl-6 pr-2 gap-4 mt-1">
          <div className="w-full flex flex-col items-center justify-end h-full group">
            <div className="w-full bg-[#2a4b3f] rounded-t-md transition-all hover:opacity-80 cursor-pointer h-[100%]"></div>
            <span className="absolute -bottom-20 -rotate-45 origin-top-left text-xs text-slate-300 whitespace-nowrap opacity-80 group-hover:opacity-100 group-hover:text-white transition-all">
              Arroz Diana x 1kg
            </span>
          </div>

          <div className="w-full flex flex-col items-center justify-end h-full group">
            <div className="w-full bg-[#488266] rounded-t-md transition-all hover:opacity-80 cursor-pointer h-[88%]"></div>
            <span className="absolute -bottom-20 -rotate-45 origin-top-left text-xs text-slate-300 whitespace-nowrap opacity-80 group-hover:opacity-100 group-hover:text-white transition-all">
              Atún Van Camps...
            </span>
          </div>

          <div className="w-full flex flex-col items-center justify-end h-full group">
            <div className="w-full bg-[#7c5c53] rounded-t-md transition-all hover:opacity-80 cursor-pointer h-[75%]"></div>
            <span className="absolute -bottom-20 -rotate-45 origin-top-left text-xs text-slate-300 whitespace-nowrap opacity-80 group-hover:opacity-100 group-hover:text-white transition-all">
              Leche Colanta...
            </span>
          </div>

          <div className="w-full flex flex-col items-center justify-end h-full group">
            <div className="w-full bg-[#cf9f6c] rounded-t-md transition-all hover:opacity-80 cursor-pointer h-[60%]"></div>
            <span className="absolute -bottom-20 -rotate-45 origin-top-left text-xs text-slate-300 whitespace-nowrap opacity-80 group-hover:opacity-100 group-hover:text-white transition-all">
              Galletas Jet...
            </span>
          </div>

          <div className="w-full flex flex-col items-center justify-end h-full group">
            <div className="w-full bg-[#2a4b3f] rounded-t-md transition-all hover:opacity-80 cursor-pointer h-[55%]"></div>
            <span className="absolute -bottom-20 -rotate-45 origin-top-left text-xs text-slate-300 whitespace-nowrap opacity-80 group-hover:opacity-100 group-hover:text-white transition-all">
              Coca Cola x 2L
            </span>
          </div>

          <div className="w-full flex flex-col items-center justify-end h-full group">
            <div className="w-full bg-[#ae5633] rounded-t-md transition-all hover:opacity-80 cursor-pointer h-[52%]"></div>
            <span className="absolute -bottom-20 -rotate-45 origin-top-left text-xs text-slate-300 whitespace-nowrap opacity-80 group-hover:opacity-100 group-hover:text-white transition-all">
              Yogurt Alpina...
            </span>
          </div>

          <div className="w-full flex flex-col items-center justify-end h-full group">
            <div className="w-full bg-[#488266] rounded-t-md transition-all hover:opacity-80 cursor-pointer h-[48%]"></div>
            <span className="absolute -bottom-20 -rotate-45 origin-top-left text-xs text-slate-300 whitespace-nowrap opacity-80 group-hover:opacity-100 group-hover:text-white transition-all">
              Pan Tajado Bimbo
            </span>
          </div>

          <div className="w-full flex flex-col items-center justify-end h-full group">
            <div className="w-full bg-[#488266] rounded-t-md transition-all hover:opacity-80 cursor-pointer h-[45%]"></div>
            <span className="absolute -bottom-20 -rotate-45 origin-top-left text-xs text-slate-300 whitespace-nowrap opacity-80 group-hover:opacity-100 group-hover:text-white transition-all">
              Detergente Ariel...
            </span>
          </div>

          <div className="w-full flex flex-col items-center justify-end h-full group">
            <div className="w-full bg-[#7c5c53] rounded-t-md transition-all hover:opacity-80 cursor-pointer h-[40%]"></div>
            <span className="absolute -bottom-20 -rotate-45 origin-top-left text-xs text-slate-300 whitespace-nowrap opacity-80 group-hover:opacity-100 group-hover:text-white transition-all">
              Dog Chow Adulto...
            </span>
          </div>

          <div className="w-full flex flex-col items-center justify-end h-full group">
            <div className="w-full bg-[#cf9f6c] rounded-t-md transition-all hover:opacity-80 cursor-pointer h-[38%]"></div>
            <span className="absolute -bottom-20 -rotate-45 origin-top-left text-xs text-slate-300 whitespace-nowrap opacity-80 group-hover:opacity-100 group-hover:text-white transition-all">
              Pasta Nutresa...
            </span>
          </div>
        </div>
      </div>
    </div>
  );
}
