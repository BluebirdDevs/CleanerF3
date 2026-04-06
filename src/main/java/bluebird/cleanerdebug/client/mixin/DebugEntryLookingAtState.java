package bluebird.cleanerdebug.client.mixin;

import bluebird.cleanerdebug.client.ConfigManager;
import net.minecraft.client.gui.components.debug.DebugEntryLookingAtBlock;
import net.minecraft.client.gui.components.debug.DebugEntryLookingAtFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;


@Mixin({
        DebugEntryLookingAtBlock.class,
        DebugEntryLookingAtFluid.class
})
public class DebugEntryLookingAtState {
    @ModifyArg(method = "display", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;pick(DFZ)Lnet/minecraft/world/phys/HitResult;"))
    public double cleanerf3$change_hit_range(double range) {
        return ConfigManager.INSTANCE.targeted_block_max_distance;
    }
}
