
let URL = "http://localhost:8081/";


// login 
let loginButton = document.getElementById('loginButton');
loginButton.onclick = loginToApp;

async function loginToApp(){
  sessionStorage.clear();

  let user = {
    username:document.getElementById("username").value,
    password:document.getElementById("password").value
  }

  let response = await fetch(URL+"login", {
    method:"POST",
    body:JSON.stringify(user),
    credentials:"include"
  });

  if (response.status===200) {
    // let data = await response.json();
    // sessionStorage.setItem("userRole",data.role);
    let login = await response.json();
    sessionStorage.setItem("login",JSON.stringify(login));
    console.log(login);

    document.getElementById("login").innerHTML="";
    document.getElementById("access_denied").innerHTML="";
    
 
    // buttonRow.appendChild(displayAllTicketsBtn);
    // buttonRow.appendChild(displayYourTicketsBtn);
  } else{
    let para = document.createElement("p");
    para.setAttribute("style","color:red");
    para.innerText="LOGIN FAILED";
    document.getElementById("login").appendChild(para);
  }
}


/* When the user clicks on the button, 
toggle between hiding and showing the dropdown content */
function displayStatuses() {
    document.getElementById("myDropdown").classList.toggle("show");
  }
  
  // Close the dropdown if the user clicks outside of it
  window.onclick = function(event) {
    if (!event.target.matches('.dropbtn')) {
      var dropdowns = document.getElementsByClassName("dropdown-content");
      var i;
      for (i = 0; i < dropdowns.length; i++) {
        var openDropdown = dropdowns[i];
        if (openDropdown.classList.contains('show')) {
          openDropdown.classList.remove('show');
        }
      }
    }
  }

  function displayAllTickets() {
    // if ((sessionStorage.getItem("userRole"))==="MANAGER") {
    let login = JSON.parse(sessionStorage.login);
    console.log(login.role);
    

    if (login.role==="MANAGER") {

      getTickets();
      let x = document.getElementById("myDIV");
      x.style.display = "block";
   
    } else {
        accessDenied();
    }
  }
  // }

  /**
   * Default setting is for tickets to be
   * displayed so this hides it when page
   * loads
   */
  // window.onload = function() {
  //   displayAllTickets();
  // };

  async function getTickets(){
    // let response = await fetch(URL+"tickets");
    let response = await fetch(URL+"tickets", {credentials:"include"});

    if(response.status === 200){
      let data = await response.json();
      populateTable(data);
    }else{
      console.log("Could not get tickets");
    }
  }

  async function getTicketsByStatus(id){

    let login = JSON.parse(sessionStorage.login);
    console.log(login.role);

    if (login.role==="MANAGER") {
    
      let path = "";
      let status = "";

      switch (id) {

        case 'pending':
        path="ticketsByPending";
        break;

        case 'approved':
        path="ticketsByApproved";
        status="APPROVED"
        break;

        case 'denied':
        path="ticketsByDenied";
        status="DENIED"
        break;
    
        default:
          break;
      }

      let response = await fetch(URL+path);
      // let response = await fetch(URL+"tickets", {credentials:"include"});

      if(response.status === 200){
        let data = await response.json();
        populateTable(data);
      }else{
        console.log("Could not get tickets");
      }
    }else{
      document.getElementById("access_denied").innerHTML="";
      let para = document.createElement("p");
      para.setAttribute("style","color:red");
      para.innerText="UNAUTHORIZED ACCESS!";
      document.getElementById("access_denied").appendChild(para);
    }
  }

  function populateTable(data){
    let tbody = document.getElementById("ticketTable");

    // clear table
    tbody.innerHTML="";

    // build table headers for each col
    let labels = ["Select","Ticket ID", "Amount", "Submitted",
    "Resolved", "Description", "Receipt", "Author", "Resolver",
    "Status", "Type"];

    let thead = document.createElement("thead");
    let tr = document.createElement("tr");

    tbody.appendChild(thead)
    tbody.appendChild(tr)

    for (let index = 0; index < labels.length; index++) {
      let th = document.createElement("th");
      th.scope="col"
      th.innerText = labels[index];

      // save table header
      tr.appendChild(th);     
    }
  
    for(let ticket of data){

      // build check box for each row
      let row = document.createElement("tr");
      let th = document.createElement("th");
      let label = document.createElement("label");
      let input = document.createElement("input");
      let span = document.createElement("span");

      label.className = "container";
      input.type = "checkbox";
      input.check = "checked";
      span.className = "checkmark";

      th.appendChild(label);
      label.appendChild(input);
      label.appendChild(span);

      // save check box
      row.appendChild(th);

      for(let cell in ticket){
        let td = document.createElement("td");

        if((cell!="author")&&(cell!="resolver")){
          td.innerText=ticket[cell];
          
        }else if(ticket[cell]){
          td.innerText = 
          `${ticket[cell].userName}`
        }

        row.appendChild(td);
      }
      
      tbody.appendChild(row);
    }
 }

async function initSession(){
  let response = await fetch(URL+"users/:user", {credentials:"include"});

  if(response.status === 200){
    sessionStorage.clear();
    let data = await response.json();
    sessionStorage.setItem("currentUserRole",data.role);
  }else{
    console.log("Could not get user");
  }
}

function getNewTicket(){
  console.log("in getNewTicket()");
  let login = JSON.parse(sessionStorage.login);
  console.log

  let newAmount = document.getElementById("ticketAmount").value;
  let newDesc = document.getElementById("ticketDesc").value; 
  let newType = document.getElementById("inputState").value;
  let newSubmitted = Date.now();
  let newResolved = 0;
  let newReceipt = "";
  let newAuthor = login;
  let newResolver = null;
  let newStatus = "PENDING";

  let ticket =  {
    amount:newAmount,
    desc:newDesc,
    type:newType,
    submitted:newSubmitted,
    resolved:newResolved,
    receipt:newReceipt,
    author:newAuthor,
    resolver:newResolver,
    status:newStatus
  }

  return ticket;
}

async function addTicket(){
  console.log("In addTicket()");
  let ticket = getNewTicket();

  let response = await fetch(URL+"tickets", {
    method:'POST',
    body:JSON.stringify(ticket),
    credentials:"include"
  });

  if(response.status===201){
    console.log("Ticket created successfully.");
  }else{
    console.log("Something went wrong creating your ticket.")
  }
}

async function displayUsersTickets(){
  document.getElementById("access_denied").innerHTML="";

  let login = JSON.parse(sessionStorage.login);
  console.log(login.id);
  let response = await fetch(URL + "tickets/author/" + login.id, {
    credentials: "include",
  });

  if (response.status === 200) {
    let data = await response.json();
    populateTable(data);
  } else {
    console.log("Couldn't find tickets for user");
  }
}

function accessDenied(){
  document.getElementById("access_denied").innerHTML="";
  let para = document.createElement("p");
  para.setAttribute("style","color:red");
  para.innerText="UNAUTHORIZED ACCESS!";
  document.getElementById("access_denied").appendChild(para);
}

function getAllChecks(){

  let checkedBoxes = [];
  const allBoxes = document.querySelectorAll("input[type='checkbox']");

  for (let i = 0; i < allBoxes.length; i++) {
    let item = allBoxes[i];
    if (item.checked) {
      checkedBoxes.push(i);
    }
  }
  console.log(checkedBoxes);
  return checkedBoxes;
}

async function approveTickets(){
  let login = JSON.parse(sessionStorage.login);
  let allChecks = [];
  let count = 0;

  if (login.role==="MANAGER") {

    let response = await fetch(URL+"tickets", {credentials:"include"});

    if(response.status === 200){
      console.log("in approveTickets()");
      let data = await response.json();
      console.log(data);
      allChecks = getAllChecks();
      console.log("allChecks: " + allChecks);

      for (let i = 0; i < data.length; i++) {

        if (i===allChecks[count]) {
          console.log(data[i]);
          let ticket = serializeData(data[i],"APPROVED");
          updateTicket(ticket);
          count++;
        }
      }

    }else{
      console.log("Could not get tickets");
    }
  }else{
    accessDenied();
  }
}

async function denyTickets(){
  let login = JSON.parse(sessionStorage.login);
  let allChecks = [];
  let count = 0;

  if (login.role==="MANAGER") {

    let response = await fetch(URL+"tickets", {credentials:"include"});

    if(response.status === 200){
      console.log("in approveTickets()");
      let data = await response.json();
      console.log(data);
      allChecks = getAllChecks();
      console.log("allChecks: " + allChecks);

      for (let i = 0; i < data.length; i++) {

        if (i===allChecks[count]) {
          console.log(data[i]);
          let ticket = serializeData(data[i],"DENIED");
          updateTicket(ticket);
          count++;
        }
      }

    }else{
      console.log("Could not get tickets");
    }
  }else{
    accessDenied();
  }
}

async function updateTicket(data) {
  // data = createData();
  let response = await fetch(URL + "tickets", {
    method: "PUT",
    body: JSON.stringify(data),
    credentials: "include",
  });

  if (response.status === 201) {
    console.log("Ticket updated.");
  } else {
    console.log("Something went wrong submitting your ticket.");
  }
}

function serializeData(ticket,status){
  console.log("in createData()");
  console.log(ticket);
  let login = JSON.parse(sessionStorage.login);
  console.log("login: "+login);

  let newID = ticket.id;
  let newAmount = ticket.amount;
  let newDesc = ticket.desc; 
  let newType = ticket.type;
  let newSubmitted = ticket.submitted;
  let newResolved = Date.now();
  let newReceipt = "receipt";
  let newAuthor = ticket.author;
  let newResolver = login;
  let newStatus = status;

  let updatedTicket =  {
    id:newID,
    amount:newAmount,
    desc:newDesc,
    type:newType,
    submitted:newSubmitted,
    resolved:newResolved,
    receipt:newReceipt,
    author:newAuthor,
    resolver:newResolver,
    status:newStatus
  }

  return updatedTicket;
}
  