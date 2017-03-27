package net.estinet.eAuth

import java.io.*
import java.util.*
//seshpenguin was here pok
fun makeConfig(){
    val f = File("config.properties")
    if(!f.exists()) f.createNewFile()
    val prop = Properties()
    var output: FileOutputStream? = null
    var reader: InputStreamReader? = null
    try {
        reader = InputStreamReader(FileInputStream(f))
        prop.load(reader)
        output = FileOutputStream(f)
        if (prop.getProperty("port") == null) {
            println("Input eAuth port (Default: 2169):")
            var input = console.readLine()
            if (input == "") input = "2169"
            prop.setProperty("port", input)
        }
        if (prop.getProperty("adminPass") == null) {
            println("Input admin password (Default: pass123):")
            var input = console.readLine()
            if (input == "") input = "pass123"
            prop.setProperty("adminPass", input)
        }
        if (prop.getProperty("aeroPort") == null) {
            println("Input aerospike port (Default: 3000):")
            var input = console.readLine()
            if (input == "") input = "3000"
            prop.setProperty("aeroPort", input)
        }
        if (prop.getProperty("aeroIP") == null) {
            println("Input aerospike IP (Default: localhost):")
            var input = console.readLine()
            if (input == "") input = "localhost"
            prop.setProperty("aeroIP", input)
        }
        if (prop.getProperty("aeroPass") == null) {
            println("Input aerospike password (Default: pass123):")
            var input = console.readLine()
            if (input == "") input = "pass123"
            prop.setProperty("aeroPass", input)
        }

    } catch (io: IOException) {
        io.printStackTrace()
    } finally {
        try {
            prop.store(output, null)
            if(reader != null) reader.close()
            if(output != null) output.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        callConfig()
    }
}
fun callConfig(){
    val prop = Properties()
    var input: InputStream? = null
    try {
        input = FileInputStream("config.properties")
        prop.load(input)
        port = prop.getProperty("port").toInt()
        pass = prop.getProperty("adminPass")
        aPort = prop.getProperty("aeroPort").toInt()
        aIP = prop.getProperty("aeroIP")
        aPass = prop.getProperty("aeroPass")
    } catch (ex: IOException) {
        ex.printStackTrace()
    } finally {
        if (input != null) {
            try {
                input.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }
}