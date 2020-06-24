import com.tobiassteely.tobiasapi.TobiasAPI;
        import com.tobiassteely.tobiasapi.TobiasBuilder;
        import com.tobiassteely.tobiasapi.command.Command;
        import com.tobiassteely.tobiasapi.command.CommandExecutor;
import com.tobiassteely.tobiasapi.command.response.CommandResponse;

import java.util.Collections;

public class Test {

    public static void main(String[] args) {
        TobiasBuilder builder = new TobiasBuilder().loadConfigManager("").loadCommandManager("Testing System", true);
        TobiasAPI tobiasAPI = builder.build();

        CommandExecutor executor = (name, arguments, data) -> {
            if(arguments.length > 0) {
                if(arguments[0].equalsIgnoreCase("a")) {
                    return new CommandResponse(data).setDescription("Sub command");
                }
            }

            return new CommandResponse(data).setDescription("Basic Command");
        };

        //String module, String name, String[] activators, String usage, String description, int staffLevel, CommandExecutor executor
        Command command = new Command("TestModule", "TestCommand", new String[] {"t"}, "test a", "test command", Collections.singletonList(executor));

        tobiasAPI.getCommandManager().registerCommand(command);
    }

}
