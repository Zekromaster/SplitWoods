package net.zekromaster.minecraft.splitwoods;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Namespace;
import net.zekromaster.minecraft.splitwoods.templates.SlabBlockTemplate;
import net.zekromaster.minecraft.splitwoods.templates.StairsBlockTemplate;

import static net.minecraft.block.Block.WOOD_SOUND_GROUP;

public class SplitWoods {

    @Entrypoint.Namespace
    private static Namespace namespace;

    public static Block BIRCH_PLANKS;
    public static Block SPRUCE_PLANKS;
    public static Block BIRCH_SLAB;
    public static Block SPRUCE_SLAB;
    public static Block BIRCH_STAIRS;
    public static Block SPRUCE_STAIRS;

    @EventListener
    private static void blockListener(BlockRegistryEvent event) {
        Block.PLANKS.setTranslationKey(namespace.id("oak_planks"));
        BIRCH_PLANKS = new TemplateBlock(namespace.id("birch_planks"), Material.WOOD)
            .setHardness(2.0F)
            .setResistance(5.0F)
            .setSoundGroup(WOOD_SOUND_GROUP)
            .setTranslationKey(namespace.id("birch_planks"));
        SPRUCE_PLANKS = new TemplateBlock(namespace.id("spruce_planks"), Material.WOOD)
            .setHardness(2.0F)
            .setResistance(5.0F)
            .setSoundGroup(WOOD_SOUND_GROUP)
            .setTranslationKey(namespace.id("spruce_planks"));

        BIRCH_SLAB = new SlabBlockTemplate(namespace.id("birch_slab"), BIRCH_PLANKS)
            .setHardness(2.0F)
            .setResistance(5.0F)
            .setSoundGroup(WOOD_SOUND_GROUP)
            .setTranslationKey(namespace.id("birch_slab"));
        SPRUCE_SLAB = new SlabBlockTemplate(namespace.id("spruce_slab"), SPRUCE_PLANKS)
            .setHardness(2.0F)
            .setResistance(5.0F)
            .setSoundGroup(WOOD_SOUND_GROUP)
            .setTranslationKey(namespace.id("spruce_slab"));

        Block.WOODEN_STAIRS.setTranslationKey(namespace.id("oak_stairs"));
        BIRCH_STAIRS = new StairsBlockTemplate(namespace.id("birch_stairs"), BIRCH_PLANKS)
            .setHardness(2.0F)
            .setResistance(5.0F)
            .setSoundGroup(WOOD_SOUND_GROUP)
            .setTranslationKey(namespace.id("birch_slab"));
        SPRUCE_STAIRS = new StairsBlockTemplate(namespace.id("spruce_stairs"), SPRUCE_PLANKS)
            .setHardness(2.0F)
            .setResistance(5.0F)
            .setSoundGroup(WOOD_SOUND_GROUP)
            .setTranslationKey(namespace.id("spruce_slab"));

    }

    @EventListener
    private static void recipeListener(RecipeRegisterEvent event) {

        if (event.recipeId.equals(RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type())) {
            CraftingRegistry.addShapelessRecipe(new ItemStack(Block.PLANKS, 4), new ItemStack(Block.LOG, 1, 0));
            CraftingRegistry.addShapelessRecipe(new ItemStack(BIRCH_PLANKS, 4), new ItemStack(Block.LOG, 1, 2));
            CraftingRegistry.addShapelessRecipe(new ItemStack(SPRUCE_PLANKS, 4), new ItemStack(Block.LOG, 1, 1));
        }
        if (event.recipeId.equals(RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type())) {
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.SLAB, 6, 2), "xxx", 'x', Block.PLANKS);
            CraftingRegistry.addShapedRecipe(new ItemStack(BIRCH_SLAB, 6), "xxx", 'x', BIRCH_PLANKS);
            CraftingRegistry.addShapedRecipe(new ItemStack(SPRUCE_SLAB, 6), "xxx", 'x', SPRUCE_PLANKS);

            CraftingRegistry.addShapedRecipe(new ItemStack(Block.WOODEN_STAIRS, 4), "x  ", "xx ", "xxx", 'x', Block.PLANKS);
            CraftingRegistry.addShapedRecipe(new ItemStack(BIRCH_STAIRS, 4), "x  ", "xx ", "xxx", 'x', BIRCH_PLANKS);
            CraftingRegistry.addShapedRecipe(new ItemStack(SPRUCE_STAIRS, 4), "x  ", "xx ", "xxx", 'x', SPRUCE_PLANKS);

        }


    }

}
