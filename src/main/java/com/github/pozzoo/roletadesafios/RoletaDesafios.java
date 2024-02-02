package com.github.pozzoo.roletadesafios;

import com.github.pozzoo.roletadesafios.commands.CancelChallengeCommand;
import com.github.pozzoo.roletadesafios.commands.FinishChallengeCommand;
import com.github.pozzoo.roletadesafios.commands.RunChallengeCommand;
import com.github.pozzoo.roletadesafios.handlers.ActionBarHandler;
import com.github.pozzoo.roletadesafios.settings.ChallengesSettings;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class RoletaDesafios extends JavaPlugin {
    private ChallengeManager challengeManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.challengeManager = new ChallengeManager();
        new ChallengesSettings().load();
        Objects.requireNonNull(getCommand("roletar")).setExecutor(new RunChallengeCommand());
        Objects.requireNonNull(getCommand("concluir")).setExecutor(new FinishChallengeCommand());
        Objects.requireNonNull(getCommand("cancelar")).setExecutor(new CancelChallengeCommand());
        new ActionBarHandler().onRun();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static RoletaDesafios getInstance() {
        return getPlugin(RoletaDesafios.class);
    }

    public ChallengeManager getChallengeManager() {
        return this.challengeManager;
    }
}
