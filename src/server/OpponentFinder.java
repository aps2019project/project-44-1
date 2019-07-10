package server;

import models.Account;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class OpponentFinder extends Thread {
    private static final Queue<Account> waitingAccountsForDeathMatch = new LinkedList<>();
    private static final Queue<Account> waitingAccountsForSaveFlag = new LinkedList<>();
    private static HashMap<Integer,Account> waitingAccountsForCaptureFlag = new HashMap<>();
    private static HashMap<Account, ResponseSender> responseSenders = new HashMap<>();

    @Override
    public void run() {
        while (true) {
//            synchronized (waitingAccounts) {
//                if (waitingAccounts.size() >= 2) {
//                    Account first = waitingAccounts.poll();
//                    Account second = waitingAccounts.poll();
//                    startBattle(first,second);
//                }
//            }
        }
    }

    private void startBattle(Account first, Account second) {
        Response response = new Response(Environment.BATTLE);
        //todo set required information to start a battle
        responseSenders.get(first).sendResponse(response);
        responseSenders.get(second).sendResponse(response);
        responseSenders.remove(first);
        responseSenders.remove(second);
    }

    public static synchronized void addToWaitingAccounts(Request request, ResponseSender responseSender) {
        Account account = Main.getOnlineAccounts().get(request.getOuthToken());
        switch (request.getBattleMode()){
            case DEATH_MATCH:
                waitingAccountsForDeathMatch.add(account);
                break;
            case CAPTURE_FLAG_1:
                waitingAccountsForSaveFlag.add(account);
                break;
            case CAPTURE_FLAG_2:
                waitingAccountsForCaptureFlag.put(request.getFlagNumbers(),account);
        }
        responseSenders.put(account, responseSender);
    }

    public static synchronized void deleteFromWaitingAccounts(Request request) {
        Account account = Main.getOnlineAccounts().get(request.getOuthToken());
        switch (request.getBattleMode()){
            case DEATH_MATCH:
                waitingAccountsForDeathMatch.remove(account);
                break;
            case CAPTURE_FLAG_1:
                waitingAccountsForSaveFlag.remove(account);
                break;
            case CAPTURE_FLAG_2:
                waitingAccountsForCaptureFlag.remove(request.getFlagNumbers(),account);
        }
        responseSenders.remove(account);
    }

}
