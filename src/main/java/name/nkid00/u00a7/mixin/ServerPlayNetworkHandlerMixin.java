package name.nkid00.u00a7.mixin;

import java.util.List;
import java.util.function.Consumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import name.nkid00.u00a7.U00A7;

import net.minecraft.network.packet.c2s.play.UpdateSignC2SPacket;
import net.minecraft.server.filter.FilteredMessage;
import net.minecraft.server.network.ServerPlayNetworkHandler;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
    @Inject(method = "onUpdateSign(Lnet/minecraft/network/packet/c2s/play/UpdateSignC2SPacket;)V", at = @At("HEAD"), cancellable = true)
    private void onUpdateSign(UpdateSignC2SPacket packet, CallbackInfo info) {
        if (U00A7.options.allowFormattingCodes) {
            filterTexts(List.of(packet.getText()), texts -> this.onSignUpdate(packet, (List<FilteredMessage<String>>)texts));
            info.cancel();
        }
    }

    @Shadow
    private void filterTexts(List<String> texts, Consumer<List<FilteredMessage<String>>> consumer) {
        throw new AssertionError();
    }

    @Shadow
    private void onSignUpdate(UpdateSignC2SPacket packet, List<FilteredMessage<String>> signText) {
        throw new AssertionError();
    }
}
