package com.github.pozzoo.roletadesafios.settings;

import com.github.pozzoo.roletadesafios.RoletaDesafios;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ChallengesSettings {

    public void load() {
        File file = new File(RoletaDesafios.getInstance().getDataFolder(), "configDesafios.yml");

        if (!file.exists()) {
            RoletaDesafios.getInstance().saveResource("configDesafios.yml", false);
        }

        YamlConfiguration config = new YamlConfiguration();
        config.options().parseComments(true);

        try {
            config.load(file);
        } catch (InvalidConfigurationException | IOException e) {
            throw new RuntimeException(e);
        }

        for (String string : config.getKeys(true)) {
            RoletaDesafios.getInstance().getChallengeManager().loadChallenges(config.getStringList("Desafios"));
        }
    }
}
