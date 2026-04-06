package bluebird.cleanerdebug.client.mixin;

import bluebird.cleanerdebug.client.ConfigManager;
import bluebird.cleanerdebug.client.ConfigValues;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.debug.DebugEntryPosition;
import net.minecraft.client.gui.components.debug.DebugScreenDisplayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;

import java.util.*;

@Mixin(DebugEntryPosition.class)
public class DebugEntryPositionMixin {

    @ModifyArgs(
            method = "display",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/String;format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;",
                    ordinal = 3
            )
    )
    private void simpleDirection(Args args) {
        Minecraft client = Minecraft.getInstance();
        Entity entity = client.getCameraEntity();

        Object[] varargs = args.get(2);
        String newDir = switch (entity.getDirection()) {
            case NORTH -> "-Z";
            case SOUTH -> "+Z";
            case WEST  -> "-X";
            case EAST  -> "+X";
            default    -> (String) varargs[1];
        };

        if (ConfigManager.INSTANCE.pos.simplified_direction) {
            varargs[1] = newDir;
            args.set(2, varargs);
        }
    }

    @Redirect(
            method = "display",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/String;format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;",
                    ordinal = 0
            )
    )
    private String simplePos(Locale locale, String s, Object[] objects) {
        String prefix = ConfigManager.INSTANCE.pos.pos_name;
        if (!prefix.isEmpty()) prefix = prefix.concat(": ");
        if (ConfigManager.INSTANCE.pos.simplified_pos) {
            Entity camera = Minecraft.getInstance().getCameraEntity();
            BlockPos blockPos = camera.blockPosition();
            return String.format(Locale.ROOT, prefix+"%d %d %d", blockPos.getX(), blockPos.getY(), blockPos.getZ());
        }
        return String.format(locale, prefix+s.substring(5), objects);
    }

    @Redirect(
            method = "display",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/components/debug/DebugScreenDisplayer;addToGroup(Lnet/minecraft/resources/ResourceLocation;Ljava/util/Collection;)V"
            )
    )
    private void redirectAddLines(DebugScreenDisplayer instance, ResourceLocation resourceLocation, Collection<String> strings) {
        List<String> arr = new ArrayList<>(strings);

        ConfigValues setting = ConfigManager.INSTANCE;

        if (setting.pos.hide_chunk_position) {
            arr.removeIf(s -> s.contains("Chunk"));
        }
        if (setting.pos.hide_facing) {
            arr.removeIf(s -> s.contains("Facing"));
        }
        if (setting.pos.simplified_pos) {
            arr.removeIf(s -> s.contains("Block"));
        }
        if (setting.pos.hide_dimension) {
            arr.removeIf(s -> s.contains("FC"));
        }

        instance.addToGroup(resourceLocation, arr);
    }
}
