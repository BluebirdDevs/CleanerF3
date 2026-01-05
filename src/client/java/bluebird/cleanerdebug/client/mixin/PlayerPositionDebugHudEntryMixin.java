package bluebird.cleanerdebug.client.mixin;

import bluebird.cleanerdebug.client.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.debug.DebugHudLines;
import net.minecraft.client.gui.hud.debug.PlayerPositionDebugHudEntry;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.*;

@Mixin(PlayerPositionDebugHudEntry.class)
public class PlayerPositionDebugHudEntryMixin {

    @ModifyArgs(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/String;format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;",
                    ordinal = 3
            )
    )
    private void simpleDirection(Args args) {
        MinecraftClient client = MinecraftClient.getInstance();
        Entity entity = client.getCameraEntity();

        Object[] varargs = args.get(2);
        String newDir = switch (entity.getHorizontalFacing()) {
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
            method = "render",
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
            BlockPos blockPos = MinecraftClient.getInstance().getCameraEntity().getBlockPos();
            return String.format(Locale.ROOT, prefix+"%d %d %d", blockPos.getX(), blockPos.getY(), blockPos.getZ());
        }
        return String.format(locale, prefix+s.substring(5), objects);
    }

    @Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/hud/debug/DebugHudLines;addLinesToSection(Lnet/minecraft/util/Identifier;Ljava/util/Collection;)V"
            )
    )
    private void redirectAddLines(DebugHudLines instance, Identifier section, Collection<String> lines) {
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

        instance.addLinesToSection(section, arr);
    }
}
