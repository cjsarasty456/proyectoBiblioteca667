var url = "http://localhost:8080/api/v1/libro/";


document.getElementById("titulo_libro")
  .addEventListener("keypress", soloLetras);

/*
Este método solo permite letras
*/
const letrasPermitidas = [
  'a', 'b','c','A','B','C','á','Á','ñ','Ñ', ' '
];
const numerosPermitidos = [
  '1',
  '2',
  '3',
  '4',
  '5',
  '6',
  '7',
  '8',
  '9',
  '0'
]
const caracteresPermitidos = [
  '@', ' ', '_', '-', '.'
]
function soloLetras(event) {
  console.log("Llave presionada: " + event.key);
  console.log("Código tecla: " + event.keyCode);
  /*
  se debe ajustar para aceptar mayúsculas minúsculas, espacio, ñ
  letras con tildes
  */
  if (!(letrasPermitidas.includes(event.key))) {
    event.preventDefault();
    return;
  }

}



function listarLibro() {
  var capturarFiltro = document.getElementById("inputSearch").value;
  var urlLocal = url;
  if (capturarFiltro != "") {
    urlLocal += "busquedafiltro/" + capturarFiltro;
  }

  $.ajax({
    url: urlLocal,
    type: "GET",
    success: function (result) {
      console.log(result);

      var cuerpoTabla = document.getElementById("cuerpoTabla");
      cuerpoTabla.innerHTML = "";

      for (var i = 0; i < result.length; i++) {
        var trResgistro = document.createElement("tr");

        var celdaId = document.createElement("td");
        let celdaTituloLibro = document.createElement("td");
        let celdaAutorLibro = document.createElement("td");
        let celdaIsbnLibro = document.createElement("td");
        let celdaGeneroLibro = document.createElement("td");
        let celdaEjemplaresDisponibles = document.createElement("td");
        let celdaEjemplaresOcupados = document.createElement("td");

        let celdaOpcionEditar = document.createElement("td");
        let botonEditarLibro = document.createElement("button");
        botonEditarLibro.value = result[i]["id_libro"];
        botonEditarLibro.innerHTML = "Editar";
        botonEditarLibro.onclick = function (e) {
          $('#exampleModal').modal('show');
          consultarLibroID(this.value);
        }
        botonEditarLibro.className = "btn btn-warning editar-libro";
        celdaOpcionEditar.appendChild(botonEditarLibro);

        let celdaOpcionEliminar = document.createElement("td");
        let botonEliminarLibro = document.createElement("button");
        botonEliminarLibro.value = result[i]["id_libro"];
        botonEliminarLibro.innerHTML = "Eliminar";
        botonEliminarLibro.onclick = function (e) {
          // Aquí deberías escribir la lógica para eliminar el libro con el id correspondiente
          // Puedes usar una función separada para realizar la eliminación
          eliminarLibro(this.value);
        }
        botonEliminarLibro.className = "btn btn-danger eliminar-libro";
        celdaOpcionEliminar.appendChild(botonEliminarLibro);

        celdaId.innerText = result[i]["id_libro"];
        celdaTituloLibro.innerText = result[i]["titulo_libro"];
        celdaAutorLibro.innerText = result[i]["autor_libro"];
        celdaIsbnLibro.innerText = result[i]["isbn_libro"];
        celdaGeneroLibro.innerText = result[i]["genero_libro"];
        celdaEjemplaresDisponibles.innerText = result[i]["numero_ejemplares_disponibles"];
        celdaEjemplaresOcupados.innerText = result[i]["numero_ejemplares_ocupados"];

        trResgistro.appendChild(celdaId);
        trResgistro.appendChild(celdaTituloLibro);
        trResgistro.appendChild(celdaAutorLibro);
        trResgistro.appendChild(celdaIsbnLibro);
        trResgistro.appendChild(celdaGeneroLibro);
        trResgistro.appendChild(celdaEjemplaresDisponibles);
        trResgistro.appendChild(celdaEjemplaresOcupados);
        trResgistro.appendChild(celdaOpcionEditar);
        trResgistro.appendChild(celdaOpcionEliminar);

        cuerpoTabla.appendChild(trResgistro);
      }
    },
    error: function (error) {
      alert("Error en la petición " + error);
    }
  });
}


function eliminarLibro(idLibro) {
  // Confirmar con el usuario antes de eliminar
  Swal.fire({
    title: "¿Estás seguro?",
    text: "¿Deseas eliminar este libro?",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "Sí, eliminar",
    cancelButtonText: "Cancelar"
  }).then((result) => {
    if (result.isConfirmed) {
      // Realizar la petición AJAX para eliminar el libro
      $.ajax({
        url: url + idLibro,
        type: "DELETE",
        success: function (response) {
          // Mostrar mensaje de confirmación
          Swal.fire({
            title: "¡Eliminado!",
            text: "El libro ha sido eliminado correctamente.",
            icon: "success"
          });
          // Volver a cargar la lista de libros después de eliminar
          listarLibro();
        },
        error: function (error) {
          // Mostrar mensaje de error si la petición falla
          Swal.fire("Error", "Error al eliminar el libro. " + error.responseText, "error");
        }
      });
    }
  });
}




//
function consultarLibroID(id) {
  //alert(id);
  $.ajax({
    url: url + id,
    type: "GET",
    success: function (result) {
      document.getElementById("id_libro").value = result["id_libro"];
      document.getElementById("titulo_libro").value = result["titulo_libro"];
      document.getElementById("autor_libro").value = result["autor_libro"];
      document.getElementById("isbn_libro").value = result["isbn_libro"];
      document.getElementById("genero_libro").value = result["genero_libro"];
      document.getElementById("numero_ejemplares_disponibles").value = result["numero_ejemplares_disponibles"];
      document.getElementById("numero_ejemplares_ocupados").value = result["numero_ejemplares_ocupados"];
    }
  });
}
//2.Crear petición que actualice la información del libro


function actualizarLibro() {
  var id_libro = document.getElementById("id_libro").value
  let formData = {
    "titulo_libro": document.getElementById("titulo_libro").value,
    "autor_libro": document.getElementById("autor_libro").value,
    "isbn_libro": document.getElementById("isbn_libro").value,
    "genero_libro": document.getElementById("genero_libro").value,
    "numero_ejemplares_disponibles": document.getElementById("numero_ejemplares_disponibles").value,
    "numero_ejemplares_ocupados": document.getElementById("numero_ejemplares_ocupados").value
  };

  if (validarCampos()) {
    $.ajax({
      url: url + id_libro,
      type: "PUT",
      data: formData,


      success: function (result) {

        // Manejar la respuesta exitosa según necesites
        Swal.fire({
          title: "¡Excelente!",
          text: "Se guardó correctamente",
          icon: "success"
        });
        // Puedes hacer algo adicional como recargar la lista de libros
        listarLibro();
      },
      error: function (error) {
        // Manejar el error de la petición
        Swal.fire({
          title: "¡Error!",
          text: "No se guardó",
          icon: "error"
        });
      },
      error: function (error) {
        Swal.fire("Error", "Error al guardar, " + error.responseText, "error");
      }
    });
  } else {
    Swal.fire({
      title: "¡Error!",
      text: "Llene todos los campos correctamente",
      icon: "error"
    });
  }
  function validarCampos() {
    // Obtener los valores de los campos
    var titulo_libro = document.getElementById("titulo_libro").value;
    var autor_libro = document.getElementById("autor_libro").value;
    var isbn_libro = document.getElementById("isbn_libro").value;
    var genero_libro = document.getElementById("genero_libro").value;
    var numero_ejemplares_disponibles = document.getElementById("numero_ejemplares_disponibles").value;
    var numero_ejemplares_ocupados = document.getElementById("numero_ejemplares_ocupados").value

    // Verificar si algún campo está vacío
    if (titulo_libro === '' || autor_libro === '' || isbn_libro === '' || genero_libro === '' || numero_ejemplares_disponibles === '' || numero_ejemplares_ocupados === '') {
      return false; // Al menos un campo está vacío
    } else {
      return true; // Todos los campos están llenos
    }
  }

}



function registrarLibro() {


  let formData = {
    "titulo_libro": document.getElementById("titulo_libro").value,
    "autor_libro": document.getElementById("autor_libro").value,
    "isbn_libro": document.getElementById("isbn_libro").value,
    "genero_libro": document.getElementById("genero_libro").value,
    "numero_ejemplares_disponibles": document.getElementById("numero_ejemplares_disponibles").value,
    "numero_ejemplares_ocupados": document.getElementById("numero_ejemplares_ocupados").value

  };

  let camposValidos = true;
  let camposRequeridos = [
    "titulo_libro",
    "autor_libro",
    "isbn_libro",
    "genero_libro",
    "numero_ejemplares_disponibles",
    "numero_ejemplares_ocupados"
  ];

  camposRequeridos.forEach(function (campo) {
    let valorCampo = document.getElementById(campo).value.trim();
    if (valorCampo === "") {
      camposValidos = false;
      return false; // Terminar la iteración si se encuentra un campo vacío
    }
  });

  if (camposValidos) {
    $.ajax({
      url: url,
      type: "POST",
      data: formData,
      success: function (result) {
        Swal.fire({
          title: "¡Excelente!",
          text: "Se guardó correctamente",
          icon: "success"
        });
        limpiarLibro();
      },
      error: function (error) {
        Swal.fire("Error", "Error al guardar, " + error.responseText, "error");
      },
    });

  } else {
    Swal.fire({
      title: "¡Error!",
      text: "Llene todos los campos correctamente",
      icon: "error"
    });
  }

}

//se ejecuta la peticion


function validarCampos() {
  var isbn_libro = document.getElementById("isbn_libro");
  return validarIsbn_libro(isbn_libro);
}
function validarIsbn_libro(cuadroNumero) {
  /*
  isbn del libro
  min=5
  max=40
  numero entero

  si cumple, se cambia color a verde
  si no, se cambia a rojo
  */
  var valor = cuadroNumero.value;
  var valido = true;
  if (valor.length < 5 || valor.length > 40) {
    valido = false
  }

  if (valido) {
    //cuadro de texto cumple
    cuadroNumero.className = "form-control is-valid";
  } else {
    //cuadro de texto no cumple
    cuadroNumero.className = "form-control is-invalid";
  }
  return valido;

}

//ValidadAutor

function validarCampos() {
  var autor_libro = document.getElementById("autor_libro");
  return validarAutor_libro(autor_libro);
}
function validarAutor_libro(cuadroNumero) {

  var valor = cuadroNumero.value;
  var valido = true;
  if (valor.length < 3 || valor.length > 40) {
    valido = false
  }

  if (valido) {
    //cuadro de texto cumple
    cuadroNumero.className = "form-control is-valid";
  } else {
    //cuadro de texto no cumple
    cuadroNumero.className = "form-control is-invalid";
  }
  return valido;

}

//Valida el titulo del libro
function validarCampos() {
  var titulo_libro = document.getElementById("titulo_libro");
  return validarTitulo_libro(titulo_libro);
}
function validarTitulo_libro(cuadroNumero) {

  var valor = cuadroNumero.value;
  var valido = true;
  if (valor.length < 2 || valor.length > 40) {
    valido = false
  }

  if (valido) {
    //cuadro de texto cumple
    cuadroNumero.className = "form-control is-valid";
  } else {
    //cuadro de texto no cumple
    cuadroNumero.className = "form-control is-invalid";
  }
  return valido;

}

//Valida el genero
function validarCampos() {
  var genero_libro = document.getElementById("genero_libro");
  return validarGenero_libro(genero_libro);
}
function validarGenero_libro(cuadroNumero) {

  var valor = cuadroNumero.value;
  var valido = true;
  if (valor.length < 5 || valor.length > 40) {
    valido = false
  }

  if (valido) {
    //cuadro de texto cumple
    cuadroNumero.className = "form-control is-valid";
  } else {
    //cuadro de texto no cumple
    cuadroNumero.className = "form-control is-invalid";
  }
  return valido;

}

//Valida los numeros de ejemplares que estan disponibles
function validarCampos() {
  var numero_ejemplares_disponibles = document.getElementById("numero_ejemplares_disponibles");
  return validarNumero_ejemplares_disponibles(numero_ejemplares_disponibles);
}
function validarNumero_ejemplares_disponibles(cuadroNumero) {

  var valor = cuadroNumero.value;
  var valido = true;
  if (valor.length < 1 || valor.length > 40) {
    valido = false
  }

  if (valido) {
    //cuadro de texto cumple
    cuadroNumero.className = "form-control is-valid";
  } else {
    //cuadro de texto no cumple
    cuadroNumero.className = "form-control is-invalid";
  }
  return valido;

}
//Valida los numeros de ejemplares que ya estan ocupados


function validarCampos() {
  var numero_ejemplares_ocupados = document.getElementById("numero_ejemplares_ocupados");
  return validarNumero_ejemplares_ocupados(numero_ejemplares_ocupados);
}
function validarNumero_ejemplares_ocupados(cuadroNumero) {

  var valor = cuadroNumero.value;
  var valido = true;
  if (valor.length < 1 || valor.length > 40) {
    valido = false
  }

  if (valido) {
    //cuadro de texto cumple
    cuadroNumero.className = "form-control is-valid";
  } else {
    //cuadro de texto no cumple
    cuadroNumero.className = "form-control is-invalid";
  }
  return valido;

}




function limpiarLibro() {
  document.getElementById("titulo_libro").className = "form-control";
  document.getElementById("autor_libro").className = "form-control";
  document.getElementById("isbn_libro").className = "form-control";
  document.getElementById("genero_libro").className = "form-control";
  document.getElementById("numero_ejemplares_disponibles").className = "form-control";
  document.getElementById("numero_ejemplares_ocupados").className = "form-control";


  document.getElementById("titulo_libro").value = "";
  document.getElementById("autor_libro").value = "";
  document.getElementById("isbn_libro").value = "";
  document.getElementById("genero_libro").value = "";
  document.getElementById("numero_ejemplares_disponibles").value = "";
  document.getElementById("numero_ejemplares_ocupados").value = "";
}