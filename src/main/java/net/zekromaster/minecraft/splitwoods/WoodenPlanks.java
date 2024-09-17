package net.zekromaster.minecraft.splitwoods;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.ShapedRecipe;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipeManager;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Namespace;

import java.util.ArrayList;

import static net.minecraft.block.Block.WOOD_SOUND_GROUP;

public class WoodenPlanks {

    @Entrypoint.Namespace
    private static Namespace namespace;

    public static Block BIRCH_PLANKS;
    public static Block SPRUCE_PLANKS;

    @EventListener
    private static void blockListener(BlockRegistryEvent event) {
        BIRCH_PLANKS = new TemplateBlock(namespace.id("birch_planks"), Material.WOOD).setHardness(2.0F).setResistance(5.0F).setSoundGroup(WOOD_SOUND_GROUP).setTranslationKey(namespace.id("birch_planks"));
        SPRUCE_PLANKS = new TemplateBlock(namespace.id("spruce_planks"), Material.WOOD).setHardness(2.0F).setResistance(5.0F).setSoundGroup(WOOD_SOUND_GROUP).setTranslationKey(namespace.id("spruce_planks"));
    }

    @EventListener
    private static void recipeListener(RecipeRegisterEvent event) {
        if (event.recipeId.equals(RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type())) {
            var recipes = CraftingRecipeManager.getInstance().getRecipes();
            var toRemove = new ArrayList<>();
            for (var recipe: recipes) {
                if (recipe instanceof ShapedRecipe rec) {
                    if (
                        rec.getOutput().getItem().equals(Block.PLANKS.asItem()) &&
                            rec.input.length == 1 &&
                            rec.input[0].getItem().equals(Block.LOG.asItem())
                    ) {
                        toRemove.add(rec);
                    }
                }
            }
            recipes.removeAll(toRemove);

            CraftingRegistry.addShapelessRecipe(new ItemStack(Block.PLANKS, 4), new ItemStack(Block.LOG, 1, 0));
            CraftingRegistry.addShapelessRecipe(new ItemStack(BIRCH_PLANKS, 4), new ItemStack(Block.LOG, 1, 2));
            CraftingRegistry.addShapelessRecipe(new ItemStack(SPRUCE_PLANKS, 4), new ItemStack(Block.LOG, 1, 1));
        }
    }

}
