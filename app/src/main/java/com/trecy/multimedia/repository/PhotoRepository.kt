package com.trecy.multimedia.repository

import android.content.ContentResolver
import android.net.Uri
import android.provider.OpenableColumns
import com.trecy.multimedia.MyApp
import com.trecy.multimedia.api.ApiClient
import com.trecy.multimedia.api.ApiInterface
import com.trecy.multimedia.model.PhotoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream



class PhotoRepository {
    val apiInterface= ApiClient.buildClient(ApiInterface::class.java)

     suspend fun uploadPhoto(uri: Uri,caption: String): Response<PhotoResponse>{
         return withContext(Dispatchers.IO){
             val imageFile =getFileFomUri(uri)
             val imageRequestBody= RequestBody.create("image/*".toMediaTypeOrNull(),imageFile)
             val imageRequest= MultipartBody.Part.createFormData("image",imageFile.name,imageRequestBody)
             val captionRequest=MultipartBody.Part.createFormData("caption",caption)
             apiInterface.uploadPhoto(captionRequest,imageRequest)

         }
     }
    fun getFileFomUri(uri: Uri): File{
        val context = MyApp.appContext
        val inputStream =context.contentResolver.openInputStream(uri)
        val file =File(context.filesDir, getFileNameFromUri(context.contentResolver,uri))
        val outputStream= FileOutputStream(file)
        inputStream!!.copyTo(outputStream)
        outputStream.close()
        return File(file.path)
    }

    fun getFileNameFromUri(resolver: ContentResolver, uri: Uri): String{
        val cursor =resolver.query(uri,null, null , null,null)!!
        val nameIndex= cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        cursor.moveToFirst()
        val name= cursor.getString(nameIndex)
        cursor.close()
        return name

    }
}