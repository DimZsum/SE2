package net.ziemers.swxercise.lg.user.service;

import javax.ejb.Stateless;

@Stateless
public class UserDtoToEntityMapper {

    /**
     * Überträgt die Eigenschaften aus dem UserDto in die Zielentität.
     *
     * @param ctx der Kontext mit den Eigenschaften und der Zielentität
     * @return den Kontext.
     */
    public UserDtoToEntityContext map(UserDtoToEntityContext ctx) {
      ctx.user.setFirstname(ctx.dto.getFirstname());
      ctx.user.setLastname(ctx.dto.getLastname());
      //ctx.user.setProfile(ctx.dto.getProfile());
      //ctx.user.setAddress(ctx.fto.getAddress());

      return ctx;
    }

}
