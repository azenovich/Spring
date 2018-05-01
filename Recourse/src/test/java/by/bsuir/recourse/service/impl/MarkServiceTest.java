package by.bsuir.recourse.service.impl;

import by.bsuir.recourse.entity.model.Mark;
import by.bsuir.recourse.repository.MarkRepository;
import by.bsuir.recourse.service.CrudService;
import by.bsuir.recourse.service.CrudServiceTest;
import by.bsuir.recourse.service.MarkService;
import by.bsuir.recourse.supplier.entity.model.EntitySupplier;
import by.bsuir.recourse.supplier.entity.model.impl.MarkSupplier;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MarkServiceTest extends CrudServiceTest<Mark, Integer> {
    private MarkService markService;
    private MarkRepository markRepository;
    private MarkSupplier markSupplier;

    public MarkServiceTest() {
        markRepository = Mockito.mock(MarkRepository.class);
        markService = new MarkServiceImpl(markRepository);
        markSupplier = new MarkSupplier();
    }

    @Test
    public void findByExistingSolutionIdTest() throws Exception {
        when(markRepository.findBySolutionId(any())).thenReturn(markSupplier.getValidEntityWithId());

        Optional<Mark> result = markService.findBySolutionId(markSupplier.getAnyId());

        verify(markRepository, times(1)).findBySolutionId(any());
        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void findByNotExistingSolutionIdTest() throws Exception {
        when(markRepository.findBySolutionId(any())).thenReturn(null);

        Optional<Mark> result = markService.findBySolutionId(markSupplier.getAnyId());

        verify(markRepository, times(1)).findBySolutionId(any());
        Assert.assertFalse(result.isPresent());
    }


    @Override
    protected CrudService<Mark, Integer> getCrudService() {
        return markService;
    }

    @Override
    protected CrudRepository<Mark, Integer> getCrudRepository() {
        return markRepository;
    }

    @Override
    protected EntitySupplier<Mark, Integer> getEntitySupplier() {
        return markSupplier;
    }

    @Override
    protected void setupAllowedRoles(Mark entity) { }

}
