package lab5;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCommand {
    List<String> commandArgs;

    public AbstractCommand() {
        commandArgs = new ArrayList<>();
    }
}
