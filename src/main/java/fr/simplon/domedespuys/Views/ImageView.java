package fr.simplon.domedespuys.Views;

public class ImageView {
    public interface Id {}
    public interface Path {}
    public interface Extension {}
    public interface IsDefault {}

    public interface ImageDetailView extends Id, Path, Extension, IsDefault {}
}