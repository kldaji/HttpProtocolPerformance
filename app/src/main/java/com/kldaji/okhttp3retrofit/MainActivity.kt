package com.kldaji.okhttp3retrofit

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.SystemClock
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.kldaji.okhttp3retrofit.databinding.ActivityMainBinding
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sampleService1: SampleService
    private lateinit var sampleService2: SampleService
    private lateinit var sampleAdapter: SampleAdapter
    private val sampleViewModel by viewModels<SampleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.pb.isVisible = false

        val retrofit1 = Retrofit.Builder()
            .baseUrl("https://picsum.photos/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().protocols(listOf(Protocol.HTTP_1_1)).build())
            .build()
        val retrofit2 = Retrofit.Builder()
            .baseUrl("https://picsum.photos/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        sampleService1 = retrofit1.create(SampleService::class.java)
        sampleService2 = retrofit2.create(SampleService::class.java)

        setSampleAdapter()
        setButtonClickListener()
        setImagesObserver()
    }

    private fun setSampleAdapter() {
        sampleAdapter = SampleAdapter()
        binding.rv.adapter = sampleAdapter
        val screenWidth = Resources.getSystem().displayMetrics.widthPixels
        val gridLayoutManager = GridLayoutManager(this, screenWidth / 100)
        binding.rv.layoutManager = gridLayoutManager
    }

    private fun setButtonClickListener() {
        binding.btnHttp11.setOnClickListener { doWork(sampleService1) }
        binding.btnHttp2.setOnClickListener { doWork(sampleService2) }
    }

    private fun doWork(sampleService: SampleService) {
        GlobalScope.launch {
            val start = SystemClock.elapsedRealtime()
            runOnUiThread {
                sampleViewModel.clearImages()
                binding.tvTime.text = ""
                binding.pb.isVisible = true
                binding.btnHttp11.isEnabled = false
                binding.btnHttp2.isEnabled = false
            }
            val deferreds = mutableListOf<Deferred<Image>>()
            for (i in 1..100) {
                deferreds.add(async {
                    val responseBody = sampleService.getImage()
                    val bitmap = BitmapFactory.decodeStream(responseBody.byteStream())
                    val image = Image(i, bitmap)
                    runOnUiThread {
                        sampleViewModel.addImage(image)
                    }
                    image
                })
            }
            deferreds.awaitAll()

            runOnUiThread {
                binding.pb.isVisible = false
                binding.btnHttp11.isEnabled = true
                binding.btnHttp2.isEnabled = true
                val end = SystemClock.elapsedRealtime()
                binding.tvTime.text = "${end - start} ms"
            }
        }
    }

    private fun setImagesObserver() {
        sampleViewModel.images.observe(this) { images ->
            sampleAdapter.submitList(images)
        }
    }
}
