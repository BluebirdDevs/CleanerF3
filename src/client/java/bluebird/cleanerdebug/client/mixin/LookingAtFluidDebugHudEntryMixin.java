package bluebird.cleanerdebug.client.mixin;

import bluebird.cleanerdebug.client.ModConfig;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.client.gui.hud.debug.LookingAtFluidDebugHudEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.Consumer;
import java.util.stream.Stream;

@Mixin(LookingAtFluidDebugHudEntry.class)
public class LookingAtFluidDebugHudEntryMixin {
    @WrapWithCondition(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/stream/Stream;forEach(Ljava/util/function/Consumer;)V"
            )
    )
    private boolean render$remove_block_tags(Stream<?> stream, Consumer<?> consumer) {
        return ModConfig.INSTANCE.targetedBlockTags;
    }
}
