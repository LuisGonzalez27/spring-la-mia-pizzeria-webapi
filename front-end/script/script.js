//GLOBAL VARIABLES
const PIZZE_URL = 'http://localhost:8080/api/v1/pizze';
const contentElement = document.getElementById("content");


//API
const getPizzaList = async() => {
    const response = await fetch(PIZZE_URL);
    return response;
}

//DOM


const createIngredientiList = (ingredienti) => {
    let ingredientiHtml = "<p>";

    ingredienti.forEach(el=>{
        ingredientiHtml += `<span class="mx-1">${el.name}</span>`;
    });

    ingredientiHtml += '</p>';
    return ingredientiHtml;
}


const createPizzaItem = (item) => {
    return `<div class="col-4"> 
                <div class="card h-100">
                    <div class="card-body">
                        <h5 class="card-title">${item.nome}</h5>
                        <p class="card-text">${item.descrizione}</p>
                        <p class="card-text">${item.prezzo} EUR</p>
                    </div>

                    <div class="card-footer">
                        ${createIngredientiList(item.ingredienti)}
                    </div>
                </div>
    </div>`;
}

const createPizzaList = (data) => {
    console.log(data);

    let list = `<div class="row gy-4">`;
    
    data.forEach(element => {
        list += createPizzaItem(element);
    });

    list += '</div>';
    return list;
}

const loadData = async() => {
    const response = await getPizzaList();
    console.log(response);

    if(response.ok){
        const data = await response.json();
        

        //render html
        contentElement.innerHTML = createPizzaList(data);


    } else {
        console.log('ERROR');
    }

}

//Global code
loadData()