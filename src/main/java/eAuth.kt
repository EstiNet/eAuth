object EstiConsole{
    val version = "v0.0.9"
}

fun println(str: String){
    System.out.println(str)
}

fun main(args: Array<String>){
    println("eAuth ${EstiConsole.version}")

}
