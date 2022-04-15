package ru.netology.transfer.data;

import lombok.Value;

public class DataHelper {

    private DataHelper() {}

    @Value
    public static class AuthInfo {
        private String login;
        private String password;

        public static AuthInfo getValidAuthInfo() {
            return new AuthInfo("vasya", "qwerty123");
        }
    }

    @Value
    public static class VerificationCode {
        private String code;

        public static VerificationCode getValidVerificationCode() {
            return new VerificationCode("12345");
        }
    }

    @Value
    public static class CardsInfo {
        private String firstCardNumber;
        private String secondCardNumber;

        public static CardsInfo getValidCardNumbers() {
            return new CardsInfo("5559 0000 0000 0001", "5559 0000 0000 0002");
        }
    }
}
