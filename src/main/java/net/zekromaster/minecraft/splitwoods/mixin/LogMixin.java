package net.zekromaster.minecraft.splitwoods.mixin;

import net.minecraft.block.LogBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.item.StationFlatteningBlockItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public abstract class LogMixin {

    @Unique
    private final static String[] keys = new String[]{
        "tile.splitwoods.oak_log",
        "tile.splitwoods.spruce_log",
        "tile.splitwoods.birch_log"
    };

    @Inject(
        method = "getTranslationKey(Lnet/minecraft/item/ItemStack;)Ljava/lang/String;",
        at = @At("HEAD"),
        cancellable = true)
    public void getTranslationKeyMixin(ItemStack stack, CallbackInfoReturnable<String> cir) {
        var block = ((StationFlatteningBlockItem) this).getBlock();
        if (block instanceof LogBlock && stack.getDamage() < keys.length) {
            cir.setReturnValue(keys[stack.getDamage()]);
        }
    }

}
