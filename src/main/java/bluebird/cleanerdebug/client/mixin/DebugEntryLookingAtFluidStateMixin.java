package bluebird.cleanerdebug.client.mixin;

import bluebird.cleanerdebug.client.ConfigManager;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.client.gui.components.debug.DebugEntryLookingAtFluid;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Mixin(DebugEntryLookingAtFluid.class)
public class DebugEntryLookingAtFluidStateMixin {
    @Redirect(
            method = "display",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/material/FluidState;getValues()Ljava/util/Map;"
            )
    )
    private Map<Property<?>, Comparable<?>> cleanerf3$remove_block_state(FluidState instance) {
        if (ConfigManager.INSTANCE.hide_targeted_block_tags) {
            return Map.of();
        }

        return instance.getValues();
    }

    @WrapWithCondition(
            method = "display",
            at = @At(value = "INVOKE", target = "Ljava/util/stream/Stream;forEach(Ljava/util/function/Consumer;)V")
    )
    private boolean cleanerf3$remove_block_tags(Stream<?> instance, Consumer<?> consumer) {
        return !ConfigManager.INSTANCE.hide_targeted_blockstate_tags;
    }
}
