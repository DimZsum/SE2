package net.ziemers.swxercise.lg.user.service;

import javax.ejb.Stateless;

/**
 * Service zum Erzeugen eines Mapping-Kontexts. Das Mapping verläuft von {@link User} nach {@link UserDto}.
 */
@Stateless
public class EntityToUserDtoContextService {

    /**
     * Erzeugt einen Mapping-Kontext für einen neuen Benutzer.
     *
     * @return den Mapping-Kontext.
     */
    public EntityToUserDtoContext createContext() {
        final EntityToUserDtoContext ctx = new EntityToUserDtoContext();
        return ctx;
    }

}
