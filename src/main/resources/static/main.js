
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
      getTickets();
      var x = document.getElementById("myDIV");
      if (x.style.display === "none") {
        x.style.display = "block";
      } else {
        x.style.display = "none";
      }
    // } else {
      // if (((sessionStorage.getItem("currentUserRole"))==="EMPLOYEE")) {
        document.getElementById("access_denied").innerHTML="";
        let para = document.createElement("p");
        para.setAttribute("style","color:red");
        para.innerText="UNAUTHORIZED ACCESS!";
        document.getElementById("access_denied").appendChild(para);
      }
  //   }
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

        if(cell!="author"){
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
  let newAmount = document.getElementById("ticketAmount").value;
  let newDesc = document.getElementById("ticketDesc").value; 
  let newType = document.getElementById("inputState").value;
  let newSubmitted = Date.now();
  let newResolved = 0;
  let newReceipt = "";
  let newAuthor = null;
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
