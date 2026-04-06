package bluebird.cleanerdebug.client.mixin;

import bluebird.cleanerdebug.client.ConfigManager;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.components.debug.DebugEntryLookingAt;
import net.minecraft.world.level.block.state.StateHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(DebugEntryLookingAt.DebugEntryLookingAtState.class)
public class DebugEntryLookingAtState {
    @WrapOperation(
            method = "extractInfo",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/debug/DebugEntryLookingAt$DebugEntryLookingAtState;addStateProperties(Ljava/util/List;Lnet/minecraft/world/level/block/state/StateHolder;)V")
    )
    private void cleanerf3$remove_block_tags(List<String> result, StateHolder<?, ?> stateHolder, Operation<Void> original) {
        if (!ConfigManager.INSTANCE.hide_targeted_block_tags) {
            original.call(result, stateHolder);
        }
    }
}
