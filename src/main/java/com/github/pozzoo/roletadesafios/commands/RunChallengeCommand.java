package com.github.pozzoo.roletadesafios.commands;

import com.github.pozzoo.roletadesafios.RoletaDesafios;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class RunChallengeCommand implements CommandExecutor {

    Random random = new Random();
    public static boolean isChoosing;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (RoletaDesafios.getInstance().getChallengeManager().getCurrentChallengeIndex() != -1) {
            sender.sendMessage("Um desafio já está ocorrendo");
            return true;
        }
        RoletaDesafios.getInstance().getChallengeManager().runChallengePicker();
        return true;
    }
}
