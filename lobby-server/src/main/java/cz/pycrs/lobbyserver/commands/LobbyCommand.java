package cz.pycrs.lobbyserver.commands;

import build.buf.gen.minekube.gate.v1.ConnectPlayerRequest;
import build.buf.gen.minekube.gate.v1.GateServiceGrpc;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.arguments.number.ArgumentInteger;
import net.minestom.server.entity.Player;

public class LobbyCommand extends Command {
    private final GateServiceGrpc.GateServiceBlockingStub stub;

    public LobbyCommand(GateServiceGrpc.GateServiceBlockingStub stub) {
        super("lobby");
        this.stub = stub;

        setDefaultExecutor(this::onDefault);

        var listArg = ArgumentType.Literal("list");
        addSyntax(this::onList, listArg);

        ArgumentInteger lobbyArg = ArgumentType.Integer("lobby");
        addSyntax(this::onSendSpecific, ArgumentType.Literal("send"), lobbyArg);

    }

    private void onDefault(CommandSender sender, CommandContext ctx) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command.");
            return;
        }

        player.sendMessage("Sending to random lobby");
    }

    private void onList(CommandSender sender, CommandContext ctx) {
        sender.sendMessage("Listing lobbies");
    }

    private void onSendSpecific(CommandSender sender, CommandContext ctx) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command.");
            return;
        }

        player.sendMessage("Sending to lobby " + ctx.get("lobby"));
        var request = ConnectPlayerRequest.newBuilder()
                .setPlayer(player.getUuid().toString())
                .setServer("server" + ctx.get("lobby"))
                        .build();

        var response = stub.connectPlayer(request);
    }

}
