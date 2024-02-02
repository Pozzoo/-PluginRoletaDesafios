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
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.Random;

public class RunChallengeCommand implements CommandExecutor {

    Random random = new Random();
    public static boolean isChoosing;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (RoletaDesafios.getInstance().getChallengeManager().getCurrentChallengeIndex() == -1) {
            RoletaDesafios.getInstance().getChallengeManager().randomChallenge();

            isChoosing = true;

            Title.Times times = Title.Times.times(Duration.ofMillis(0), Duration.ofMillis(3000), Duration.ofMillis(0));
            Component component = StringUtils.formatString("<bold> <gold>" +  RoletaDesafios.getInstance().getChallengeManager().getCurrentChallenge().toUpperCase());
            Title finalTitle = Title.title(component, Component.text(""), times);

            new BukkitRunnable() {
                int index = random.nextInt(RoletaDesafios.getInstance().getChallengeManager().getChallengeList().size());
                int index1 = index - 1;
                int index3 = index + 1;
                int iterations = 0;
                @Override
                public void run() {

                    if (index1 < 0)
                        index1 = RoletaDesafios.getInstance().getChallengeManager().getChallengeList().size() - 1;

                    if (index1 >= RoletaDesafios.getInstance().getChallengeManager().getChallengeList().size())
                        index1 = 0;

                    if (index3 >= RoletaDesafios.getInstance().getChallengeManager().getChallengeList().size())
                        index3 = 0;

                    String text1 = RoletaDesafios.getInstance().getChallengeManager().getChallengeList().get(index1);
                    String text2 = RoletaDesafios.getInstance().getChallengeManager().getChallengeList().get(index).toUpperCase();
                    String text3 = RoletaDesafios.getInstance().getChallengeManager().getChallengeList().get(index3);

                    Component finalText = StringUtils.formatString(text3+ " <bold>" + text2 + "</bold> " + text1);

                    Title.Times times = Title.Times.times(Duration.ofMillis(0), Duration.ofMillis(3000), Duration.ofMillis(0));
                    Title title = Title.title(finalText, Component.text( ""), times);

                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.showTitle(title);
                            player.playSound(player, Sound.BLOCK_COMPARATOR_CLICK, 1, 1);
                        }
                    index++;
                    if (index >= RoletaDesafios.getInstance().getChallengeManager().getChallengeList().size())
                        index = 0;

                    index1++;
                    index3++;

                    if (iterations > 6 && index == RoletaDesafios.getInstance().getChallengeManager().getCurrentChallengeIndex()) {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.showTitle(finalTitle);
                            player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                            isChoosing = false;
                        }
                        this.cancel();
                    }

                    iterations++;

                }
            }.runTaskTimer(RoletaDesafios.getInstance(), 1, 12);
        } else {
            sender.sendMessage("Um desafio já está ocorrendo");
        }

        return true;
    }
}
