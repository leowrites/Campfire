package user.sort;

import net.bytebuddy.TypeCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import user.sort.exceptions.SortCriteriaNotFoundException;

public class SortAlgorithmFactoryTest {

    SortAlgorithmFactory factory1;
    SortAlgorithmFactory factory2;
    SortAlgorithmFactory factory3;

    @Test
    void testFactoryWorksWithExpectedSortCriteria() throws SortCriteriaNotFoundException {
        factory1 = new SortAlgorithmFactory("Helpful");
        factory2 = new SortAlgorithmFactory("Highest Rating");
        factory3 = new SortAlgorithmFactory("Newest");
        assertDoesNotThrow(() -> factory1.createSortAlgorithm());
        assertDoesNotThrow(() -> factory2.createSortAlgorithm());
        assertDoesNotThrow(() -> factory3.createSortAlgorithm());
    }

    @Test
    void testFactoryThrowsExceptionOnWrongSortCriteria(){
        factory1 = new SortAlgorithmFactory("Wrong Input");
        assertThrows(SortCriteriaNotFoundException.class, () -> factory1.createSortAlgorithm());
    }
}
