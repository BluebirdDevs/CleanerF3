package bluebird.cleanerdebug.client.mixin;

import bluebird.cleanerdebug.client.ConfigManager;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import net.minecraft.client.gui.components.debug.DebugScreenEntryList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DebugScreenOverlay.class)
public abstract class DebugScreenOverlayMixin {

    @Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/components/debug/DebugScreenEntryList;isF3Visible()Z"
            )
    )
    private boolean conditionallyDisableF3Help(DebugScreenEntryList instance) {
        if (ConfigManager.INSTANCE.hide_debug_hints) {
            return false;
        }
        return instance.isF3Visible();
    }
}
