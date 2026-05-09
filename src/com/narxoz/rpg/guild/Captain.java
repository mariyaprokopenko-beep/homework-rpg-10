package com.narxoz.rpg.guild;

public class Captain extends GuildMember {
    public Captain(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void issueOrder(String topic, String payload) {
        System.out.println("[Captain] " + getName() + " orders: " + payload);
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        System.out.println("[Captain] " + getName() + " from " + from.getName() + " [" + topic + "]: " + payload);
        if (topic.equals("scouting")) System.out.println("  -> Reviewing intel...");
        else if (topic.equals("healing")) System.out.println("  -> Acknowledging...");
        else if (topic.equals("supplies")) System.out.println("  -> Checking inventory...");
    }
}