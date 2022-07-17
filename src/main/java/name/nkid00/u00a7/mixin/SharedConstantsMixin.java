package name.nkid00.u00a7.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import name.nkid00.u00a7.U00A7;

import net.minecraft.SharedConstants;

@Mixin(SharedConstants.class)
public class SharedConstantsMixin {
    @Inject(method = "isValidChar(C)Z", at = @At("HEAD"), cancellable = true)
    private static void isValidChar(char chr, CallbackInfoReturnable<Boolean> info) {
        if (U00A7.options.allowFormattingCodes && chr == '\u00a7') {
            info.setReturnValue(true);
        }
    }
}
