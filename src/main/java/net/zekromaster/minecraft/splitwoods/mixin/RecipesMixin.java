package net.zekromaster.minecraft.splitwoods.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

// This has to run right before StAPI tagifies recipes
@Mixin(value = CraftingRecipeManager.class, priority =  999)
public class RecipesMixin {

    @WrapWithCondition(
        method = "<init>",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/recipe/CraftingRecipeManager;addShapedRecipe(Lnet/minecraft/item/ItemStack;[Ljava/lang/Object;)V")
    )
    public boolean shouldRegisterRecipe(CraftingRecipeManager instance, ItemStack output, Object[] input) {
        if (output.getItem().equals(Block.SLAB.asItem()) && output.getDamage() == 2) {
            return false;
        }
        if (output.getItem().equals(Block.PLANKS.asItem())) {
            return false;
        }
        return !output.getItem().equals(Block.WOODEN_STAIRS.asItem());
    }


}
