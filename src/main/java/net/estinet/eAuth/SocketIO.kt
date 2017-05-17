package net.estinet.eAuth

import com.corundumstudio.socketio.AckRequest
import com.corundumstudio.socketio.Configuration
import com.corundumstudio.socketio.SocketIOClient
import com.corundumstudio.socketio.SocketIOServer

object SocketIO {
    lateinit var sslServer: SocketIOServer
    fun start() {
        net.estinet.eAuth.println("Starting server...")

        //eAuth.println("Initializing SSL context...")
        //val sslContext = initSslContext()

        val config: Configuration = Configuration();
        config.setPort(port)

        sslServer = SocketIOServer(config)
        networkOn = true
        sslServer.addEventListener("admin", String::class.java, { client: SocketIOClient, data: String, ack: AckRequest ->
            run {
                val password = data.split(" ")[0]
                try {
                    if (password == pass) {
                        adminSessions.put(client.sessionId.toString(), client)
                        ack.sendAckData("authed")
                    } else {
                        ack.sendAckData("authfail")
                    }
                }
                catch(e: Throwable){
                    ack.sendAckData("authfail")
                    e.printStackTrace()
                }
            }
        })
        sslServer.addEventListener("auth", String::class.java, { client: SocketIOClient, data: String, ack: AckRequest ->
            run {
                val user = data.split(" ")[0]
                val pass = data.split(" ")[0]


            }
        })
        sslServer.start()
        net.estinet.eAuth.println("Started server on port $port!")
    }
}