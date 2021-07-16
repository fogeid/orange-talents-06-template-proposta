package br.com.zupacademy.diego.proposta.validators;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValorUnicoValidator implements ConstraintValidator<ValorUnico, Object> {
    @PersistenceContext
    private EntityManager em;

    private String fieldName;
    private Class<?> obj;

    @Override
    public void initialize(ValorUnico constraintAnnotation) {
        this.obj = constraintAnnotation.obj();
        this.fieldName = constraintAnnotation.fieldName();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return this.em.createQuery("SELECT 1 FROM " + this.obj.getName() + " WHERE " + fieldName + "= :field")
                .setParameter("field", value)
                .getResultList()
                .isEmpty();
    }
}
