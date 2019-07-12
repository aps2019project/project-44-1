package server;

import models.Account;
import models.Battle;
import models.Enums.BattleKind;
import models.Enums.BattleMode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class OpponentFinder extends Thread {
    private static final Queue<Account> waitingAccountsForDeathMatch = new LinkedList<>();
    private static final Queue<Account> waitingAccountsForSaveFlag = new LinkedList<>();
    private static final HashMap<Integer, Queue<Account>> waitingAccountsForCaptureFlag = new HashMap<>();
    private static HashMap<Account, RequestHandler> responseSenders = new HashMap<>();

    static {
        for (int i = 1; i < 10; i++) {
            waitingAccountsForCaptureFlag.put(i, new LinkedList<>());
        }
    }

    @Override
    public void run() {
        while (true) {
            synchronized (waitingAccountsForDeathMatch) {
                if (waitingAccountsForDeathMatch.size() >= 2) {
                    Account first = waitingAccountsForDeathMatch.poll();
                    Account second = waitingAccountsForDeathMatch.poll();
                    startBattle(first, second, BattleMode.DEATH_MATCH);
                }
            }
            synchronized (waitingAccountsForSaveFlag) {
                if (waitingAccountsForSaveFlag.size() >= 2) {
                    Account first = waitingAccountsForSaveFlag.poll();
                    Account second = waitingAccountsForSaveFlag.poll();
                    startBattle(first, second, BattleMode.CAPTURE_FLAG_1);
                }
            }
            synchronized (waitingAccountsForCaptureFlag) {
                for (int i = 1; i < 10; i++) {
                    if (waitingAccountsForCaptureFlag.get(i).size() >= 2) {
                        Account first = waitingAccountsForCaptureFlag.get(i).poll();
                        Account second = waitingAccountsForCaptureFlag.get(i).poll();
                        startBattle(first, second, BattleMode.CAPTURE_FLAG_2, i);
                    }
                }
            }
        }
    }

    private void startBattle(Account first, Account second, BattleMode battleMode) {
        this.startBattle(first, second, battleMode, 0);
    }

    private void startBattle(Account first, Account second, BattleMode battleMode, int flagNumber) {
        //todo set prize
        Battle battle = new Battle(BattleKind.MULTI_PLAYER, battleMode, first, second, flagNumber);
        responseSenders.get(first).setBattle(battle);
        responseSenders.get(second).setBattle(battle);
        Response response = new Response(Environment.BATTLE);
        response.setResponseType(ResponseType.ENTER_MULTI_BATTEL_MAP);
        response.setMap(battle.getMap());
        //set account of the response to the first account and send response to first one
        response.setPlayer(battle.getFirstPlayer());
        response.setSecondPlayerName(second.getUsername());
        responseSenders.get(first).getResponseSender().sendResponse(response);
        //set account of the response to the second account and send response to second one
        response.setPlayer(battle.getSecondPlayer());
        responseSenders.get(second).getResponseSender().sendResponse(response);
        responseSenders.remove(first);
        responseSenders.remove(second);
    }

    public static synchronized void addToWaitingAccounts(Request request, RequestHandler requestHandler) {
        Account account = Main.getOnlineAccounts().get(request.getOuthToken());
        switch (request.getBattleMode()) {
            case DEATH_MATCH:
                waitingAccountsForDeathMatch.add(account);
                break;
            case CAPTURE_FLAG_1:
                waitingAccountsForSaveFlag.add(account);
                break;
            case CAPTURE_FLAG_2:
                waitingAccountsForCaptureFlag.get(request.getFlagNumbers()).add(account);
        }
        responseSenders.put(account, requestHandler);
    }

    public static synchronized void deleteFromWaitingAccounts(Request request) {
        Account account = Main.getOnlineAccounts().get(request.getOuthToken());
        switch (request.getBattleMode()) {
            case DEATH_MATCH:
                waitingAccountsForDeathMatch.remove(account);
                break;
            case CAPTURE_FLAG_1:
                waitingAccountsForSaveFlag.remove(account);
                break;
            case CAPTURE_FLAG_2:
                waitingAccountsForCaptureFlag.get(request.getFlagNumbers()).remove(account);
        }
        responseSenders.remove(account);
    }

    public static synchronized void deleteLogOutAccount(Account account) {
        waitingAccountsForDeathMatch.remove(account);
        waitingAccountsForSaveFlag.remove(account);
        for (int i = 1; i < 10; i++) {
            waitingAccountsForCaptureFlag.get(i).remove(account);
        }
    }

}
