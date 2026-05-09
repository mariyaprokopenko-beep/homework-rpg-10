package com.narxoz.rpg.council;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.guild.GuildHall;
import com.narxoz.rpg.guild.GuildMediator;
import com.narxoz.rpg.quest.*;
import java.util.List;

public class CouncilEngine {

    public CouncilRunResult runCouncil(List<Hero> party, QuestLog questLog, GuildMediator hall) {
        int questsTraversed = 0;
        int messagesRouted = 0;
        int membersNotified = 0;

        System.out.println("\n=== WAR COUNCIL ===\n");

        // Iterator 1: Ordered
        System.out.println("--- Ordered traversal ---");
        QuestIterator ordered = questLog.ordered();
        while (ordered.hasNext()) {
            Quest q = ordered.next();
            questsTraversed++;
            System.out.println("  " + q.getTitle() + " (" + q.getPriority() + ", " + q.getRewardGold() + "g)");
            hall.dispatch("orders", null, "Review: " + q.getTitle());
            messagesRouted++;
        }

        // Iterator 2: High priority
        System.out.println("\n--- High priority (HIGH+) ---");
        QuestIterator high = questLog.priorityAtLeast(QuestPriority.HIGH);
        while (high.hasNext()) {
            Quest q = high.next();
            System.out.println("  URGENT: " + q.getTitle());
            hall.dispatch("urgent", null, "URGENT: " + q.getTitle());
            messagesRouted++;
        }

        // Mediator messages
        System.out.println("\n--- Coordination ---");
        if (hall instanceof GuildHall) {
            membersNotified = ((GuildHall) hall).getMemberCount();
        }

        hall.dispatch("scouting", null, "Need intel on monster movement");
        messagesRouted++;
        hall.dispatch("supplies", null, "Requesting additional potions");
        messagesRouted++;
        hall.dispatch("healing", null, "Medical team prepare");
        messagesRouted++;
        hall.dispatch("rewards", null, "Commission mission rewards");
        messagesRouted++;

        return new CouncilRunResult(questsTraversed, messagesRouted, membersNotified);
    }
}