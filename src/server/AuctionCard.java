package server;

import client.CardBuilder;
import models.Account;
import models.Placeable;
import models.Shop;

public class AuctionCard extends Thread {
    private Placeable placeable;
    private Account owner;
    private ResponseSender responseSender;
    private int cardId;
    private int price;
    private Account buyer;
    private ResponseSender buyerResPonseSender;
    private int remainedTime = 180;
    private static int idGenerator=0;


    public AuctionCard(Placeable placeable, Account owner, ResponseSender responseSender) {
        this.placeable = placeable;
        this.owner = owner;
        owner.getCollection().deleteCardFromCollection(owner.getCollection().getCollectionID(placeable.getName()));
        this.responseSender = responseSender;
        price = placeable.getCost();
        this.cardId = idGenerator;
        idGenerator+=1;
    }

    @Override
    public void run() {
        Response response = new Response(Environment.SHOP);
        response.setResponseType(ResponseType.NEW_AUCTION_CARD_ADDED_TO_SHOP);
        response.setAuctionCard(placeable.getName());
        response.setRemainedTimeOfAuction(this.remainedTime);
        response.setAuctionCardId(this.cardId);
        Main.getResponseSenders().forEach(responseSender1 -> responseSender1.sendResponse(response));
        while (remainedTime > 0) {
            remainedTime -= 1;
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (buyer == null) {
            Shop.getInstance().sell(owner.getCollection().getCardIDInCollection(placeable.getName()), owner);
        } else {
            buyer.getCollection().getCollectionCards().add(Shop.getInstance().getCard(placeable.getName()));
            response.setResponseType(ResponseType.SUCCESSFUL_BUY_AUCTION);
            response.setCardToBuy(Shop.getInstance().getCard(placeable.getName()));
            response.setMoney(Integer.toString(buyer.getMoney()));
            buyerResPonseSender.sendResponse(response);

        }
        response.setResponseType(ResponseType.SUCCESSFUL_SELL_AUCTION);
        response.setCardToSell(placeable.getName());
        response.setMoney(Integer.toString(owner.getMoney()));
        responseSender.sendResponse(response);
//        new java.util.Timer().schedule(
//                new java.util.TimerTask() {
//                    @Override
//                    public void run() {
//                        if (buyer==null){
//                            Shop.getInstance().sell(owner.getCollection().getCardIDInCollection(placeable.getName()), owner);
//                        } else {
//                            buyer.getCollection().getCollectionCards().add(Shop.getInstance().getCard(placeable.getName()));
//                            response.setResponseType(ResponseType.SUCCESSFUL_BUY_AUCTION);
//                            response.setCardToBuy(Shop.getInstance().getCard(placeable.getName()));
//                            response.setMoney(Integer.toString(buyer.getMoney()));
//                            buyerResPonseSender.sendResponse(response);
//
//                        }
//                        response.setResponseType(ResponseType.SUCCESSFUL_SELL_AUCTION);
//                        response.setCardToSell(placeable.getName());
//                        response.setMoney(Integer.toString(owner.getMoney()));
//                        responseSender.sendResponse(response);
//                    }
//                },
//                180000
//        );

    }

    public Placeable getPlaceable() {
        return placeable;
    }

    public void setPlaceable(Placeable placeable) {
        this.placeable = placeable;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public ResponseSender getResponseSender() {
        return responseSender;
    }

    public void setResponseSender(ResponseSender responseSender) {
        this.responseSender = responseSender;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Account getBuyer() {
        return buyer;
    }

    public void setBuyer(Account buyer) {
        this.buyer = buyer;
    }

    public ResponseSender getBuyerResPonseSender() {
        return buyerResPonseSender;
    }

    public void setBuyerResPonseSender(ResponseSender buyerResPonseSender) {
        this.buyerResPonseSender = buyerResPonseSender;
    }

    public void updatePrice(int newCost, Account buyer, ResponseSender buyerResponseSender) {
        if (newCost <= price) {
            return;
            //todo can show alert
        }
        if (buyer.getMoney() < newCost) {
            return;
            //todo can show alert
        }
        if (this.buyer != null) {
            this.buyer.increaseMoney(this.price);
            Response response = new Response(Environment.SHOP);
            response.setResponseType(ResponseType.UPDATE_ACCOUNT_MONEY);
            response.setMoney(Integer.toString(buyer.getMoney()));
            buyerResponseSender.sendResponse(response);
        }
        price = newCost;
        this.buyer = buyer;
        buyer.decreaseMoney(newCost);
        this.buyerResPonseSender = buyerResponseSender;
        Response response = new Response(Environment.SHOP);
        response.setResponseType(ResponseType.UPDATE_ACCOUNT_MONEY);
        response.setMoney(Integer.toString(buyer.getMoney()));
        buyerResponseSender.sendResponse(response);

        response.setResponseType(ResponseType.UPDATE_AUCTION_CARD_PRICE);
        response.setAuctionCardId(this.cardId);
        response.setSuggestedPrice(this.price);
        response.setNewBuyer(buyer.getUsername());
        Main.getResponseSenders().forEach(responseSender1 -> responseSender1.sendResponse(response));


    }
}
