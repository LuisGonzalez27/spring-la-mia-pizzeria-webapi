//GLOBAL VARIABLES
const PIZZE_URL = 'http://localhost:8080/api/v1/pizze';
const contentElement = document.getElementById("content");
const toggleForm = document.getElementById("toggle-form");
const pizzaForm = document.getElementById("pizza-form");

//API
const getPizzaList = async() => {
    const response = await fetch(PIZZE_URL);
    return response;
}

const postPizza = async(jsonData) => {
    const fetchOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: jsonData,
    };
    const response = await fetch(PIZZE_URL, fetchOptions);
    return response;
}

//DOM
const toggleFormVisibility = () => {
    document.getElementById("form").classList.toggle('d-none');
}
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

//Other
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

const savePizza = async (event) => {
    event.preventDefault();
    const formData = new FormData(event.target);
    
    const formDataObj = Object.fromEntries(formData.entries());
    
    const formDataJson = JSON.stringify(formDataObj);

    const response = await postPizza(formDataJson);

    const responseBody = await response.json();

    if(response.ok){
        loadData();
        event.target.reset();
    } else {
        console.log('ERROR');
        console.log(response.status);
        console.log(responseBody);
    }
};

//Global code
toggleForm.addEventListener('click' , toggleFormVisibility);
pizzaForm.addEventListener('submit', savePizza);
loadData();