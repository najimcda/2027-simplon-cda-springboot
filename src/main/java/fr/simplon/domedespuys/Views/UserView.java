package fr.simplon.domedespuys.Views;

public class UserView {

    public interface Id {

    }

    public interface Email{

    }

    public interface Firstname{

    }

    public interface Lastname{

    }



    public interface CreatedAt{

    }
    public interface UserListView extends Email, Firstname, Lastname, CreatedAt {
    }

    public interface UserDetailView extends UserListView, ProductView.ProductListView {
    }

}
