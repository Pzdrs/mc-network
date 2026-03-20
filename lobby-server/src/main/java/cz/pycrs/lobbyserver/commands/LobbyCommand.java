package cz.pycrs.lobbyserver.commands;

import net.minestom.server.command.builder.Command;

public class LobbyCommand extends Command {
    public LobbyCommand() {
        super("lobby");

        setDefaultExecutor((sender, ctx) -> {
            sender.sendMessage("Lobby command!");
        });

    }
}
