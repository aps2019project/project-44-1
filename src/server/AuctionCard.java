package server;

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


    public AuctionCard(Placeable placeable, Account owner, ResponseSender responseSender) {
        this.placeable = placeable;
        this.owner = owner;
        this.responseSender = responseSender;
        price = placeable.getCost();
    }

    @Override
    public void run() {
        Response response = getResponse();
        Main.getResponseSenders().forEach(responseSender1 -> responseSender1.sendResponse(response));
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        if (buyer == null) {
                            Shop.getInstance().sell(owner.getCollection().getCardIDInCollection(placeable.getName()), owner);
                        } else {
                            buy(response);
                        }
                        successful(response);
                    }
                },
                180000
        );
    }

    private void successful(Response response) {
        response.setResponseType(ResponseType.SUCCESSFUL_SELL_AUCTION);
        response.setCardToSell(placeable.getName());
        response.setMoney(Integer.toString(owner.getMoney()));
        responseSender.sendResponse(response);
    }

    private void buy(Response response) {
        buyer.getCollection().getCollectionCards().add(Shop.getInstance().getCard(placeable.getName()));
        response.setResponseType(ResponseType.SUCCESSFUL_BUY_AUCTION);
        response.setCardToBuy(Shop.getInstance().getCard(placeable.getName()));
        response.setMoney(Integer.toString(buyer.getMoney()));
        buyerResPonseSender.sendResponse(response);
    }

    private Response getResponse() {
        Response response = new Response(Environment.SHOP);
        response.setResponseType(ResponseType.NEW_AUCTION_CARD_ADDED_TO_SHOP);
        response.setAuctionCard(placeable.getName());
        response.setAuctionCardId(this.cardId);
        return response;
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

}
