package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.INTERNAL;

import static ExpenseService.Expense.ExpenseType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExpenseServiceTest {
    @Test
    void should_return_internal_expense_type_if_project_is_internal() throws UnexpectedProjectTypeException {
        Project project = new Project(ProjectType.INTERNAL,"1111111111111");

        assertEquals(ExpenseService.getExpenseCodeByProjectTypeAndName(project),INTERNAL_PROJECT_EXPENSE);
    }

    @Test
    void should_return_expense_type_A_if_project_is_external_and_name_is_project_A() throws UnexpectedProjectTypeException {
        Project project = new Project(ProjectType.EXTERNAL,"Project A");

        assertEquals(ExpenseService.getExpenseCodeByProjectTypeAndName(project),EXPENSE_TYPE_A);
    }

    @Test
    void should_return_expense_type_B_if_project_is_external_and_name_is_project_B() throws UnexpectedProjectTypeException {
        Project project = new Project(ProjectType.EXTERNAL,"Project B");

        assertEquals(ExpenseService.getExpenseCodeByProjectTypeAndName(project),EXPENSE_TYPE_B);
    }

    @Test
    void should_return_other_expense_type_if_project_is_external_and_has_other_name() throws UnexpectedProjectTypeException {
        Project project = new Project(ProjectType.EXTERNAL,"666666666");

        assertEquals(ExpenseService.getExpenseCodeByProjectTypeAndName(project),OTHER_EXPENSE);
    }

    @Test
    void should_throw_unexpected_project_exception_if_project_is_invalid() {
        Project project = new Project(ProjectType.UNEXPECTED_PROJECT_TYPE,"8888888888");

        assertThrows(UnexpectedProjectTypeException.class,()->ExpenseService.getExpenseCodeByProjectTypeAndName(project));
    }
}