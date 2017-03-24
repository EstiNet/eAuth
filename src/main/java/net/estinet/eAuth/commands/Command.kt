package net.estinet.eAuth.commands

interface Command{
    val name: String
    val desc: String
    fun execute(args: List<String>)
}