package com.untildawn.Enums;

public enum GameTexts {
    SIGNUP("SIGN UP", "INSCRIPTION"),
    ENTER_YOUR_USERNAME("ENTER YOUR USERNAME", "NOM D'UTILISATEUR"),
    ENTER_YOUR_EMAIL_ADDRESS("ENTER YOUR EMAIL ADDRESS", "ADRESSE E-MAIL"),
    ENTER_YOUR_PASSWORD("ENTER YOUR PASSWORD", "MOT DE PASSE"),
    I_HAVE_ALREADY_HAD_AN_ACCOUNT("I've already had an account", ""),
    PLAY_AS_GUEST("Play as guest", ""),
    LOGIN("LOGIN", ""),
    I_HAVE_FORGOTTEN_MY_PASSWORD("I've forgotten my password", ""),
    I_DO_NOT_HAVE_AN_ACCOUNT("I don't have an account", ""),
    YOU_HAVE_LOGGED_IN_SUCCESSFULLY("You have logged in successfully", ""),
    MAIN_MENU("MAIN MENU", ""),
    LOAD_SAVED_GAME("LOAD SAVED GAME", ""),
    PREPARE_GAME_MENU("PREPARE GAME MENU", ""),
    SCOREBOARD("SCOREBOARD", ""),
    HINT_MENU("HINT MENU", ""),
    SETTING("SETTING", ""),
    PROFILE("PROFILE", ""),
    LOGOUT("LOGOUT", ""),
    DELETE_MY_ACCOUNT("Delete my account", ""),
    SUBMIT("SUBMIT", ""),
    CONFIRM("CONFIRM", ""),
    ARE_YOU_SURE("Are you sure?", ""),
    YES("YES", ""),
    NO("NO", ""),
    HERO("HERO", ""),
    WEAPON("WEAPON", ""),
    AUTO_RELOAD("AUTO RELOAD", ""),
    GRAY_SCALE("GRAY SCALE", ""),
    OFF("OFF", ""),
    ON("ON", ""),
    START_NEW_GAME("START NEW GAME", ""),
    BACK("BACK", ""),
    REVOLVER("REVOLVER", ""),
    SMG_DUAL("SMGs DUAL", ""),
    SHOTGUN("SHOTGUN", ""),
    TIME_2_MINUTES("2 MINUTES", ""),
    TIME_5_MINUTES("5 MINUTES", ""),
    TIME_10_MINUTES("10 MINUTES", ""),
    TIME_20_MINUTES("20 MINUTES", ""),
    START_NEW_GAME_WITH_THESE_PREFERENCES("Start new game with these preferences?", ""),
    USERNAME("USERNAME", ""),
    SCORE("SCORE", ""),
    KILL("KILL", ""),
    LONGEST_TIME_ALIVE("LONGEST TIME ALIVE", ""),
    SORT_BY_USERNAME("Sort by username", ""),
    SORT_BY_SCORE("Sort by score", ""),
    SORT_BY_KILL("Sort by kill", ""),
    SORT_BY_LONGEST_TIME_ALIVE("Sort by longest time alive", ""),
    TRACK_1("TRACK 1", ""),
    TRACK_2("TRACK 2", ""),
    TRACK_3("TRACK 3", ""),
    TRACK_4("TRACK 4", ""),
    TRACK_5("TRACK 5", ""),
    TRACK_6("TRACK 6", ""),
    // TODO: complete
    ;

    private final String englishText;
    private final String frenchText;

    GameTexts(String englishText,
              String frenchText) {
        this.englishText = englishText;
        this.frenchText = frenchText;
    }

    public String getText() {
        return "";
    }
}
