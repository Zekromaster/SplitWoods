package net.zekromaster.minecraft.splitwoods.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.item.SlabBlockItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SlabBlockItem.class)
public abstract class SlabMixin {

    @Inject(
        method = "getTranslationKey(Lnet/minecraft/item/ItemStack;)Ljava/lang/String;",
        at = @At("HEAD"),
        cancellable = true)
    public void getTranslationKeyMixin(ItemStack stack, CallbackInfoReturnable<String> cir) {
        if (stack.getDamage() == 2) {
            cir.setReturnValue("tile.splitwoods.oak_slab");
        }

    }

}
