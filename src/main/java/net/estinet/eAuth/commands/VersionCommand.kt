package net.estinet.eAuth.commands

import net.estinet.eAuth.EstiConsole

class VersionCommand : Command{
    override val name = "version"
    override val desc = "Displays the version number."
    override fun execute(args: List<String>) {
        EstiConsole.println("net.estinet.eAuth.EstiConsole ${EstiConsole.version}")
    }
}
