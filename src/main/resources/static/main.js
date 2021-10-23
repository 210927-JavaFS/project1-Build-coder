const URL = "http://localhost:8081/";

let buttonRow = document.getElementById("buttonRow");
let invoiceButton = document.createElement("button");
let homeButton = document.createElement("button");
let addUserButton = document.getElementById('addUserButton');
let loginButton = document.getElementById('loginButton');

invoiceButton.onclick = getInvoices;
homeButton.onclick = getHomes;
addUserButton.onclick = addUser;
loginButton.onclick = loginToApp; 

invoiceButton.innerText = "All Invoices";
userButton.innerText = "See Users";

async function loginToApp(){
  let user = {
    username:document.getElementById("username").value,
    password:document.getElementById("password").value
  }

  let response = await fetch(URL+"login", {
    method:"POST",
    body:JSON.stringify(user),
    credentials:"include" //This will save the cookie when we receive it. 
  });

  if(response.status===200){
    document.getElementsByClassName("formClass")[0].innerHTML = '';
    buttonRow.appendChild(invoiceButton);
    buttonRow.appendChild(userButton);
  }
  else{
    let para = document.createElement("p");
    para.setAttribute("style", "color:red")
    para.innerText = "LOGIN FAILED"
    document.getElementsByClassName("formClass")[0].appendChild(para);
  }
}

async function getInvoices(){
  let response = await fetch(URL+"invoices", {credentials:"include"});

  if(response.status === 200){
    let data = await response.json();
    populateInvoicesTable(data);
  }else{
    console.log("Could not get invoices");
  }
}

function populateInvoicesTable(data){
  let tbody = document.getElementById("invoiceBody");

  tbody.innerHTML="";

  for(let invoice of data){
    let row = document.createElement("tr");

    for(let cell in invoice){
      let td = document.createElement("td");
      if(cell!="home"){
        td.innerText=invoice[cell];
      }else if(invoice[cell]){
        td.innerText = `${invoice[cell].id}: 
        ${invoice[cell].amount} 
        ${invoice[cell].submitted} 
        ${invoice[cell].resolved} 
        ${invoice[cell].desc} 
        ${invoice[cell].receipt} 
        ${invoice[cell].author}
        ${invoice[cell].resolver}
        ${invoice[cell].status}
        ${invoice[cell].type}`
      }
      row.appendChild(td);
    }
    tbody.appendChild(row);
  }
}

async function getUsers(){
  let response = await fetch(URL+"users", {credentials:"include"});
  if(response.status===200){
    let data = await response.json();
    populateHomeTable(data);
  }else{
    console.log("Could not get users");
  }
}

function populateUserTable(data){
  let tbody = document.getElementById("homeBody");

  tbody.innerHTML="";

  for(let user of data){
    let row = document.createElement("tr");
    for(let cell in user){
      let td = document.createElement("td");
      td.innerText = user[cell];
      row.appendChild(td);
    }
    tbody.appendChild(row);
  }
}

function getNewUser(){
  let newName = document.getElementById("userName").value;
  let newFirstName = document.getElementById("firstName").value; 
  let newLastName = document.getElementById("lastName").value;
  let newPassword = document.getElementById("password").value;
  let newEmail = document.getElementById("email").value;
  let newRole = document.getElementById("role").value;

  let user =  {
    name:newName,
    newFirstName:newFirstName,
    newLastName:newLastName,
    newPassword:newPassword,
    newEmail:newEmail,
    newRole:newRole
  }

  return user;
}

async function addUser(){
  let user = getNewUser();

  let response = await fetch(URL+"users", {
    method:'POST',
    body:JSON.stringify(user),
    credentials:"include"
  });

  if(response.status===201){
    console.log("User created successfully.");
  }else{
    console.log("Something went wrong creating your user.")
  }
}