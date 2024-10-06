// Based on code from DanyGames2014's Tropicraft, see COPYING.Tropicraft

package net.zekromaster.minecraft.splitwoods.templates;

import net.modificationstation.stationapi.api.util.StringIdentifiable;

public enum SlabType implements StringIdentifiable {
    TOP("top"),
    BOTTOM("bottom"),
    DOUBLE("double");

    final String slabType;

    SlabType(String slabType) {
        this.slabType = slabType;
    }

    @Override
    public String asString() {
        return this.slabType;
    }
}