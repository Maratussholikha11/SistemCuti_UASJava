package org.javajarvis.SistemCuti_UASJava.utils;

import org.javajarvis.SistemCuti_UASJava.model.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
public class AuditorAwareImpl implements AuditorAware<String> {


    @Override
    public Optional<String> getCurrentAuditor() {
        Employee currentEmployee = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.of(currentEmployee.getUsername());
    }
}
