
let URL = "http://localhost:8081/";


/* When the user clicks on the button, 
toggle between hiding and showing the dropdown content */
function displayMenu() {
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
      console.log(JSON.stringify(data));
      populateTable(data);
    }else{
      console.log("Could not get tickets");
    }
  }

  function populateTable(data){
    let tbody = document.getElementById("ticketTable");
  
    // clear table
    tbody.innerHTML="";

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
      tr.appendChild(th);     
    }

    let head = document.createElement("th");
    let label = document.createElement("label");
    label.className = "container";

  
    for(let ticket of data){
      let row = document.createElement("tr");

      let th = document.createElement("th");
      let label = document.createElement("label");
      th.appendChild(label);
      label.className = "container";
      let input = document.createElement("input");
      input.type = "checkbox";
      input.check = "checked";
      label.appendChild(input);
      let span = document.createElement("span");
      span.className = "checkmark";
      label.appendChild(span);

      row.appendChild(th);
  
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