package server;

import models.Account;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class OpponentFinder extends Thread {
    private static final Queue<Account> waitingAccounts = new LinkedList<>();
    private static HashMap<Account, ResponseSender> responseSenders = new HashMap<>();

    @Override
    public void run() {
        while (true) {
            synchronized (waitingAccounts) {
                if (waitingAccounts.size() >= 2) {
                    Account first = waitingAccounts.poll();
                    Account second = waitingAccounts.poll();
                    startBattle(first,second);
                }
            }
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

    public static synchronized void addToWaitingAccounts(Account account, ResponseSender responseSender) {
        waitingAccounts.add(account);
        responseSenders.put(account, responseSender);
    }

    public static synchronized void deleteFromWaitingAccounts(Account account) {
        waitingAccounts.remove(account);
        responseSenders.remove(account);
    }

}
