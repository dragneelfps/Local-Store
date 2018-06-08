package com.nooblabs.example.localstore.util

import android.content.Context
import java.io.IOException
import java.nio.charset.Charset

@Throws(IOException::class)
fun loadJSONFromAsset(context: Context, jsonFileName: String): String{
    (context.assets).open(jsonFileName).let {
        val buffer = ByteArray(it.available())
        it.read(buffer)
        it.close()
        return String(buffer, Charset.forName("UTF-8"))
    }
}

/*
@CheckResult
@Throws(IOException::class)
fun loadJSONFromAsset(context: Context, jsonFileName: String): String{
    (context.assets).open(jsonFileName).let {
        val time = Date()
        val bfs = BufferedReader(InputStreamReader(it))
        val total = StringBuilder()
        var line = bfs.readLine()
        while( line != null){
            total.append(line + "\n")
            line = bfs.readLine()
        }
        bfs.close()
        val elapsedTime = Date()
        Log.d("xyz_test","Time taken: ${elapsedTime.time - time.time}")
        return total.toString()
    }
}*/
