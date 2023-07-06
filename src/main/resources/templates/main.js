// Arquivo que faz a conexao com a API

// const url = "http://localhost:8080/task/user/2";

// function hideLoader() {
//   document.getElementById("loading").style.display = "none";
// }

// function show(tasks) {
//          let tab = `<thead>
//                            <th scope="col">#</th>
//                            <th scope="col">Description</th>
//                            <th scope="col">Username</th>
//                            <th scope="col">User Id</th>

//                   </thead>`;

//          for (let task of tasks) {
//                   tab += `
//                   <tr>
//                            <td scope="row" > ${task.id} </td>
//                            <td scope="row" > ${task.description} </td>
//                            <td scope="row" > ${task.user.username} </td>
//                            <td scope="row" > ${task.user.id} </td>
//                   </tr>`;

//          }

//          document.getElementById("tasks").innerHTML = tab;
// }

// // Funcao assincrona aquela que nao ee instantanea, vai carregar depois da pagina carregar.
// //  usa-se mais quando estamos atratar assuntos que vem de fora do nosso localhost

// async function getApi(url) {
//          const response = await fetch(url, {method:"GET"});

//          var data = await response.json();
//          console.log("ola Mundo");
//          if (response) {
//                   hideLoader();
//          }
//          show(data);
// }

// getApi(url);

// gpt

const url = "http://localhost:8080/task/user/1";

function hideLoader() {
  document.getElementById("loading").style.display = "none";
}

function show(tasks) {
  let tab = `
    <thead>
      <th scope="col">#</th>
      <th scope="col">Description</th>
      <th scope="col">Username</th>
      <th scope="col">User Id</th>
    </thead>
  `;

  for (let task of tasks) {
    tab += `
      <tr>
        <td scope="row">${task.id}</td>
        <td scope="row">${task.description}</td>
        <td scope="row">${task.user.username}</td>
        <td scope="row">${task.user.id}</td>
      </tr>`;
  }

  document.getElementById("tasks").innerHTML = tab;
}

async function getApi(url) {
  try {
    const response = await fetch(url, { method: "GET" });

    if (response.ok) {
      const data = await response.json();
      hideLoader();
      show(data);
    } else {
      throw new Error("Error: " + response.status);
    }
  } catch (error) {
    console.log(error);
    // Lógica para lidar com erros na chamada da API
  }
}

getApi(url);
