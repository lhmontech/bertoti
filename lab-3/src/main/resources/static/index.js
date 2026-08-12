i= 0
function uuidv4() {
    return ([1e7]+-1e3+-4e3+-8e3+-1e11).replace(/[018]/g, c =>
      (c ^ crypto.getRandomValues(new Uint8Array(1))[0] & 15 >> c / 4).toString(16)
    );
  }

const btnAdd = document.getElementById('submitBtn')
function Adicionar() {
    axios.post('http://localhost:8090/comics', {
        id: inputID.value,
        name: nomeHQ.value,
        publisher: editoraHQ.value,
        price: precoHQ.value
    }) .then(response => {
        window.location.reload();
        console.log("HQ adicionada", response.data)
    }).catch(e => {
        }
    )
}
btnAdd.addEventListener('click',Adicionar)
document.addEventListener("DOMContentLoaded", () => {

    fetch("http://localhost:8090/comics")
        .then(response => response.json())
        .then(data => {
            
            const comicList = document.getElementById("comic-list");
            data.forEach(comic => {
                const listItem = document.createElement("li");
                const deleteButton = document.createElement('button') 
                const upItem = document.createElement("input")
                const upButton = document.createElement("button");
                const inputID = document.getElementById('inputID')
                const nomeHQ = document.getElementById('nomeHQ')
                inputID.setAttribute('value',uuidv4())
                upButton.innerHTML = "Editar nome";
                deleteButton.innerHTML = "Excluir";
                listItem.textContent = comic.name;
                comicList.appendChild(listItem);
                comicList.appendChild(upItem);
                comicList.appendChild(upButton);
                comicList.appendChild(deleteButton);
                upButton.setAttribute('id',i);
                
                upButton.setAttribute('class','btn btn-primary')
                deleteButton.setAttribute('class','btn btn-danger')
                i++;
                upButton.addEventListener('click',Update)
                deleteButton.addEventListener('click', Excluir)
                function Update(){
                   upItemValor = upItem.value;
                   axios.put("http://localhost:8090/comics/"+comic.id , { id: comic.id, name: upItemValor })
                   .then(response => {
                    console.log("HQ atualizada com sucesso:", response.data)
                        listItem.innerText = upItemValor
                })
                .catch(error => {
                    console.error("Erro ao atualizar o HQ:", error);
                });


                }

                function Excluir(){
                    axios.delete("http://localhost:8090/comics/"+comic.id, { id: comic.id })
                    .then(response =>{
                        console.log('Deletado', response.data)
                        upButton.remove()
                        deleteButton.remove()
                        listItem.remove()
                        upItem.remove()
                    })
                    .catch(error => {
                        console.error("Erro ao deletar", error)
                    })
                }

            });
        });
});

