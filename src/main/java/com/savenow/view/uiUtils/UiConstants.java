package com.savenow.view.uiUtils;

/**
 * Repertory of UI copies.
 */
public class UiConstants {
    public static final String banner = """
                  ________     __  ___      ___  _______  _____  ___      ______    __   __  ___\s
                 /"       )   /""\\|"  \\    /"  |/"     "|(\\"   \\|"  \\    /    " \\  |"  |/  \\|  "|
                (:   \\___/   /    \\\\   \\  //  /(: ______)|.\\\\   \\    |  // ____  \\ |'  /    \\:  |
                 \\___  \\    /' /\\  \\\\\\  \\/. ./  \\/    |  |: \\.   \\\\  | /  /    ) :)|: /'        |
                  __/  \\\\  //  __'  \\\\.    //   // ___)_ |.  \\    \\. |(: (____/ //  \\//  /\\'    |
                 /" \\   :)/   /  \\\\  \\\\\\   /   (:      "||    \\    \\ | \\        /   /   /  \\\\   |
                (_______/(___/    \\___)\\__/     \\_______) \\___|\\____\\)  \\"_____/   |___/    \\___|
                                                                                                \s
        """;
    public static final String menu =  """
            Welcome to SaveNow - Your Personal Finance Organizer!
            --------------------------------------------------------
            Select an option:
            [0] Home                    - Go to home screen.
            [1] All boxes               - Visualize all your boxes.
            [2] Create box              - Create new box.
            [3] Update box              - Update box details.
            [4] Delete box              - Remove box.
            [5] All transactions        - Consult your recent transactions.
            [6] Create transaction      - Register new transaction.
            [7] Update transaction      - Update an existin transaction.
            [8] Exit                    - Exit the program.
            Start managing your finances efficiently!
        """;

    public static final String RED_COLOR = "\u001B[31m";
    public static final String RESET_COLOR = "\u001B[0m";
    public static final String GREEN_COLOR = "\u001B[32m";

    public static final String enterOptionCopy = "Enter option: ";
    public static final String unkownRoutePage = "Unknown route page";
    public static final String defaultPromptLabel = "  ->  ";
    public static final String getAllBoxesTitle = """
            
            *******************************************************
                                   All boxes
            *******************************************************
            """;
    public static final String createBoxTitle = """
            
            *******************************************************
                                   Create box
            *******************************************************
            """;
    public static final String createTransactionTitle = """
            
            *******************************************************
                                   Create transaction
            *******************************************************
            """;
    public static final String updateTransactionTitle = """
            
            *******************************************************
                                   Update transaction
            *******************************************************
            """;
    public static final String updateBoxTitle = """
            
            *******************************************************
                                   Update box
            *******************************************************
            """;
    public static final String deleteBoxTitle = """
            
            *******************************************************
                                   Delete box
            *******************************************************
            """;
    public static final String getAllMovementsTitle = """
            
            *******************************************************
                                   All movements
            *******************************************************
            """;
    public static final String createMovementTitle = """
            
            *******************************************************
                                   Create movement
            *******************************************************
            """;
    public static final String defaultErrorMessage = RED_COLOR + "Uups! Something went wrong. Please try later." + RESET_COLOR;
    public static final String resourceCreatedSuccess = GREEN_COLOR + "Resource created successfully!" + RESET_COLOR;
    public static final String resourceUpdatedSuccess = GREEN_COLOR + "Resource updated successfully!" + RESET_COLOR;
    public static final String resourceDeletedSuccess = GREEN_COLOR + "Resource deleted successfully!" + RESET_COLOR;
}
