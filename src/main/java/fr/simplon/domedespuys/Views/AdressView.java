package fr.simplon.domedespuys.Views;

public class AdressView {
    public interface Id {}
    public interface Line {}
    public interface ExtraLine {}
    public interface ZipCode {}
    public interface City {}
    public interface Country {}
    public interface Phone {}
    public interface Firstname {}
    public interface Lastname {}
    public interface CreatedAt {}

    public interface AdressDetailView extends Id, Line, ExtraLine, ZipCode, City,
            Country, Phone, Firstname, Lastname, CreatedAt {}
}