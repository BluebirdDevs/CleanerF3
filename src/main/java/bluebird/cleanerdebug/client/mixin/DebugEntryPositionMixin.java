package bluebird.cleanerdebug.client.mixin;

import bluebird.cleanerdebug.client.ModConfig;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.debug.DebugEntryPosition;
import net.minecraft.client.gui.components.debug.DebugScreenDisplayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;

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

        if (ModConfig.INSTANCE.simplified_direction || ModConfig.INSTANCE.simplified_pos) {
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
        String prefix = ModConfig.INSTANCE.pos_name;
        if (!prefix.isEmpty()) prefix = prefix.concat(": ");
        if (ModConfig.INSTANCE.simplified_pos) {
            BlockPos blockPos = Minecraft.getInstance().getCameraEntity().blockPosition();
            return String.format(Locale.ROOT, prefix+"%d %d %d", blockPos.getX(), blockPos.getY(), blockPos.getZ());
        }
        return String.format(locale, prefix+s.substring(5), objects);
    }

    @Redirect(
            method = "display",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/components/debug/DebugScreenDisplayer;addToGroup(Lnet/minecraft/resources/Identifier;Ljava/util/Collection;)V"
            )
    )
    private void redirectAddLines(DebugScreenDisplayer instance, Identifier section, Collection<String> lines) {
        List<String> arr = new ArrayList<>(lines);

        ModConfig setting = ModConfig.INSTANCE;

        if (setting.simplified_pos) {
            arr.removeIf(s -> !(s.contains(ModConfig.INSTANCE.pos_name) || s.contains("Facing")));
        } else {
            if (setting.hide_chunk_position) {
                arr.removeIf(s -> s.contains("Chunk"));
            }
            if (setting.hide_facing) {
                arr.removeIf(s -> s.contains("Facing"));
            }
        }

        instance.addToGroup(section, arr);
    }
}
