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
        // User-Objekt mappen
        ctx.user.setFirstname(ctx.dto.getFirstname());
        ctx.user.setLastname(ctx.dto.getLastname());

        // Profile-Objekt mappen, falls gegeben
        if (ctx.profile != null) {
            ctx.user.setProfile(ctx.profile);
            if(ctx.dto.getPassword().length() > 0) {
                ctx.profile.setPassword(ctx.dto.getPassword());
            }
            ctx.profile.setMailaddress(ctx.dto.getMailaddress());
        }

        // Address-Objekt mappen, falls gegeben
        if (ctx.address != null) {
            ctx.user.setAddress(ctx.address);
        }
        return ctx;
    }

}
