package net.ziemers.swxercise.ui.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Stub für die WebSocket-Unterstützung.
 *
 * Muss bei Bedarf noch um ein JSON-Marshalling erweitert werden.
 *
 * Aufgepasst: Mittels CDI kann nur @ApplicationScoped injiziert werden,
 * da während eines WebSocket-Callbacks kein Session-Kontext aktiv ist.
 */
@ServerEndpoint(WebSocketController.serverEndpointPath)
public class WebSocketController {

    static final String serverEndpointPath = "/ws/api/v1/anEndpoint/{aParameter}";

    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<>());

    private Logger logger;

    @PostConstruct
    private void init() {
        logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    }

    /**
     * Callback-Methode für das Öffnen einer neuen WebSocket-Verbindung.
     *
     * @param session das {@link Session}-Objekt der neuen WebSocket-Verbindung
     */
    @OnOpen
    public void onOpen(Session session) {
        logger.info("WebSocket opened with session id #{}", session.getId());
        peers.add(session);
    }

    /**
     * Callback-Methode, die den Empfang einer neuen WebSocket-Nachricht signalisiert.
     *
     * @param message der Inhalt der WebSocket-Nachricht
     * @param session das {@link Session}-Objekt der sendenden WebSocket-Verbindung
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("aParameter") String param) {
        logger.info("WebSocket Message '{}/{}' received by session id #{}", message, param, session.getId());
        try {
            // wir senden die empfangene Nachricht gleich wieder zurück
            session.getBasicRemote().sendText(String.format("%s/%s", message, param));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Callback-Methode, wenn in der WebSocket ein Problem auftrat.
     *
     * @param t die Exception
     */
    @OnError
    public void onError(Throwable t) {
        logger.error("WebSocket Error '{}' occured!", t.getMessage());
    }

    /**
     * Callback-Methode für das Schließen einer geöffneten WebSocket-Verbindung.
     *
     * @param reason die Ursache für das Schließen der WebSocket-Verbindung
     * @param session das {@link Session}-Objekt der geschlossenen WebSocket-Verbindung
     */
    @OnClose
    public void onClose(CloseReason reason, Session session) {
        logger.info("Closing WebSocket due to '{}' by session id #{}", reason.getReasonPhrase(), session.getId());
        peers.remove(session);
    }

}
