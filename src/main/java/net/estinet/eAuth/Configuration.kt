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

}