package com.github.pozzoo.roletadesafios;

import com.github.pozzoo.roletadesafios.commands.CancelChallengeCommand;
import com.github.pozzoo.roletadesafios.commands.FinishChallengeCommand;
import com.github.pozzoo.roletadesafios.commands.RunChallengeCommand;
import com.github.pozzoo.roletadesafios.settings.ChallengesSettings;
import org.bukkit.plugin.java.JavaPlugin;

public final class RoletaDesafios extends JavaPlugin {
    private ChallengeManager challengeManager;
    private static RoletaDesafios instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        this.challengeManager = new ChallengeManager();
        new ChallengesSettings().load();
        getCommand("roletar").setExecutor(new RunChallengeCommand());
        getCommand("concluir").setExecutor(new FinishChallengeCommand());
        getCommand("cancelar").setExecutor(new CancelChallengeCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static RoletaDesafios getInstance() {
        return instance;
    }

    public ChallengeManager getChallengeManager() {
        return this.challengeManager;
    }
}
