package fr.simplon.domedespuys.Views;


public class PurchaseView {

    public interface Id {
    }

    public interface Number {
    }

    public interface Status {
    }

    public interface CreatedAt {
    }

    public interface User {
    }

    public interface Product{

    }


    public interface PurchaseProduct {
    }


    public interface PurchaseListView extends Number, Status {
    }

    public interface PurchaseDetailView extends PurchaseListView,
            User,
            UserView.Firstname,
            UserView.Lastname,
            PurchaseProduct,
            PurchaseProductView.PurchaseProductDetailView
            {
    }

}