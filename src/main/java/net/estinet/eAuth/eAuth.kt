package net.estinet.eAuth

import jline.console.ConsoleReader
import jline.console.CursorBuffer
import net.estinet.eAuth.commands.Command
import java.util.*

object EstiConsole{
    val version = "v0.0.9"
    fun println(str: String){
        stashLine()
        System.out.println(str)
        unstashLine()
    }
}

val commands = ArrayList<Command>()

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
     * Index MongoDB for auth.
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
