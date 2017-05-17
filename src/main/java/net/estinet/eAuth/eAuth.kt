package net.estinet.eAuth

import com.corundumstudio.socketio.SocketIOClient
import jline.console.ConsoleReader
import jline.console.CursorBuffer
import net.estinet.eAuth.commands.Command
import java.util.*
import com.aerospike.client.policy.ClientPolicy
import com.aerospike.client.AerospikeClient
import com.aerospike.client.Host

object EstiConsole{
    val version = "v0.0.9"
    fun println(str: String){
        stashLine()
        System.out.println(str)
        unstashLine()
    }
}

val commands = ArrayList<Command>()
val adminSessions = HashMap<String, SocketIOClient>()

var networkOn = false



var port = 2169
var pass = "pass123"
var aPort = 3000
var aIP = "localhost"
var aPass = "pass123"

val users = HashMap<UUID, String>()
val curKeys = HashMap<UUID, UUID>()

var console: ConsoleReader = ConsoleReader()
private var stashed: CursorBuffer? = null

fun println(str: String){
    stashLine()
    System.out.println(str)
    unstashLine()
}

fun main(args: Array<String>){
    println("eAuth ${EstiConsole.version}")

    /*
     * Initialize Commands.
     */

    commands.add(net.estinet.eAuth.commands.VersionCommand())
    commands.add(net.estinet.eAuth.commands.HelpCommand())

    /*
     * Index Aerospike for auth.
     */

    indexDatabase()

    /*
     * Start SocketIO server
     */

    SocketIO.start()

    /*
     * Start command system.
     */

    while (true) {
        console.setPrompt(">");
        val input = console.readLine()
        val inputs = input.split(" ")
        for(c in commands){
            if(c == inputs){
                val list = ArrayList<String>()
                var i = 1
                while(i < inputs.size){
                    list.add(inputs[i])
                    i++
                }
                c.execute(list)
                break;
            }
        }
    }
}

fun indexDatabase(){
    val hosts = arrayOf(Host("a.host", 3000), Host("another.host", 3000), Host("and.another.host", 3000))
    val client = AerospikeClient(ClientPolicy(), *hosts)

}

fun stashLine() {
    stashed = console.getCursorBuffer().copy();
    try {
        console.getOutput().write("\u001b[1G\u001b[K");
        console.flush();
    } catch (e: Exception) {
        // ignore
    }
}

fun unstashLine() {
    try {
        console.resetPromptLine(console.getPrompt(), stashed.toString(), stashed!!.cursor)
    } catch (e: Exception) {
        // ignore
    }
}
