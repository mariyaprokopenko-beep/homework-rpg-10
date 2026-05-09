package com.narxoz.rpg.guild;

public class Quartermaster extends GuildMember {
    public Quartermaster(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void requestSupplies(String topic, String payload) {
        System.out.println("[Quartermaster] " + getName() + " requests: " + payload);
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        System.out.println("[Quartermaster] " + getName() + " from " + from.getName() + " [" + topic + "]: " + payload);
        if (topic.equals("supplies")) System.out.println("  -> Preparing supplies!");
        else if (topic.equals("rewards")) System.out.println("  -> Calculating rewards...");
    }
}
