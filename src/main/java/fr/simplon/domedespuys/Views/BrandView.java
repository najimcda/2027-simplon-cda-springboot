package fr.simplon.domedespuys.Views;

public class BrandView {

    public interface Id {}
    public interface Label {}
    public interface Logo {}
    public interface Slug {}
    public interface Products {}

    public interface BrandListView extends Label, Slug {}
    public interface BrandDetailView extends
            BrandListView,
            Logo,
            Products,
            ProductView.ProductListView {}

}