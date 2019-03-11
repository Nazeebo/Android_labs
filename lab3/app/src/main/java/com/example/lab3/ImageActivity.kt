package com.example.lab3

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity : AppCompatActivity() {

    companion object {
        val IMAGE_KEY = "image_key"
        val NAME_KEY = "name_key"
    }

    lateinit var photoPath: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        text.text = intent.getStringExtra(NAME_KEY)
        photoPath = intent.getStringExtra(IMAGE_KEY)

        setPic()
    }

    private fun setPic() {
        //imageView.setImageBitmap(BitmapFactory.decodeFile(photoPath))
        // Get the dimensions of the View
        val targetW: Int = imageView.maxHeight
        val targetH: Int = imageView.maxWidth

        val bmOptions = BitmapFactory.Options().apply {
            // Get the dimensions of the bitmap
            inJustDecodeBounds = true
            BitmapFactory.decodeFile(photoPath, this)
            val photoW: Int = outWidth
            val photoH: Int = outHeight

            // Determine how much to scale down the image
            val scaleFactor: Int = Math.min(photoW / targetW, photoH / targetH)

            // Decode the image file into a Bitmap sized to fill the View
            inJustDecodeBounds = false
            inSampleSize = scaleFactor
        }
        BitmapFactory.decodeFile(photoPath, bmOptions)?.also { bitmap ->
            imageView.setImageBitmap(bitmap)
        }
    }
}
