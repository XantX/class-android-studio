package com.example.app_camara

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private val CAMERA_RESQUET_CODE = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btCamara = findViewById<Button>(R.id.btPermisos)
        btCamara.setOnClickListener {
            checkCamaraPermission()
        }
    }

    private fun checkCamaraPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA )
        != PackageManager.PERMISSION_GRANTED)
        {
            //no tengo permiso
            resquestCameraPermission()
        }else
        {
            // tengo permiso
           Toast.makeText(this, "Ya se cuenta con acceso a al camara", Toast.LENGTH_SHORT).show()
        }
    }

    private fun resquestCameraPermission() {
       if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA))
       {
           //el permiso fue negado, debe ir a ajustes
           Toast.makeText(this, "Rechazo el permiso antes, habilitelo manualmente",
               Toast.LENGTH_SHORT).show()
       }
        else{
           // 1ra ver que se pide el permiso
           Toast.makeText(this, "Debe aceptar el permiso",
               Toast.LENGTH_SHORT).show()
           ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_RESQUET_CODE)
       }
    }

    //escuchar la rpta
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            CAMERA_RESQUET_CODE -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    //PERMISO ACEPTADO. Se puede llamar a la funcionalidad
                    Toast.makeText(this, "Se autorizo el permiso",
                        Toast.LENGTH_SHORT).show()
                }else{
                    //permiso denegado
                    Toast.makeText(this, "Se denego el permiso",
                        Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }
}