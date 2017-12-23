package net.ziemers.swxercise.ui.ws;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Stub für die WebSocket-Unterstützung.
 */
@ApplicationScoped
@ServerEndpoint(WebSocketController.serverEndpointPath)
public class WebSocketController {

    static final String serverEndpointPath = "/ws/api/v1/anEndpoint";

    /**
     * Callback-Methode für das Öffnen einer neuen WebSocket-Verbindung.
     *
     * @param session das {@link Session}-Objekt der neuen WebSocket-Verbindung
     */
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("WebSocket opened with session id #" + session.getId());
    }

    /**
     * Callback-Methode, die den Empfang einer neuen WebSocket-Nachricht signalisiert.
     *
     * @param message der Inhalt der WebSocket-Nachricht
     * @param session das {@link Session}-Objekt der sendenden WebSocket-Verbindung
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("WebSocket Message '" + message + "' received by session id #" + session.getId());
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Callback-Methode für das Schließen einer geöffneten WebSocket-Verbindung.
     *
     * @param reason die Ursache für das Schließen der WebSocket-Verbindung
     * @param session das {@link Session}-Objekt der geschlossenen WebSocket-Verbindung
     */
    @OnClose
    public void onClose(CloseReason reason, Session session) {
        System.out.println("Closing WebSocket due to " + reason.getReasonPhrase() + " by id #" + session.getId());
    }

}
