package bluebird.cleanerdebug.client.mixin;

import bluebird.cleanerdebug.client.ModConfig;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import net.minecraft.client.gui.components.debug.DebugScreenEntryList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DebugScreenOverlay.class)
public abstract class DebugScreenOverlayMixin {

    @Redirect(
            method = "extractRenderState",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/components/debug/DebugScreenEntryList;isOverlayVisible()Z"
            )
    )
    private boolean conditionallyDisableF3Help(DebugScreenEntryList instance) {
        if (!ModConfig.INSTANCE.debugHints) {
            return false;
        }
        return instance.isOverlayVisible();
    }
}
