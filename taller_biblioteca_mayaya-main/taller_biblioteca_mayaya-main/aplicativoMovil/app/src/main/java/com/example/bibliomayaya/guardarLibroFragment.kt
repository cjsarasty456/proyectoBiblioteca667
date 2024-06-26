package com.example.bibliomayaya

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Request.Method
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.bibliomayaya.config.config
import com.example.bibliomayaya.models.libro
import com.google.gson.Gson
//import com.google.gson.JsonObject //genera error
import org.json.JSONObject
import java.lang.Exception
import java.nio.charset.Charset
import java.util.Base64
import java.util.Objects

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [guardarLibroFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class guardarLibroFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //definir las variables
    private lateinit var txtTitulo:EditText
    private lateinit var txtAutor:EditText
    private lateinit var tXtISBN:EditText
    private lateinit var txtGenero:EditText
    private lateinit var txtDisponible:EditText
    private lateinit var txtOcupado:EditText

    private lateinit var btnGuardar: Button
        //temporal se asigna un id existente
    private var id:String="78b282ef-3333-11ef-a079-a4bb6d503080"

    /*
    Request es petición que hace a la API
    StringRequest=responde un String
    JsonRequest=responde un json
    JsonArrayRequest=responde un arreglo de json
     */
//método encargado de traer la información del libro
    fun consultarLibro(){
        /*
        solo se debe consultar, si el ID
        es diferente a vacío
         */
        if(id!=""){

            var request=JsonObjectRequest(
                Method.GET,//método de la petición
                config.urlLibro+id, //url
                null,//parametros
                { response->
                //variable response contiene la respuesta de la API
                //se convierte de json a un objeto tipo libro
                    //se genera un objeto de la librería Gson
                    val gson= Gson()
                    //se convierte
                    val libro:libro=gson.fromJson(response.toString(),libro::class.java)
                    //se modifica el atributo text de los campos con el valor del objeto
                    txtAutor.setText(response.getString("nombre_autor"))
                    txtDisponible.setText(
                        response.getInt("num_ejemplares_disponibles").toString()
                    )
                    "id_libro": "78b282ef-3333-11ef-a079-a4bb6d503080",
                    "titulo": "cien años",
                    "nombre_autor": "gabriel",
                    "isbn": "123154165",
                    "genero": "libro",
                    "num_ejemplares_disponibles": 100,
                    "num_ejemplares_ocupados": 1

                },//respuesta correcta
                {error->
                    Toast.makeText(
                        context,
                        "Error al consultar",
                        Toast.LENGTH_LONG
                    ).show()
                } //error en la petición
            )
            //se añade la petición
            var queue=Volley.newRequestQueue(context)
            queue.add(request)
        }
    }

    fun guardarLibro(){
        try {
            if (id==""){//se crea el libro
                /*
       ejemplo de una petición que recibe un String
        //se crea la peticion
                   val request= object :StringRequest(
                       Request.Method.POST,
                       config.urlLibro,
                       Response.Listener {
                                         //método que se ejecuta cuando la peticion es correcta
                                         Toast.makeText(context,"Se guardó correctamente",
                           Toast.LENGTH_LONG).show()
                       },
                       Response.ErrorListener {
                           //método que se ejecuta cuando la peticion genera error
                       }
                   )
                   {
                       override fun getParams():Map<String,String>{
                           var parametros =HashMap<String,String>()
                           parametros.put("titulo_libro",txtTitulo.text.toString())
                           parametros.put("autor_libro",txtAutor.text.toString())
                           parametros.put("isbn_libro",tXtISBN.text.toString())
                           parametros.put("genero_libro",txtGenero.text.toString())
                           parametros.put("numero_ejemplares_disponibles",txtDisponible.text.toString())
                           parametros.put("numero_ejemplares_ocupados",txtOcupado.text.toString())
                           //uno por cada dato que se requiere
                           return parametros
                       }
                       }
        */
                var parametros=JSONObject()
                parametros.put("titulo",txtTitulo.text.toString())
                parametros.put("nombre_autor",txtAutor.text.toString())
                parametros.put("isbn",tXtISBN.text.toString())
                parametros.put("genero",txtGenero.text.toString())
                parametros.put("num_ejemplares_disponibles",txtDisponible.text.toString())
                parametros.put("num_ejemplares_ocupados",txtOcupado.text.toString())
                //uno por cada dato que se requiere

                var request=JsonObjectRequest(
                    Request.Method.POST, //método
                    config.urlLibro,//url
                    parametros,//datos de la petición
                    {response->
                        Toast.makeText(
                            context,
                            "Se guardo correctamente",
                            Toast.LENGTH_LONG
                        ).show()
                    },//cuando la respuesta es correcta
                    {error->
                        val decodeByte= Base64.getDecoder().decode(error.networkResponse.data)
                        val decoderString=String(decodeByte, Charsets.UTF_8)
                        var json=JSONObject(decoderString)
                        Toast.makeText(
                            context,
                            "Se genero un error {${error.networkResponse.data}}",
                            Toast.LENGTH_LONG
                        ).show()
                    } //cuando es incorrecta
                )
                //se crea la cola de trabajo y se añade la petición
                var queue=Volley.newRequestQueue(context)
                //se añade la petición
                queue.add(request)
            } else{//se actualiza el libro

            }

        } catch (error:Exception){

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_guardar_libro, container, false)
        txtTitulo=view.findViewById(R.id.txtTitulo)
        txtAutor=view.findViewById(R.id.txtAutor)
        tXtISBN=view.findViewById(R.id.txtISBN)
        txtGenero=view.findViewById(R.id.txtGenero)
        txtDisponible=view.findViewById(R.id.txtDisponibles)
        txtOcupado=view.findViewById(R.id.txtOcupados)
        btnGuardar=view.findViewById(R.id.btnGuardar)
        btnGuardar.setOnClickListener{guardarLibro()
        }
        consultarLibro()
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment guardarLibroFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            guardarLibroFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}