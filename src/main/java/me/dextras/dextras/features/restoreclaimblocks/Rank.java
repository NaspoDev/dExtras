package me.dextras.dextras.features.restoreclaimblocks;

// Rank object class.
public class Rank {

    private String name;
    private int priorityInAscendingOrder;
    private int allocatedClaimblocks;

    Rank(String name, int priorityInAscendingOrder, int allocatedClaimblocks) {
        this.name = name;
        this.priorityInAscendingOrder = priorityInAscendingOrder; // Ex. 2 is higher than 1
        this.allocatedClaimblocks = allocatedClaimblocks;
    }

    // --- Getters ---

    String getName() {
        return name;
    }

    int getPriorityInAscendingOrder() {
        return priorityInAscendingOrder;
    }

    int getAllocatedClaimblocks() {
        return allocatedClaimblocks;
    }
}
