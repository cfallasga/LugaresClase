package com.lugares_u

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lugares_u.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //Objeto para tener acceso a los controles creados en la vista...
    private lateinit var binding: ActivityMainBinding

    //Objeto para realizar la comunicacion con FirebaseAuth
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) //fije la vista de contenido con lo que esta en activity_main.xml

        //Inicializo el objeto de autenticacion, realmente Firebase
        FirebaseApp.initializeApp(this)

        auth = Firebase.auth
        //control para los botonoes
        binding.btLogin.setOnClickListener { haceLogin() }
        binding.btRegister.setOnClickListener { haceRegistro() }
    }

    private fun haceRegistro() {
        val correo = binding.etCorreo.text.toString()
        val clave = binding.etClave.text.toString()

        auth.createUserWithEmailAndPassword(correo,clave)
            .addOnCompleteListener(this) { task ->
                if(task.isSuccessful){ //si se hizo el registro
                    Log.d("creando usuario","Registrado")
                    val user = auth.currentUser
                    refresca(user)
                } else { // si no se hizo el registro
                    Log.d("creando usuario","Falló")
                    Toast.makeText(baseContext, "Falló", Toast.LENGTH_LONG).show()
                    refresca(null)
                }

            }
    }

    private fun refresca(user: FirebaseUser?) {
        if (user != null){
            //Me paso a la pantalla principal...
            val intent = Intent(this, Principal::class.java)
            startActivity(intent)
        }
    }

    //Esto hara que una vez autenticado... No pida mas a menos que cierre sesion
    public override fun onStart(){
        super.onStart() // preguntar al profesor por esta recursividad
        val usuario = auth.currentUser
        refresca(usuario)
    }

    private fun haceLogin() {
        val correo = binding.etCorreo.text.toString()
        val clave = binding.etClave.text.toString()
        //se hace el login
        // NOTA LA MONADA CAMBIO AL INGRESO DE DE CORREO Y CONTRASENNA
        auth.signInWithEmailAndPassword(correo,clave)
            .addOnCompleteListener(this) { task ->
                if(task.isSuccessful){ //si se hizo el registro
                    Log.d("Autenticando","Autenticado")
                    val user = auth.currentUser
                    refresca(user)
                } else { // si no se hizo el registro
                    Log.d("Autenticando","Falló")
                    Toast.makeText(baseContext, "Falló", Toast.LENGTH_LONG).show()
                    refresca(null)
                }

            }
    }
    // metodo para agregar en principal
   /* override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.log ->{
                Firebase.auth.signOut()
                finish()
                true
            }else -> super.onOptionsItemSelected(item)
        }
    }*/

}