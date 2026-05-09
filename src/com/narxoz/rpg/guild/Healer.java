package com.narxoz.rpg.guild;

public class Healer extends GuildMember {
    public Healer(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void prepareAid(String topic, String payload) {
        System.out.println("[Healer] " + getName() + " prepares: " + payload);
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        System.out.println("[Healer] " + getName() + " from " + from.getName() + " [" + topic + "]: " + payload);
        if (topic.equals("healing")) System.out.println("  -> Stocking potions!");
        else if (topic.equals("urgent")) System.out.println("  -> Emergency team ready!");
    }
}