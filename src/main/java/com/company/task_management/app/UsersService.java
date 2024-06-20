package com.company.task_management.app;

import com.company.task_management.entity.Project;
import com.company.task_management.entity.User;
import io.jmix.core.FetchPlan;
import io.jmix.core.FetchPlans;
import io.jmix.data.PersistenceHints;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("tm_UsersService")
public class UsersService {
    private final FetchPlans fetchPlans;
    @PersistenceContext
    private EntityManager entityManager;

    public UsersService(FetchPlans fetchPlans) {
        this.fetchPlans = fetchPlans;
    }

    @Transactional
    public List<User> getUsersNotInProject(Project project, int firstResult, int maxResult) {
        FetchPlan fetchPlan = fetchPlans.builder(User.class)
                .add("username")
                .partial()
                .build();

        return entityManager.createQuery("SELECT u FROM tm_User u " +
                        "WHERE u.id NOT IN " +
                        "(SELECT u1.id FROM tm_User u1 " +
                        " INNER JOIN u1.projects pul " +
                        " WHERE pul.id = ?1)", User.class)
                .setParameter(1, project.getId())
                .setFirstResult(firstResult)
                .setMaxResults(maxResult)
                .setHint(PersistenceHints.FETCH_PLAN, fetchPlan)
                .getResultList();
    }
}