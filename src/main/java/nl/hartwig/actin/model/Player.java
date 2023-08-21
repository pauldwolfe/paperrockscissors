package nl.hartwig.actin.model;

import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.hartwig.actin.enums.PrsMove;
import nl.hartwig.actin.exception.InvalidUsernameException;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Player {

    protected String name;
    protected PrsMove lastMove;

    public abstract PrsMove getMove();

    public abstract PrsMove getLastMove();

    protected void setName(final String name) {
        if (StringUtils.isBlank(name)) {
            throw new InvalidUsernameException("player's name cannot be blank");
        }
        this.name = name;
    }

    protected boolean isExist(final String input) {
        return StringUtils.isNotBlank(input)
                && (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("q"));
    }
}
