package controller;

import controller.action.Action;
import controller.action.DeleteAction;
import controller.action.IllegalCommandException;
import controller.action.InsertAction;
import controller.action.InsertFormAction;
import controller.action.ListAction;
import controller.action.LoginAction;
import controller.action.LoginFormAction;
import controller.action.LogoutAction;
import controller.action.MlistAction;
import controller.action.UpdateAction;
import controller.action.UpdateFormAction;
import controller.action.ViewAction;
import controller.action.basket.BasketAddFormAction;
import controller.action.basket.BasketAddOnlyOneAction;
import controller.action.basket.BasketByCategoryAction;
import controller.action.basket.BasketDeleteAction;
import controller.action.basket.BasketInsertAction;
import controller.action.basket.BasketListAction;
import controller.action.basket.BasketProductViewAction;
import controller.action.basket.ClistAction;
import controller.action.basket.ScorePlusAction;
import controller.action.elist.CustomerElistListAction;
import controller.action.elist.ElistDeleteAction;
import controller.action.elist.ElistInsertAction;
import controller.action.elist.ElistInsertFormAction;
import controller.action.elist.ElistListAction;
import controller.action.elist.ElistRecAddFormAction;
import controller.action.elist.ElistSearchByCategoryAction;
import controller.action.elist.ElistUpdateAction;
import controller.action.elist.ElistUpdateFormAction;
import controller.action.elist.ElistViewAction;
import controller.action.event.EventDeleteAction;
import controller.action.event.EventInsertAction;
import controller.action.event.EventInsertFormAction;
import controller.action.event.EventListAction;
import controller.action.event.EventUpdateAction;
import controller.action.event.EventUpdateFormAction;
import controller.action.event.EventViewAction;
import controller.action.product.ProductDeleteAction;
import controller.action.product.ProductInsertAction;
import controller.action.product.ProductInsertFormAction;
import controller.action.product.ProductListAction;
import controller.action.product.ProductListSearchByCategoryAction;
import controller.action.product.ProductUpdateAction;
import controller.action.product.ProductUpdateFormAction;
import controller.action.product.ProductViewAction;
import controller.action.receiving.ReceivingAddAction;
import controller.action.receiving.ReceivingDeleteAction;
import controller.action.receiving.ReceivingInsertAction;
import controller.action.receiving.ReceivingInsertListAction;
import controller.action.receiving.ReceivingListAction;
import controller.action.receiving.ReceivingListSearchByCategory;
import controller.action.search.BasketProductViewForAddAction;
import controller.action.search.SearchHighBasketAction;
import controller.action.search.SearchHighScoreAction;
import controller.action.search.SearchNewProductAction;
import function.RecommendListAction;

public class UserCommandFactory {

	private UserCommandFactory() {}

	public static UserCommandFactory getInstance() {
		return new UserCommandFactory();
	}

	/*
	 * 수행해야할 명령어에 해당하는 Action객체를 생성.
	 */
	public Action getAction(String command) throws IllegalCommandException {
		Action action = null;

		if (command.equals("list")) {
			action = new ListAction();
		} else if (command.equals("view")) {
			action = new ViewAction();
		} else if (command.equals("insert")) {
			action = new InsertAction();
		} else if (command.equals("update")) {
			action = new UpdateAction();
		} else if (command.equals("delete")) {
			action = new DeleteAction();
		} else if (command.equals("login")) {
			action = new LoginAction();
		} else if (command.equals("updateForm")) {
			action = new UpdateFormAction();
		} else if (command.equals("insertForm")) {
			action = new InsertFormAction();
		} else if (command.equals("loginForm")) {
			action = new LoginFormAction();
		} else if (command.equals("logout")) {
			action = new LogoutAction();
		} else if(command.equals("mlist")) {
			action = new MlistAction();
		} else if(command.equals("productList")) {
			action = new ProductListAction();
		} else if(command.equals("productInsertForm")) {
			action = new ProductInsertFormAction();
		} else if(command.equals("productInsert")) {
			action = new ProductInsertAction();
		} else if(command.equals("productUpdateForm")) {
			action = new ProductUpdateFormAction();
		} else if(command.equals("productUpdate")) {
			action = new ProductUpdateAction();
		}  else if(command.equals("productDelete")) {
			action = new ProductDeleteAction();
		} else if(command.equals("productView")) {
			action = new ProductViewAction();
		} else if(command.equals("productListSearchByCategory") ){
			action = new ProductListSearchByCategoryAction();
		} else if(command.equals("eventList")) {
			action = new EventListAction();
		} else if(command.equals("eventInsertForm")) {
			action = new EventInsertFormAction();
		} else if(command.equals("eventInsert")) {
			action = new EventInsertAction();
		} else if(command.equals("eventUpdateForm")) {
			action = new EventUpdateFormAction();
		} else if(command.equals("eventUpdate")) {
			action = new EventUpdateAction();
		} else if(command.equals("eventDelete")) {
			action = new EventDeleteAction();
		} else if(command.equals("eventView")) {
			action = new EventViewAction();
		} else if(command.equals("clist")) {
			action = new ClistAction();
		} else if(command.equals("basketList")) {
			action = new BasketListAction();
		} else if(command.equals("basketAddForm")) {
			action = new BasketAddFormAction();
		} else if(command.equals("basketByCategory") ){
			action = new BasketByCategoryAction();
		} else if(command.equals("basketDelete")) {
			action = new BasketDeleteAction();
		} else if(command.equals("basketInsert")) {
			action = new BasketInsertAction();
		} else if(command.equals("basketProductView")) {
			action = new BasketProductViewAction();
		} else if(command.equals("scorePlus")) {
			action = new ScorePlusAction();
		} else if(command.equals("receivingList")) {
			action = new ReceivingListAction();
		} else if(command.equals("receivingListSearchByCategory")) {
			action = new ReceivingListSearchByCategory();
		} else if(command.equals("ReceivingInsertList")) {
			action = new ReceivingInsertListAction();
		} else if(command.equals("receivingAdd")) {
			action = new ReceivingAddAction();
		} else if(command.equals("receivingDelete")) {
			action = new ReceivingDeleteAction();
		} else if(command.equals("receivingInsert")) {
			action = new ReceivingInsertAction();
		} else if(command.equals("searchNewProduct")) {
			action = new SearchNewProductAction();
		} else if(command.equals("searchHighScore")) {
			action = new SearchHighScoreAction();
		} else if(command.equals("searchHighBasket")) {
			action = new SearchHighBasketAction();
		} else if(command.equals("basketProductViewForAdd")) {
			action = new BasketProductViewForAddAction();
		} else if(command.equals("basketAddOnlyOne")) {
			action = new BasketAddOnlyOneAction();
		}  else if(command.equals("recommendList")) {
			action = new RecommendListAction();
		} else if(command.equals("elistList")) {
			action = new ElistListAction();
		} else if(command.equals("elistInsertForm")) {
			action = new ElistInsertFormAction();
		} else if(command.equals("elistInsert")) {
			action = new ElistInsertAction();
		} else if(command.equals("elistUpdateForm")) {
			action = new ElistUpdateFormAction();
		} else if(command.equals("elistUpdate")) {
			action = new ElistUpdateAction();
		} else if(command.equals("elistDelete")) {
			action = new ElistDeleteAction();
		} else if(command.equals("elistView")) {
			action = new ElistViewAction();
		} else if(command.equals("elistSearchByCategory")) {
			action = new ElistSearchByCategoryAction();
		} else if(command.equals("elistRecAddForm")) {
			action = new ElistRecAddFormAction();
		} else if(command.equals("customerElistList")) {
			action = new CustomerElistListAction();
		} else {
			throw new IllegalCommandException("잘못된 실행명령입니다. 다른 명령을 실행해 주십시요");
		}

		return action;
	}
}
