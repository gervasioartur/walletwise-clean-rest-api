package com.br.walletwise.core.entities;

import com.br.walletwise.core.domain.entities.User;
import com.br.walletwise.core.exception.DomainException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class UserTests {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException when trying to build user with firstname empty or null")
    void shouldThrowDomainExceptionWhenTryingToBuildUserWithFirstNameEmptyOrNull(String firstname) {
        Throwable exception = Assertions.catchThrowable( () -> new User(
                null,
                firstname,
                "any_last_name",
                "any_username",
                "any_email",
                "any_password",
                true));

        Assertions.assertThat(exception).isInstanceOf(DomainException.class);
        Assertions.assertThat(exception.getMessage()).isEqualTo("Firstname is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException when trying to set firstname with empty or null value")
    void shouldThrowDomainExceptionWhenTryingToSetFirstNameWithEmptyOrNullValue(String firstname) {
        User user = new User(
                null,
                "any_fist_name",
                "any_last_name",
                "any_username",
                "any_email",
                "any_password",
                true);

        Throwable exception = Assertions.catchThrowable(() -> user.setFirstname(firstname) );

        Assertions.assertThat(exception).isInstanceOf(DomainException.class);
        Assertions.assertThat(exception.getMessage()).isEqualTo("Firstname is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException when trying to build user with lastname empty or null")
    void shouldThrowDomainExceptionWhenTryingToBuildUserWitLastNameEmptyOrNull(String lastname) {
        Throwable exception = Assertions.catchThrowable( () -> new User(
                null,
                "first_name",
                lastname,
                "any_username",
                "any_email",
                "any_password",
                true));

        Assertions.assertThat(exception).isInstanceOf(DomainException.class);
        Assertions.assertThat(exception.getMessage()).isEqualTo("Lastname is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException when trying to set lastname with empty or null value")
    void shouldThrowDomainExceptionWhenTryingToSetLastnameWithEmptyOrNullValue(String lastname) {
        User user = new User(
                null,
                "any_fist_name",
                "any_last_name",
                "any_username",
                "any_email",
                "any_password",
                true);

        Throwable exception = Assertions.catchThrowable(() -> user.setLastname(lastname) );

        Assertions.assertThat(exception).isInstanceOf(DomainException.class);
        Assertions.assertThat(exception.getMessage()).isEqualTo("Lastname is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException when trying to build user with username empty or null")
    void shouldThrowDomainExceptionWhenTryingToBuildUserWitUsernameEmptyOrNull(String username) {
        Throwable exception = Assertions.catchThrowable( () -> new User(
                null,
                "first_name",
                "any_last_name",
                username,
                "any_email",
                "any_password",
                true));

        Assertions.assertThat(exception).isInstanceOf(DomainException.class);
        Assertions.assertThat(exception.getMessage()).isEqualTo("Username is required.");
    }
}