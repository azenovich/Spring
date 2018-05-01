package by.bsuir.recourse.supplier.entity.dto;

import by.bsuir.recourse.entity.dto.RegistrationDetails;
import by.bsuir.recourse.entity.model.User;

import java.util.function.Supplier;

public class RegistrationDetailsSupplier implements Supplier<RegistrationDetails> {
    public RegistrationDetails get() {
        RegistrationDetails result = new RegistrationDetails();
        result.setEmail("a@b.c");
        result.setName("Ivan");
        result.setSurname("Shimko");
        result.setPassword("password");
        result.setPasswordConfirmation("password");
        result.setGender(User.Gender.MALE);
        return result;
    }
}
