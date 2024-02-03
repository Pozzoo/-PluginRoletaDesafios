package com.github.pozzoo.roletadesafios;

import com.github.pozzoo.roletadesafios.utils.StringUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class ChallengeManager {
    private List<String> challengeList;
    private String currentChallenge = null;
    private int currentChallengeIndex = -1;
    private final Random random;
    private boolean enableActionBar = false;

    public ChallengeManager() {
        random = new Random();
        actionBar();
    }

    public void loadChallenges(List<String> strings) {
       this.challengeList = strings;
    }

    public void randomChallenge() {
        int index = random.nextInt(challengeList.size());
        currentChallenge = challengeList.get(index);
        currentChallengeIndex = index;
    }

    public String getCurrentChallenge() {
        return currentChallenge;
    }

    public int getCurrentChallengeIndex() {
        return currentChallengeIndex;
    }

    public void clearCurrentChallenge() {
        currentChallenge = null;
        currentChallengeIndex = -1;
        enableActionBar = false;
    }

    public void runChallengePicker() {
        randomChallenge();
        enableActionBar = false;

        Title.Times times = Title.Times.times(Duration.ofMillis(0), Duration.ofMillis(3000), Duration.ofMillis(0));
        Component component = StringUtils.formatString("<bold> <gold>" +  currentChallenge.toUpperCase());
        Title finalTitle = Title.title(component, Component.text(""), times);

        new BukkitRunnable() {
            int index = random.nextInt(challengeList.size());
            int index1 = index - 1;
            int index3 = index + 1;
            int iterations = 0;
            @Override
            public void run() {

                if (index1 < 0)
                    index1 = challengeList.size() - 1;

                if (index1 >= challengeList.size())
                    index1 = 0;

                if (index3 >= challengeList.size())
                    index3 = 0;

                String text1 = challengeList.get(index1);
                String text2 = challengeList.get(index).toUpperCase();
                String text3 = challengeList.get(index3);

                Component finalText = StringUtils.formatString(text3+ " <bold>" + text2 + "</bold> " + text1);

                Title title = Title.title(finalText, Component.text( ""), times);

                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.showTitle(title);
                    player.playSound(player, Sound.BLOCK_COMPARATOR_CLICK, 1, 1);
                }
                index++;
                if (index >= challengeList.size())
                    index = 0;

                index1++;
                index3++;

                if (iterations > 6 && index == currentChallengeIndex) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.showTitle(finalTitle);
                        player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                    }
                    enableActionBar = true;
                    this.cancel();
                }

                iterations++;

            }
        }.runTaskTimer(RoletaDesafios.getInstance(), 1, 12);
    }

    public void actionBar() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (enableActionBar) {
                    Component component = StringUtils.formatString("<bold>" + RoletaDesafios.getInstance().getChallengeManager().getCurrentChallenge().toUpperCase());
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendActionBar(component);
                    }
                }
            }

        }.runTaskTimer(RoletaDesafios.getInstance(), 0, 20);
    }
}
