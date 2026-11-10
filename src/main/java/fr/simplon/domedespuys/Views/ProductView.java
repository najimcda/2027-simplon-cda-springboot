package fr.simplon.domedespuys.Views;

public class ProductView {

    public interface Id {}
    public interface Label {}
    public interface Description {}
    public interface Price {}
    public interface Slug {}
    public interface CreatedAt {}
    public interface Brand {}
    public interface Images {}
    public interface MainImage {}

    public interface ProductListView extends
            Label,
            Price,
            MainImage,
            ImageView.Path,
            Slug {}
    public interface ProductDetailView extends
            Label,
            Price,
            Description,
            Slug,
            Brand,
            BrandView.Label,
            BrandView.Slug,
            Images,
            ImageView.Path,
            ImageView.Extension,
            ImageView.IsDefault {}

}