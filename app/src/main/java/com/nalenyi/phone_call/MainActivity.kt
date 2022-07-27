package com.nalenyi.phone_call

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    lateinit var etContact : EditText
    val REQUEST_PHONE_CALL =1
    lateinit var btnCall :Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnCall=findViewById(R.id.btnCall)
        etContact = findViewById(R.id.etContact)

//        val action_phone_call
        btnCall.setOnClickListener {

            if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE) !=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE),REQUEST_PHONE_CALL)

         }else{

             startCall()
        }

        }
    }
//    @SuppressLint("MissingPermissions")
    private fun startCall(){
        val number : String = etContact.text.toString()
        val callIntent = Intent(Intent.ACTION_CALL)
        val dial = "tel: $number"
    startActivity(Intent(Intent.ACTION_CALL, Uri.parse(dial)))
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PHONE_CALL)startCall()
    }
}