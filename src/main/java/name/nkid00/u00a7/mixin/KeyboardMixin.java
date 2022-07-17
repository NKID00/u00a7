package name.nkid00.u00a7.mixin;

import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import name.nkid00.u00a7.U00A7;
import net.minecraft.client.Keyboard;

@Mixin(Keyboard.class)
public class KeyboardMixin {
    @Inject(method = "onKey(JIIII)V", at = @At("HEAD"), cancellable = true)
    private void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo info) {
        // U00A7.info("onKey: {}, {}, {}, {}", key, scancode, action, modifiers);
        if (U00A7.options.enableHotKey
                && key == GLFW.GLFW_KEY_7
                /* `Ctrl+7` or `Ctrl+Shift+7` (`Ctrl+&`) */
                && (modifiers & ~GLFW.GLFW_MOD_SHIFT) == GLFW.GLFW_MOD_CONTROL
                && (action == 1 || action == 2) /* press or repeat */ ) {
            // U00A7.info("Hot Key triggered");
            onChar(window, (int) '\u00a7', 0);
            info.cancel();
        }
    }

    @Shadow
    public void onChar(long window, int codePoint, int modifiers) {
        throw new AssertionError();
    }

    @Inject(method = "onChar(JII)V", at = @At("HEAD"), cancellable = true)
    private void onChar(long window, int codePoint, int modifiers, CallbackInfo info) {
        // U00A7.info("onChar: {}, {}", codePoint, modifiers);
        if (U00A7.options.enableHotKey
                && codePoint == (int) '&'
                && (modifiers & ~GLFW.GLFW_MOD_SHIFT) == GLFW.GLFW_MOD_CONTROL) {
            // omit `&` when pressing `Ctrl+&`
            // U00A7.info("Hot Key triggered");
            info.cancel();
        }
    }
}
