package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.council.CouncilEngine;
import com.narxoz.rpg.council.CouncilRunResult;
import com.narxoz.rpg.guild.*;
import com.narxoz.rpg.quest.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Homework 10 Demo: Iterator + Mediator ===\n");

        // Heroes
        Hero warrior = new Hero("Grom", 150, 25, 12);
        Hero mage = new Hero("Elara", 100, 20, 8);
        List<Hero> party = List.of(warrior, mage);

        System.out.println("--- Heroes ---");
        for (Hero h : party) System.out.println(h.getName() + ": HP=" + h.getHp());

        // Quest log
        QuestLog questLog = new QuestLog();
        questLog.add(new Quest("Clear the Caves", QuestPriority.LOW, 50, false));
        questLog.add(new Quest("Escort Merchant", QuestPriority.NORMAL, 100, false));
        questLog.add(new Quest("Defeat Goblin Chief", QuestPriority.HIGH, 200, true));
        questLog.add(new Quest("Rescue Villagers", QuestPriority.HIGH, 250, true));
        questLog.add(new Quest("Slay Ancient Dragon", QuestPriority.URGENT, 1000, true));
        questLog.add(new Quest("Retrieve Artifact", QuestPriority.NORMAL, 150, false));

        System.out.println("\n--- Quest Log (" + questLog.size() + " quests) ---");

        // Demonstrate RewardSorted iterator
        System.out.println("\n--- Reward Sorted (highest first) ---");
        QuestIterator sorted = new RewardSortedQuestIterator(questLog);
        while (sorted.hasNext()) {
            Quest q = sorted.next();
            System.out.println("  " + q.getTitle() + " - " + q.getRewardGold() + "g");
        }

        // Guild Hall and members
        GuildHall hall = new GuildHall();
        new Quartermaster("Aldric", hall);
        new Scout("Lyra", hall);
        new Healer("Serena", hall);
        new Captain("Marcus", hall);
        new Loremaster("Old Wren", hall);

        System.out.println("\n--- Guild Members Registered ---");

        // Run council
        CouncilEngine engine = new CouncilEngine();
        CouncilRunResult result = engine.runCouncil(party, questLog, hall);

        System.out.println("\n=== COUNCIL RUN RESULT ===");
        System.out.println(result);

        System.out.println("\n=== Demo Complete ===");
    }
}