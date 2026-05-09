package com.narxoz.rpg.guild;

public class Loremaster extends GuildMember {
    public Loremaster(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void shareLore(String topic, String payload) {
        System.out.println("[Loremaster] " + getName() + " shares: " + payload);
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        System.out.println("[Loremaster] " + getName() + " from " + from.getName() + " [" + topic + "]: " + payload);
        if (topic.equals("lore") || topic.equals("history")) System.out.println("  -> Consulting archives...");
        else if (topic.equals("curse")) System.out.println("  -> Warning! Ancient curse detected!");
    }
}