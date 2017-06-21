package net.ziemers.swxercise.lg.user.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import net.ziemers.swxercise.db.dao.GenericDao;
import net.ziemers.swxercise.lg.model.user.User;
import net.ziemers.swxercise.lg.user.dto.UserDto;

@Stateless
public class UserDtoToEntityContextService {

    @Inject
    private GenericDao dao;

    /**
     * Erzeugt den Aktualisierungskontext zur übergebenen Zielentität.
     *
     * @param dto das Benutzer-DTO
     * @return den erzeugten Kontext.
     */
    public UserDtoToEntityContext createContext(final UserDto dto) {
      final UserDtoToEntityContext ctx = new UserDtoToEntityContext();

      ctx.dto = dto;
      ctx.user = dto.getEntityId() == null ? new User() : dao.findById(User.class, dto.getEntityId());

      return ctx;
    }

}
