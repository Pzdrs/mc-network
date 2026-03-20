package cz.pycrs.lobbyserver.commands;

import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.arguments.number.ArgumentInteger;

public class LobbyCommand extends Command {
    public LobbyCommand() {
        super("lobby");

        setDefaultExecutor(this::onDefault);

        var listArg = ArgumentType.Literal("list");
        addSyntax(this::onList, listArg);

        ArgumentInteger lobbyArg = ArgumentType.Integer("lobby");
        addSyntax(this::onSendSpecific, ArgumentType.Literal("send"), lobbyArg);

    }

    private void onDefault(CommandSender sender, CommandContext ctx) {
        sender.sendMessage("Sending to random lobby");
    }

    private void onList(CommandSender sender, CommandContext ctx) {
        sender.sendMessage("Listing lobbies");
    }

    private void onSendSpecific(CommandSender sender, CommandContext ctx) {
        sender.sendMessage("Sending to lobby " + ctx.get("lobby"));
    }

}
