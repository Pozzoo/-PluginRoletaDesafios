package com.github.pozzoo.roletadesafios.commands;

import com.github.pozzoo.roletadesafios.RoletaDesafios;
import com.github.pozzoo.roletadesafios.utils.StringUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CancelChallengeCommand implements CommandExecutor {

    final Component mainTitle = StringUtils.formatString("<red> Desafio Cancelado");
    final Title title = Title.title(mainTitle, Component.text(""));
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (RoletaDesafios.getInstance().getChallengeManager().getCurrentChallengeIndex() == -1) {
            sender.sendMessage("Não há nenhum desafio ocorrendo atualmente");
            return true;
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.showTitle(title);
            player.playSound(player, Sound.ENTITY_ITEM_BREAK, 1, 1);
        }

        RoletaDesafios.getInstance().getChallengeManager().clearCurrentChallenge();
        return true;
    }
}
