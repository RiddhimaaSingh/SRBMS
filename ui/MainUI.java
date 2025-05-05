package ui;

import controller.LibraryController;
import repository.BookRepository;
import repository.UserRepository;
import service.LibraryService;
import service.UserService;

import java.util.Scanner;

public class MainUI {
    public void start() {
        BookRepository bookRepo = new BookRepository();
        UserRepository userRepo = new UserRepository();
        LibraryService libraryService = new LibraryService(bookRepo, userRepo);
        UserService userService = new UserService(bookRepo);
        LibraryController controller = new LibraryController(libraryService, userService);

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nSelect Role:");
            System.out.println("1. Librarian\n2. User\n3. Exit");
            int role = sc.nextInt();
            sc.nextLine();

            if (role == 1) {
                controller.librarianMenu();
            } else if (role == 2) {
                System.out.print("Enter your User ID: ");
                String userId = sc.nextLine();
                if(!libraryService.isUserExist(userId)){
                    System.out.println("User doesn't Exist");
                }
                else controller.userMenu(userId);
            } else {
                break;
            }
        }
    }
}

