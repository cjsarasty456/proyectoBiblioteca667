var url = "http://localhost:8080/api/v1/usuario/";


function listarUsuario() {
  var capturarFiltro = document.getElementById("inputSearch").value;
  var urlLocal = url;
  if (capturarFiltro != "") {
    urlLocal += "busquedafiltro/" + capturarFiltro;
  }

  $.ajax({
    url: urlLocal,
    type: "GET",
    success: function(result) {
      console.log(result);

      var cuerpoTabla = document.getElementById("cuerpoTabla");
      cuerpoTabla.innerHTML = "";

      for (var i = 0; i < result.length; i++) {
        var trResgistro = document.createElement("tr");

        var celdaId = document.createElement("td");
        let celdaNumero_documento_usuario = document.createElement("td");
        let celdaNombre_usuario = document.createElement("td");
        let celdaDireccion_usuario = document.createElement("td");
        let celdaCorreo_electronico_usuario = document.createElement("td");
        let celdaTipo_usuario = document.createElement("td");


        let celdaOpcionEditar = document.createElement("td");
        let botonEditarUsuario = document.createElement("button");
        botonEditarUsuario.value = result[i]["id_usuario"];
        botonEditarUsuario.innerHTML = "Editar";
        botonEditarUsuario.onclick = function(e) {
          $('#exampleModal').modal('show');
          consultarUsuarioID(this.value);
        }
        botonEditarUsuario.className = "btn btn-warning editar-usuario";
        celdaOpcionEditar.appendChild(botonEditarUsuario);

        let celdaOpcionEliminar = document.createElement("td");
        let botonEliminarUsuario = document.createElement("button");
        botonEliminarUsuario.value = result[i]["id_usuario"];
        botonEliminarUsuario.innerHTML = "Eliminar";
        botonEliminarUsuario.onclick = function(e) {
          // Aquí deberías escribir la lógica para eliminar el libro con el id correspondiente
          // Puedes usar una función separada para realizar la eliminación
          eliminarUsuario(this.value);
        }
        botonEliminarUsuario.className = "btn btn-danger eliminar-usuario";
        celdaOpcionEliminar.appendChild(botonEliminarUsuario);

        celdaId.innerText = result[i]["id_libro"];
        celdaNumero_documento_usuario.innerText = result[i]["numero_documento_usuario"];
        celdaNombre_usuario.innerText = result[i]["nombre_usuario"];
        celdaDireccion_usuario.innerText = result[i]["direccion_usuario"];
        celdaCorreo_electronico_usuario.innerText = result[i]["correo_electronico_usuario"];
        celdaTipo_usuario.innerText = result[i]["tipo_usuario"];
        

        trResgistro.appendChild(celdaId);
        trResgistro.appendChild(celdaNumero_documento_usuario);
        trResgistro.appendChild(celdaNombre_usuario);
        trResgistro.appendChild(celdaDireccion_usuario);
        trResgistro.appendChild(celdaCorreo_electronico_usuario);
        trResgistro.appendChild(celdaTipo_usuario);


        trResgistro.appendChild(celdaOpcionEditar);
        trResgistro.appendChild(celdaOpcionEliminar);

        cuerpoTabla.appendChild(trResgistro);
      }
    },
    error: function(error) {
      alert("Error en la petición " + error);
    }
  });
}


function eliminarUsuario(idUsuario) {
  // Confirmar con el usuario antes de eliminar
  Swal.fire({
    title: "¿Estás seguro?",
    text: "¿Deseas eliminar este usuario?",
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
        url: url + idUsuario,
        type: "DELETE",
        success: function(response) {
          // Mostrar mensaje de confirmación
          Swal.fire({
            title: "¡Eliminado!",
            text: "El usuario ha sido eliminado correctamente.",
            icon: "success"
          });
          // Volver a cargar la lista de libros después de eliminar
          listarUsuario();
        },
        error: function(error) {
          // Mostrar mensaje de error si la petición falla
          Swal.fire("Error", "Error al eliminar el usuario. " + error.responseText, "error");
        }
      });
    }
  });
}




//
function consultarUsuarioID(id){
  //alert(id);
  $.ajax({
      url:url+id,
      type:"GET",
      success: function(result){
          document.getElementById("id_usuario").value=result["id_usuario"];
          document.getElementById("numero_documento_usuario").value=result["numero_documento_usuario"];
          document.getElementById("nombre_usuario").value=result["nombre_usuario"];
          document.getElementById("direccion_usuario").value=result["direccion_usuario"];
          document.getElementById("correo_electronico_usuario").value=result["correo_electronico_usuario"];
          document.getElementById("tipo_usuario").value=result["tipo_usuario"];
      }
  });
}
//2.Crear petición que actualice la información del usuario


function actualizarUsuario() { 
  var id_usuario=document.getElementById("id_usuario").value
  let formData={
      "numero_documento_usuario": document.getElementById("numero_documento_usuario").value,
      "nombre_usuario": document.getElementById("nombre_usuario").value,
      "direccion_usuario": document.getElementById("direccion_usuario").value,
      "correo_electronico_usuario": document.getElementById("correo_electronico_usuario").value,
      "tipo_usuario": document.getElementById("tipo_usuario").value
};

if (validarCampos()) {
  $.ajax({
      url:url+id_usuario,
      type: "PUT",
      data: formData,
    
      
      success: function(result) {
        
          // Manejar la respuesta exitosa según necesites
          Swal.fire({
              title: "¡Excelente!",
              text: "Se guardó correctamente",
              icon: "success"
            });
          // Puedes hacer algo adicional como recargar la lista de usuarios
          listarUsuario();
      },
      error: function(error) {
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
    var numero_documento_usuario = document.getElementById("numero_documento_usuario").value;
    var nombre_usuario = document.getElementById("nombre_usuario").value;
    var direccion_usuario = document.getElementById("direccion_usuario").value;
    var correo_electronico_usuario = document.getElementById("correo_electronico_usuario").value;
    var tipo_usuario = document.getElementById("tipo_usuario").value
  
    // Verificar si algún campo está vacío
    if (numero_documento_usuario === '' || nombre_usuario === '' || direccion_usuario === '' || correo_electronico_usuario === '' || tipo_usuario === '') {
      return false; // Al menos un campo está vacío
    } else {
      return true; // Todos los campos están llenos
    }
  }
  
}

  

function registrarUsuario() {


  let formData = {
    "numero_documento_usuario": document.getElementById("numero_documento_usuario").value,
    "nombre_usuario": document.getElementById("nombre_usuario").value,
    "direccion_usuario": document.getElementById("direccion_usuario").value,
    "correo_electronico_usuario": document.getElementById("correo_electronico_usuario").value,
    "tipo_usuario": document.getElementById("tipo_usuario").value
   

  };

  let camposValidos = true;
  let camposRequeridos = [
      "numero_documento_usuario",
      "nombre_usuario",
      "direccion_usuario",
      "correo_electronico_usuario",
      "tipo_usuario"
  ];

  camposRequeridos.forEach(function(campo) {
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
              limpiarUsuario();
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
  var numero_documento_usuario = document.getElementById("numero_documento_usuario");
  return validarNumDocumento(numero_documento_usuario);
}
function validarNumDocumento(cuadroNumero) {
  /*
  numero de documento del usuario
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

//Validar nombre usuario

function validarCampos() {
  var nombre_usuario = document.getElementById("nombre_usuario");
  return validarNombre(nombre_usuario);
}
function validarNombre(cuadroNumero) {

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

//Valida la direccion del usuario
function validarCampos() {
  var direccion_usuario = document.getElementById("direccion_usuario");
  return validarDireccion(direccion_usuario);
}
function validarDireccion(cuadroNumero) {
  
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

//Valida el correo electrónico
function validarCampos() {
  var correo_electronico_usuario = document.getElementById("correo_electronico_usuario");
  return validarCorreo(correo_electronico_usuario);
}
function validarCorreo(cuadroNumero) {
  
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

//Valida tipo de usuario
function validarCampos() {
  var tipo_usuario = document.getElementById("tipo_usuario");
  return validarTipo_usuario(tipo_usuario);
}
function validarTipo_usuario(cuadroNumero) {
  
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




function limpiarUsuario() {
  document.getElementById("numero_documento_usuario").className="form-control";
  document.getElementById("nombre_usuario").className="form-control";
  document.getElementById("direccion_usuario").className="form-control";
  document.getElementById("correo_electronico_usuario").className="form-control";
  document.getElementById("tipo_usuario").className="form-control";


  document.getElementById("numero_documento_usuario").value = "";
  document.getElementById("nombre_usuario").value = "";
  document.getElementById("direccion_usuario").value = "";
  document.getElementById("correo_electronico_usuario").value = "";
  document.getElementById("tipo_usuario").value = "";
}