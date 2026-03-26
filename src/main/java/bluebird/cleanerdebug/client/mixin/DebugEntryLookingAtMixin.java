package bluebird.cleanerdebug.client.mixin;

import bluebird.cleanerdebug.client.ModConfig;
import net.minecraft.client.gui.components.debug.DebugEntryLookingAt;
import net.minecraft.core.TypedInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(DebugEntryLookingAt.DebugEntryLookingAtTags.class)
public class DebugEntryLookingAtMixin {
    @Redirect(method = "extractInfo", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/debug/DebugEntryLookingAt$DebugEntryLookingAtTags;addTagEntries(Ljava/util/List;Lnet/minecraft/core/TypedInstance;)V"))
    private void render$remove_blockstate(List list, TypedInstance typedInstance) {
        if (ModConfig.INSTANCE.targetedBlockState) {
            DebugEntryLookingAt.addTagEntries(list, typedInstance);
        }
    }
}
