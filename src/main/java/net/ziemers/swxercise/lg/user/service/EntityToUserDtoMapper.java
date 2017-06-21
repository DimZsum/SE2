package net.ziemers.swxercise.lg.user.service;

import net.ziemers.swxercise.lg.user.dto.UserDto;

/**
 * Mapper zur Konvertierung von User-Entities in {@link UserDto}s.
 */
public class EntityToUserDtoMapper {

    /**
     * Erzeugt ein DTO aus dem übergebenem Kontext.
     *
     * @param ctx der Kontext
     * @return das erzeugte DTO.
     */
    public UserDto map(EntityToUserDtoContext ctx) {
        final UserDto dto = new UserDto();

        if (ctx.user != null) {
          mapEntityId(ctx, dto);
          mapFirstname(ctx, dto);
          mapLastname(ctx, dto);
        }
        return dto;
    }

    private void mapEntityId(EntityToUserDtoContext ctx, UserDto dto) {
        dto.setEntityId(ctx.user.getId());
    }

    private void mapFirstname(EntityToUserDtoContext ctx, UserDto dto) {
        dto.setFirstname(ctx.user.getFirstname());
    }

    private void mapLastname(EntityToUserDtoContext ctx, UserDto dto) {
        dto.setLastname(ctx.user.getLastname());
    }

}
