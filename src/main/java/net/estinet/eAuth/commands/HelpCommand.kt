package net.estinet.eAuth.commands

import net.estinet.eAuth.EstiConsole

class HelpCommand : Command{
    override val name = "help"
    override val desc = "Displays the help menu."
    override fun execute(args: List<String>) {
        EstiConsole.println("<===== Help ======>")
        for(c in net.estinet.eAuth.commands){
            EstiConsole.println("/${c.name} : ${c.desc}")
        }
    }
}
