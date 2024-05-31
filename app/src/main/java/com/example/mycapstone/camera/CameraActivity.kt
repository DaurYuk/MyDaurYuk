package com.example.mycapstone.camera

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import retrofit2.HttpException
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresExtension
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.lifecycleScope
import com.example.mycapstone.R
import com.example.mycapstone.camera.CameraActivity2.Companion.CAMERAX_RESULT
import com.example.mycapstone.camera.api.ApiConfig
import com.example.mycapstone.camera.api.FileUploadResponse
import com.example.mycapstone.databinding.ActivityCameraBinding
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

class CameraActivity : AppCompatActivity() {
    private lateinit var binding:ActivityCameraBinding
    private var currentImageUri: Uri? = null

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ){ isGranted: Boolean ->
            if (isGranted){
                Toast.makeText(this, "Permission request granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Permission request denied", Toast.LENGTH_LONG).show()
            }

        }
    private fun allPermissionGranted() =
        ContextCompat.checkSelfPermission(
            this,
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(!allPermissionGranted()){
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }

        binding.galleryButton.setOnClickListener { startGallery() }
        binding.cameraButton.setOnClickListener { startCamera() }
//        binding.cameraXButton.setOnClickListener { startCameraX() }
        binding.uploadButton.setOnClickListener { uploadImage() }
    }

    // Gallery
    private fun startGallery(){
       launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ){uri: Uri? ->
        if (uri != null){
            currentImageUri = uri
            showImage()
        }else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private fun showImage(){
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.previewImageView.setImageURI(it)
        }
    }

    // Camera
    private fun startCamera(){
        currentImageUri = getImageUri(this)
        launcherIntentCamera.launch(currentImageUri)
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ){ isSuccess: Boolean ->
        if (isSuccess){
            showImage()
        }
    }

    // Camera X
    private fun startCameraX(){
        val intent = Intent(this, CameraActivity2::class.java)
        launchIntentCameraX.launch(intent)
    }

    private val launchIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){if (it.resultCode == CAMERAX_RESULT)
        currentImageUri = it.data?.getStringExtra(CameraActivity2.EXTRA_CAMERAX_IMAGE)?.toUri()
        showImage()
    }

    // Upload Image
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun uploadImage() {
        currentImageUri?.let { uri ->
            val imageFile = uriToFile(uri, this).reduceFileImage()
            Log.d("Image Classification File", "showImage: ${imageFile.path}")
            showLoading(true)

            val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
            val multipartBody = MultipartBody.Part.createFormData(
                "photo",
                imageFile.name,
                requestImageFile
            )
            lifecycleScope.launch {
                try {
                    val apiService = ApiConfig.getApiService()
                    val successResponse = apiService.uploadImage(multipartBody)
                    with(successResponse.data){
                        binding.resultTextView.text = if (isAboveThreshold == true){
                            showToast(successResponse.message.toString())
                            String.format("tidak kena %s | Tingkat GoodLooking %.2f%%", result, confidenceScore)
                        }else{
                            showToast("Model is predicted successfully but under threshold.")
                            String.format("Tingkat kemiripan dengan monyet %.2f%%", confidenceScore)
                        }
                    }
                    showLoading(false)
                }catch (e: HttpException) {
                    val errorBody = e.response()?.errorBody()?.string()
                    if (errorBody != null) {
                        try {
                            val errorResponse = Gson().fromJson(errorBody, FileUploadResponse::class.java)
                            if (errorResponse != null) {
                                showToast(errorResponse.message.toString())
                            } else {
                                showToast("Unknown error occurred.")
                            }
                        } catch (ex: Exception) {
                            showToast("Failed to parse error response.")
                        }
                    } else {
                        showToast("No error body available.")
                    }
                    showLoading(false)
                }
            }

        }?: showToast(getString(R.string.empty_image_warning))
    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
    }
}