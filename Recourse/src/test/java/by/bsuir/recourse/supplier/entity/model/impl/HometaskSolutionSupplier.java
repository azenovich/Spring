package by.bsuir.recourse.supplier.entity.model.impl;

import by.bsuir.recourse.entity.model.HometaskSolution;
import by.bsuir.recourse.entity.model.User;
import by.bsuir.recourse.supplier.entity.model.EntityIntegerPKSupplier;

public class HometaskSolutionSupplier implements EntityIntegerPKSupplier<HometaskSolution> {
    private UserSupplier userSupplier = new UserSupplier();

    @Override
    public HometaskSolution getValidEntityWithoutId() {
        HometaskSolution hometaskSolution = new HometaskSolution();
        hometaskSolution.setLessonId(getAnyId());
        User student = userSupplier.getValidEntityWithId();
        hometaskSolution.setStudent(student);
        hometaskSolution.setSolution("solution");
        return hometaskSolution;
    }

    @Override
    public HometaskSolution getInvalidEntity() {
        return new HometaskSolution();
    }
}
