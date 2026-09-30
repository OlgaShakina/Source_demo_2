package user;

import utils.PropertyReader;

public class UserFactory {
    public static User withAdminPermission() {
        return new User(PropertyReader.getProperty("saucedemmo.user"),
                PropertyReader.getProperty("saucedemmo.password"));
    }

    public static User withLockedPermission() {
        return new User(PropertyReader.getProperty("saucedemmo.locked.user"),
                PropertyReader.getProperty("saucedemmo.password"));
    }

    public static User withProblemPermission() {
        return new User(PropertyReader.getProperty("saucedemmo.problem.user"),
                PropertyReader.getProperty("saucedemmo.password"));
    }

    public static User withPerformanceGlitchPermission() {
        return new User(PropertyReader.getProperty("saucedemmo.performance.user"),
                PropertyReader.getProperty("saucedemmo.password"));
    }

    public static User withErrorPermission() {
        return new User(PropertyReader.getProperty("saucedemmo.error.user"),
                PropertyReader.getProperty("saucedemmo.password"));
    }

    public static User withVisualPermission() {
        return new User(PropertyReader.getProperty("saucedemmo.visual.user"),
                PropertyReader.getProperty("saucedemmo.password"));
    }
}