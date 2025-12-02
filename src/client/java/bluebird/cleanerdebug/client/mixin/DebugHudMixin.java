package bluebird.cleanerdebug.client.mixin;

import bluebird.cleanerdebug.client.ModConfig;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.client.gui.hud.debug.DebugHudProfile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DebugHud.class)
public abstract class DebugHudMixin {

    @Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/hud/debug/DebugHudProfile;isF3Enabled()Z"
            )
    )
    private boolean conditionallyDisableF3Help(DebugHudProfile instance) {
        if (!ModConfig.INSTANCE.debugHints) {
            return false;
        }
        return instance.isF3Enabled();
    }
}
