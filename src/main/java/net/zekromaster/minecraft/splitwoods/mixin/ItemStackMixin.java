package net.zekromaster.minecraft.splitwoods.mixin;

import net.minecraft.block.LogBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Shadow public abstract Item getItem();

    @Shadow public abstract int getDamage();

    @Inject(
        method = "getTranslationKey",
        at = @At("HEAD"),
        cancellable = true)
    public void getTranslationKeyMixin(CallbackInfoReturnable<String> cir) {
        if (this.getItem() instanceof BlockItem b && b.getBlock() instanceof LogBlock l) {
            cir.setReturnValue(
                switch (this.getDamage()) {
                    case 0 -> "tile.splitwoods.oak_log";
                    case 1 -> "tile.splitwoods.spruce_log";
                    case 2 -> "tile.splitwoods.birch_log";
                    default -> l.getTranslationKey();
                }
            );
        }
    }

}
