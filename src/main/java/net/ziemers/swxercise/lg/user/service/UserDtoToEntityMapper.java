package net.ziemers.swxercise.lg.user.service;

import net.ziemers.swxercise.lg.user.dto.UserDto;

import javax.ejb.Stateless;

/**
 * Mapper zur Konvertierung des Kontexts eines {@link UserDto}s in eine User-Entity.
 */
@Stateless
public class UserDtoToEntityMapper {

    /**
     * Überträgt die Eigenschaften aus dem UserDto sowie dem zusätzlichen Kontext in die Zielentität.
     *
     * @param ctx der Kontext mit den Eigenschaften und der Zielentität
     * @return den Kontext.
     */
    public UserDtoToEntityContext map(UserDtoToEntityContext ctx) {
        ctx.user.setFirstname(ctx.dto.getFirstname());
        ctx.user.setLastname(ctx.dto.getLastname());
        if (ctx.profile != null) {
            ctx.user.setProfile(ctx.profile);
        }
        if (ctx.address != null) {
            ctx.user.setAddress(ctx.address);
        }
        return ctx;
    }

}
