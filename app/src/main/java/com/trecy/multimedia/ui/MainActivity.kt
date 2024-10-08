package com.trecy.multimedia.ui
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.trecy.multimedia.databinding.ActivityMainBinding
import com.trecy.multimedia.utils.Constants
import com.trecy.multimedia.viewmodel.PhotoViewModel

//
//import android.net.Uri
//import android.os.Bundle
//import android.provider.MediaStore.Audio.Media
//import android.view.LayoutInflater
//import android.widget.Toast
//import androidx.activity.enableEdgeToEdge
//import androidx.activity.result.ActivityResultLauncher
//import androidx.activity.result.PickVisualMediaRequest
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.activity.viewModels
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.LayoutInflaterCompat
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import com.squareup.picasso.Picasso
//import com.trecy.multimedia.R
//import com.trecy.multimedia.databinding.ActivityMainBinding
//import com.trecy.multimedia.utils.Constants
//import com.trecy.multimedia.viewmodel.PhotoViewModel
//
//
//class MainActivity : AppCompatActivity() {
//    val photosViewModel: PhotoViewModel by viewModels()
//    lateinit var binding: ActivityMainBinding
//    lateinit var pickMedia: ActivityResultLauncher<PickVisualMediaRequest>
//    var photoUri: Uri? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//    }
//
//    override fun onResume() {
//        super.onResume()
//        pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){ uri ->
//            if(uri != null){
//                photoUri = uri
//
//            }
//        }
//
//        binding.btnUpload
//
//        binding.ivPhoto.setOnClickListener{
//            val mimeType = "image/"
//            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.SingleMimeType(mimeType)))
//        }
//        photosViewModel.errorLiveData.observe(this){error ->
//            Toast.makeText(this, error, Toast.LENGTH_LONG)
//
//        }
//        photosViewModel.photoResponseLiveData.observe(this){photoResponse ->
//            val imageUrl ="${Constants.BASEURL}${photoResponse.image}"
//            Picasso.get()
//                .load(imageUrl)
//                .into(binding.ivPhoto)
//
//        }
//    }
//
//    private fun validateForm(){
//        var err = false
//        if(photoUri==null){
//            err = true
//            Toast.makeText(this, "Please select a photo", Toast.LENGTH_LONG).show()
//        }
//
//        var caption = binding.etCaption.error.toString()
//        if(caption.isBlank()){
//            err = true
//            binding.etCaption.error = "Caption required"
//        }
//
//        if(!err){
//            photosViewModel.postPhoto(photoUri!!, caption)
//
//        }
//    }
//
//
//}
//
//
//
//
//
//
//
//



class MainActivity : AppCompatActivity() {
    val photoViewModel: PhotoViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    lateinit var pickMedia: ActivityResultLauncher<PickVisualMediaRequest>
    var photoUri: Uri?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                photoUri=uri
                //do uploud
            }
        }


        binding.btnUpload.setOnClickListener{
            validateForm()
        }


    }


    override fun onResume() {
        super.onResume()
        binding.ivPhoto.setOnClickListener {
            val mimeType = "image/*"
            pickMedia.launch(PickVisualMediaRequest(
                ActivityResultContracts.PickVisualMedia.SingleMimeType(
                    mimeType)))


        }


        photoViewModel.errorLiveData.observe(this){error->
            Toast.makeText(this,error,Toast.LENGTH_LONG)
        }


        photoViewModel.photoResponseLiveData.observe(this){photoResponse->
            val imageUrl = "${Constants.BASEURL}${photoResponse.image}"
            Picasso.get()
                .load(imageUrl)
                .into(binding.ivPhoto)
        }
    }




    private fun validateForm(){
        var err = false
        if(photoUri==null){
            err = true
            Toast.makeText(this,"Please select a photo", Toast.LENGTH_LONG).show()
        }


        val caption = binding.etCaption.text.toString()
        if(caption.isBlank()){
            err=true
            binding.etCaption.error="Caption required"
        }
        if(!err){
            photoViewModel.postPhoto(photoUri!!, caption)
        }
    }




}
