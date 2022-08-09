package name.nkid00.u00a7.mixin;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import name.nkid00.u00a7.U00A7;

import net.minecraft.network.packet.c2s.play.UpdateSignC2SPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.filter.FilteredMessage;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
    @Final
    @Shadow
    private MinecraftServer server;
    @Shadow
    public ServerPlayerEntity player;

    @SuppressWarnings("rawtypes")
    @Inject(method = "onUpdateSign(Lnet/minecraft/network/packet/c2s/play/UpdateSignC2SPacket;)V", at = @At("HEAD"), cancellable = true)
    private void onUpdateSign(UpdateSignC2SPacket packet, CallbackInfo info) {
        if (U00A7.options.allowFormattingCodes && U00A7.options.allowFormattingCodesOnSigns) {
            List<String> list = List.of(packet.getText());
            filterTexts(list).thenAcceptAsync(texts -> this.onSignUpdate(packet, (List<FilteredMessage>) texts),
                    (Executor) this.server);
            info.cancel();
        }
    }

    @SuppressWarnings("rawtypes")
    @Shadow
    private CompletableFuture<List<FilteredMessage>> filterTexts(List<String> texts) {
        throw new AssertionError();
    }

    @SuppressWarnings("rawtypes")
    @Shadow
    private void onSignUpdate(UpdateSignC2SPacket packet, List<FilteredMessage> signText) {
        throw new AssertionError();
    }
}
