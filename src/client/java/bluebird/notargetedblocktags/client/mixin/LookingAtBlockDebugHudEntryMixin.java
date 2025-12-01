package bluebird.notargetedblocktags.client.mixin;

import bluebird.notargetedblocktags.client.ModConfig;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.client.gui.hud.debug.LookingAtBlockDebugHudEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.Consumer;
import java.util.stream.Stream;

@Mixin(LookingAtBlockDebugHudEntry.class)
public abstract class LookingAtBlockDebugHudEntryMixin {
    @WrapWithCondition(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/stream/Stream;forEach(Ljava/util/function/Consumer;)V"
            )
    )
    private boolean conditionallyRunForEach(Stream<?> stream, Consumer<?> consumer) {
        return !ModConfig.INSTANCE.enabled;
    }
}
