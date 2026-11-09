package fr.simplon.domedespuys.Views;

public class PurchaseProductView {

    public interface Id{

    }

    public interface Quantity{

    }

    public interface Price{

    }

    public interface Purchase{

    }

    public interface Product{

    }

    public interface PurchaseProductListView extends Quantity, Price{

    }

    public interface PurchaseProductDetailView extends PurchaseProductListView,Product, ProductView.Label{

    }


}
