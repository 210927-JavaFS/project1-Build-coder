
let URL = "http://localhost:8081/";


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
    getTickets();
    var x = document.getElementById("myDIV");
    if (x.style.display === "none") {
      x.style.display = "block";
    } else {
      x.style.display = "none";
    }
  }

  /**
   * Default setting is for tickets to be
   * displayed so this hides it when page
   * loads
   */
  window.onload = function() {
    displayAllTickets();
  };


  async function getTickets(){
    let response = await fetch(URL+"tickets");
    // let response = await fetch(URL+"tickets", {credentials:"include"});

    if(response.status === 200){
      let data = await response.json();
      populateTable(data);
    }else{
      console.log("Could not get tickets");
    }
  }

  async function getTicketsByStatus(id){
    
    let path = "";

    switch (id) {
      case 'p':
      path="ticketsByPending";
      break;

      case 'a':
      path="ticketsByApproved";
      break;

      case 'd':
      path="ticketsByDenied";
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
      let createRow = true;

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
  
      // put each field into row of table
      for(let cell in ticket){
        let td = document.createElement("td");
        if(cell!="ticket"){
          td.innerText=ticket[cell];
        }else if(ticket[cell]){
          td.innerHTML = 
          `${ticket[cell].id}:
          ${ticket[cell].amount} 
          ${ticket[cell].submitted} 
          ${ticket[cell].resolved} 
          ${ticket[cell].desc} 
          ${ticket[cell].receipt} 
          ${ticket[cell].author}
          ${ticket[cell].resolver}
          ${ticket[cell].status}
          ${ticket[cell].type}`
        }

        row.appendChild(td);
      }
     
      tbody.appendChild(row);
    }
  }

