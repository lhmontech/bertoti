console.log("LOJA CARREGADA"); // Se isso não aparecer no console, o cache está te enganando!
const API_URL = 'http://localhost:8090/comics';
const comicGrid = document.getElementById('comic-grid');
const modal = document.getElementById('modal');
const modalTitle = document.getElementById('modal-title');

// Campos do Formulário
const inputId = document.getElementById('comic-id');
const inputName = document.getElementById('name');
const inputPublisher = document.getElementById('publisher');
const inputPrice = document.getElementById('price');

// Carregar comics ao iniciar
document.addEventListener('DOMContentLoaded', fetchComics);

// Função para buscar todas as HQs
async function fetchComics() {
    try {
        const response = await axios.get(API_URL);
        renderComics(response.data);
    } catch (error) {
        console.error("Erro ao buscar HQs:", error);
        alert("Erro ao conectar com o servidor.");
    }
}

// Função para renderizar os cards na tela
function renderComics(comics) {
    comicGrid.innerHTML = '';

    comics.forEach(comic => {
        const card = document.createElement('div');
        card.className = 'comic-card bg-slate-800 p-6 rounded-xl shadow-xl flex flex-col justify-between';

        card.innerHTML = `
            <div>
                <div class="flex justify-between items-start mb-4">
                    <span class="bg-pink-600 text-xs font-bold px-2 py-1 rounded uppercase tracking-wider text-white">
                        ${comic.publisher}
                    </span>
                    <span class="text-emerald-400 font-bold text-xl">R$ ${comic.price.toFixed(2)}</span>
                </div>
                <h3 class="text-xl font-bold text-slate-100 leading-tight mb-4 uppercase hero-font">${comic.name}</h3>
            </div>
            
            <div class="flex gap-2 mt-6">
                <button onclick="editComic('${comic.id}', '${comic.name}', '${comic.publisher}', ${comic.price})" 
                    class="flex-1 bg-slate-700 hover:bg-slate-600 text-white py-2 rounded transition text-sm font-semibold">
                    Editar
                </button>
                <button onclick="deleteComic('${comic.id}')" 
                    class="bg-red-500/20 hover:bg-red-500 text-red-500 hover:text-white p-2 rounded transition">
                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16" />
                    </svg>
                </button>
            </div>
        `;
        comicGrid.appendChild(card);
    });
}

// Abrir modal para adicionar
function openModal() {
    modalTitle.innerText = "Nova HQ";
    inputId.value = "";
    inputName.value = "";
    inputPublisher.value = "";
    inputPrice.value = "";
    modal.classList.remove('hidden');
}

// Abrir modal para editar
function editComic(id, name, publisher, price) {
    modalTitle.innerText = "Editar HQ";
    inputId.value = id;
    inputName.value = name;
    inputPublisher.value = publisher;
    inputPrice.value = price;
    modal.classList.remove('hidden');
}

function closeModal() {
    modal.classList.add('hidden');
}

// Salvar (POST ou PUT)
async function saveComic() {
    const comicData = {
        name: inputName.value,
        publisher: inputPublisher.value,
        price: parseFloat(inputPrice.value)
    };

    const id = inputId.value;

    try {
        if (id) {
            // Se tem ID, é Edição (PUT)
            await axios.put(`${API_URL}/${id}`, comicData);
        } else {
            // Se não tem ID, é Novo (POST)
            await axios.post(API_URL, comicData);
        }
        closeModal();
        fetchComics();
    } catch (error) {
        console.error("Erro ao salvar:", error);
        alert("Preencha os dados corretamente!");
    }
}

// Deletar
async function deleteComic(id) {
    if (confirm("Deseja realmente excluir esta HQ?")) {
        try {
            await axios.delete(`${API_URL}/${id}`);
            fetchComics();
        } catch (error) {
            console.error("Erro ao deletar:", error);
        }
    }
}