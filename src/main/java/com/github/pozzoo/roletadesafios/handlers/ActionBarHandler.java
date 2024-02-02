package com.github.pozzoo.roletadesafios.handlers;

import com.github.pozzoo.roletadesafios.commands.RunChallengeCommand;
import com.github.pozzoo.roletadesafios.RoletaDesafios;
import com.github.pozzoo.roletadesafios.utils.StringUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ActionBarHandler {
    public void onRun() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!(RoletaDesafios.getInstance().getChallengeManager().getCurrentChallengeIndex() == -1) && !RunChallengeCommand.isChoosing) {
                    Component component = StringUtils.formatString("<bold>" + RoletaDesafios.getInstance().getChallengeManager().getCurrentChallenge().toUpperCase());
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendActionBar(component);
                    }
                }
            }

        }.runTaskTimer(RoletaDesafios.getInstance(), 0, 20);
    }
}
