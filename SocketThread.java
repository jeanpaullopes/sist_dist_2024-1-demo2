import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketThread extends Thread{
    Socket socket;
    BufferedReader in;
    public SocketThread(Socket sckt) {
        this.socket = sckt;
    }
    public void run() {
        try {
            // cria um bufferedReader a partir do fluxo
            // de entrada do socket, mas isto foi substituido
            // pela leitura caracter a carater
            in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
            String entrada = "";
            int ret = 0;
            char lf = 13;
            char cr = 10;
            String fim = ""+lf+cr+lf+cr;
            System.out.println("conexao de "+
            socket.getRemoteSocketAddress().toString());
            // laço de repetição enquanto o socket
            // estiver aberto e o byte lido for maior que 0
            // e não for 255 (sinal de final de frase do sistema)
            while(!socket.isClosed() && ret >= 0 && ret != 255 && 
            !(entrada.contains(fim))){
                // Lê um byte do fluxo de entrada
                ret = socket.getInputStream().read();
                // converte o byte lido em char e concatena
                entrada += (char) ret;
               // System.out.println((int) ret);
            }
            System.out.println(entrada);
            System.out.println("Depois do while");
            //System.out.println(ret+ "  "+entrada);
            // adiciona o nome recebido a lista de socket
            // aqui futuramente deve haver a decisão para
            // saber o que foi demando do servidor (comando)
            SocketsList.getInstance().addSocket(entrada, socket.getRemoteSocketAddress().toString());

            // pega o fluxo de saída do socket
            OutputStream out = socket.getOutputStream();
            System.out.println("vai escrever");
            // escreve no fluxo de saída do socket a lista
            // de sockets registrada
            String json = "{\"sockets\":3}";
            String response = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: application/json\r\n" +
                    "Content-Length:"+json.length()+"\r\n\r\n" +
                    json
+fim;
            out.write(
                    response.getBytes()
            );
            out.flush();
            out.close();
            //socket.close();
            //System.out.println("Fechei socket");
            //SocketsList.getInstance().removeSocket(socket.getInetAddress().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(
                "enviado"
                );
    }
}
