package bluebird.cleanerdebug.client.mixin;

import bluebird.cleanerdebug.client.ModConfig;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.hud.debug.LookingAtBlockDebugHudEntry;
import net.minecraft.state.property.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Map;
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
    private boolean render$remove_block_tags(Stream<?> stream, Consumer<?> consumer) {
        return ModConfig.INSTANCE.targetedBlockTags;
    }

    @Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/BlockState;getEntries()Ljava/util/Map;"
            )
    )
    private Map<Property<?>, Comparable<?>> render$remove_blockstate(BlockState state) {
        if (!ModConfig.INSTANCE.targetedBlockState) {
            return Map.of();
        }

        return state.getEntries();
    }
}
